# Batch Processing in mule with Exceptoin handling
This example illustrates the concept of Batch Processing in mule

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
9.	Click on the database, goto connector configuration and provide your mysql configuration details 
	i.e (host, port, username, password and add the mysql driver) and then click on test connection to 
	verify your connection.
10.	In the Package Explorer, right-click the connect-with-salesforce project name, then select Run As > Mule Application. Studio runs the application on the embedded server.

#How it Works
Input phase:
This application queries Database contacts and prints them in the console view.
Process Phase:
•	Whatever data we are fetching from the database that values can be inserted into database, some of the records may fail because of using primary key in the database it should not allow duplicates.
•	Failed records can be handled in the next batch step by using Accept policy ONLY_FAILURES.
•	Here we used Salesforce connector to handle those failures, if any of the records fails in the Step1 we can able to catch in next Batch Step.
•	If you want to handle all the records or only success records then you can use Accept Policy as (NO_FAILURES OR ALL)

?	If you don't want to allow any duplicates then put Max Failed Records is "0"
?	If you want to continue processing even any number of failed records then put Max Failed 
Records is "-1"
?	If you want to allow certain number of failed records then put Max Failed 
Records is(integer)  "5" 

 
Batch Block Size:

•	The problem is that if you have 1 million records to place in a queue for a batch job that has 3 steps Instead of queueing each record individually, we queue blocks of 100 records, reducing the I/O overhead at the cost of higher working memory requirements.
•	100 turned out to be a nice convergence number between performance and reasonable working memory requirements.
On Complete Phase:
•	In this phase it displays report or summary of the records it processed for the particular batch job instance. This phase exists to give system administrators and developers some insight into which records failed so as to address any issues that might exist with the input data. 


