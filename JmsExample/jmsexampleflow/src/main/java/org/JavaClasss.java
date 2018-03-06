package org;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;
public class JavaClasss implements Callable{

	@Override
    public Object onCall(MuleEventContext eventContext) throws Exception {

        eventContext.getMessage().setInvocationProperty("a", " World!");
        return eventContext.getMessage().getPayload();
    }
}
