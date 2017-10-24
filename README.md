# Salesforce data synchronization Using Batch processing and Batch commit
This example illustrates the concept of data synchronozation 

#Set Up and Run the Example

Complete the following procedure to create, then run this example in your own instance of Anypoint Studio. Skip ahead to the next section if you prefer to simply examine this example.
1.	Open the Example project in Anypoint Studio from Anypoint Exchange. Do not run the application.
2.	Log in to your Salesforce account. From your account menu (your account is labeled with your name), select Setup.
3.	In the left navigation bar, under the Personal Setup heading, click to expand the My Personal Information folder.
4.	Click Reset My Security Token. Salesforce resets the token and emails you the new one.
5.	Access the email that Salesforce sent and copy the new token onto your local clipboard.
6.	In your application in Studio, click the Global Elements tab.
7.	Double-click the Salesforce global element to open its Global Element Properties panel. In the Security Token field, paste the new Salesforce token you copied from the email. Alternatively, configure the global element in the XML Editor.
8.	Change the contents of the Username and Password fields to your account-specific values, then click OK to save your changes.
9.	In the Package Explorer, right-click the connect-with-salesforce project name, then select Run As > Mule Application. Studio runs the application on the embedded server.

#How it Works

Using a single flow, this application queries Database contacts and prints them in the console view.
The Poll Component periodically invokes Database to perform a query in your database to get Name, state, city, phone attributes. The polling is set to be executed every 1000 seconds right from the start but can be easily changed by accessing component configuration. The polling is executed in the Input Phase of Batch Processing
The following steps outline the process to build this application.
1.	Drop a Batch Scope in your application.
2.	Drop a Poll Component in the Input Phase of the Batch enabling the polling mechanism. Set Frequency and Time units to 1000 seconds or any time period you wish. 
3.	Drop a database connector inside the poll and click on new Connector Configuration then select the mysql configuration and provide the details of hostname, port, username, password, database name and add the mysql driver jar file and click on Test connection to check whether it is working fine or not. 
4.	 The connection test results in success and Mule saves your global element configurations. If any of the values are invalid, the connection test results in failure, and Mule does not save the global element, prompting you to correct the invalid configurations. Click the dropdown button and  select the Select Operation and write the query to fetch the database records.

5.	Next, put a Salesforce Connector into BatchStep. At this point, you can configure the connector with your Salesforce account-specific details and test the connection to Salesforce.
6.	When you click Test Connection, Mule tests the connection to Salesforce. With a valid username, password and security token, the connection test results in success and Mule saves your global element configurations. If any of the values are invalid, the connection test results in failure, and Mule does not save the global element, prompting you to correct the invalid configurations.
7.	Back in the Salesforce connector properties editor, choose Query operation from the dropdown menu and set it as Create and select the Object Type as Contact(contact).
8.	Add a Logger Component to the Process Records Phase of the batch and set Message to #[payload]
9.	Drag and drop transform message in between the database and salesforce connectors and map the payload from database connector to salesforce contacts. Here the lastname is mandatory field in salesforce contacts.
10.	The configuration now complete, you can save, then run the application. After running the application open your salesforce application and check your contacts.

