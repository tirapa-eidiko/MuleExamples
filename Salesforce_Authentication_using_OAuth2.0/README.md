Salesforce Authentication Using OAuth 2.0:

The following example shows you connecting to Salesforce using  OAuth 2.0.

Use Case:
Sometimes in mule we may have a need to connect to Salesforce based on the requirement.For that you need  to connect to the Salesforce using the OAuth.
In the following example the  Salesforce  connector employs to enable OAuth 2.0 authentication and fetches the contacts from the Salesforce.

SetUp and Run the Example:
1.	Download the example project and open the example project in the Anypoint Studio
2.	For using the Salesforce connector with Oauth 2.0 authentication we need to have a ConsumerKey and a Consumer Secret.
3.	 For that you need to log in to  your  Salesforce account and then select  SETUP.Then, on   the left you'll be seen Administer,Build,Deploy and few more.
4.	Among them select the Build ->Create->Apps
5.	Then after select the Connected Apps->New.Create a new Connected App with all the required fields.
6.	When creating the Connected App you need to check the Enable OAuth Settings,after enabling it you will be asked for Callback URL.Provide the URL as http://localhost:8082/oauth2callback
7.	Among the Selected OAuth Scopes add full access and then click save.You will be provided with Consumer Key and Consumer Secret.
8.	Now,open the Anypointstudio and open Global Elements of the project.
9.	Select the Salesforce OAuth 2.0 and paste the Consumer Key and Consumer Secret.
10.	After that Run the project.Now goto the browser and hit the URL http://localhost:8082/ , you will be redirected to the Salesforce account asking your access to the application,click on allow access .
11.	Open the Anypointstudio, you'll be seen the log of the contacts in the console.

Project Description:
The example project contains an Http listener for recieving the requests.It consists of two Salesforce connectors one for the Authorization and another which contains the query for fetching the contacts from the Salesforce account.
A Logger which is placed inside the for each scope will log the Contact details and a Setpayload component for informing the number of contacts fetched. 
