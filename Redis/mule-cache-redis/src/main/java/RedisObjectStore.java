import java.io.Serializable;

import java.util.List;
import org.mule.DefaultMuleEvent;
import org.mule.DefaultMuleMessage;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.construct.FlowConstruct;
import org.mule.api.context.MuleContextAware;
import org.mule.api.registry.RegistrationException;
import org.mule.api.store.ObjectStore;
import org.mule.api.store.ObjectStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheManager;

public class RedisObjectStore<T extends Serializable> implements ObjectStore<T>, MuleContextAware {
	@Autowired
	private RedisCacheManager cacheManager;
	private org.springframework.data.redis.cache.RedisCache cache;
	private MuleContext context;
	private DefaultMuleMessage message;

	@Autowired
	public void setCache() {
		this.cache = (RedisCache) this.cacheManager.getCache("PERSON");
	}

	public RedisCache getCache() {
		return this.cache;
	}

	@Override
	public synchronized boolean contains(Serializable key) throws ObjectStoreException {
		System.out.println("Inside contains Method");
		if (cache.get(key.toString(), Object.class) == null)
			return false;
		else
			return true;
	}

	@Override
	public synchronized void store(Serializable key, T value) throws ObjectStoreException {
		System.out.println("Inside Store Method");
		MuleEvent event = (MuleEvent) value;
		@SuppressWarnings("unchecked")
		List<Object> person = (List<Object>) event.getMessage().getPayload();
		cache.put(key.toString(), person);
	}

	@SuppressWarnings("unchecked")
	@Override
	public synchronized T retrieve(Serializable key) throws ObjectStoreException {
		System.out.println("Inside Retrieve Method");
		List<Object> person = (List<Object>) cache.get(key.toString(), Object.class);
		DefaultMuleEvent event = null;
		String operation = null;
		operation = context.getRegistry().get("operation");
		if (operation.equalsIgnoreCase("store")) {
			System.out.println("retrieve completed");
			return null;
		} else if (person == null && operation.equalsIgnoreCase("remove")) {
			return null;
		} else if (person == null && operation.equalsIgnoreCase("retrieve")) {
			message = new DefaultMuleMessage("Key " + key.toString() + " not found", context);
			FlowConstruct flow = context.getRegistry().lookupFlowConstruct("cacheFlow");
			event = new DefaultMuleEvent(message, org.mule.MessageExchangePattern.ONE_WAY, flow);
		} else {
			message = new DefaultMuleMessage(person, context);
			FlowConstruct flow = context.getRegistry().lookupFlowConstruct("cacheFlow");
			event = new DefaultMuleEvent(message, org.mule.MessageExchangePattern.ONE_WAY, flow);
		}
		
		return (T) event;
	}

	public synchronized T remove(Serializable key) throws ObjectStoreException {
		T value = retrieve(key);
		if (value != null) {
			cache.evict(key);
			try {
				context.getRegistry().registerObject("evict", "Key " + key.toString() + " evicted from cache");
			} catch (RegistrationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				context.getRegistry().registerObject("evict", "Key " + key.toString() + " not found");
			} catch (RegistrationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return value;
	}

	@Override
	public boolean isPersistent() {
		return true;
	}

	@Override
	public synchronized void clear() throws ObjectStoreException {
		System.out.println("Inside clear method");
	}

	@Override
	public void setMuleContext(MuleContext context) {
		this.context = context;
	}

}
