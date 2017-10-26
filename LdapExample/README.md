LDAP Connector:

The Lightweight Directory Access Protocol (LDAP) is a public standard that facilitates maintenance and access to distributed directories (such as network user privilege information) over an Internet Protocol (IP) network.

Many LDAP servers are included in free-use open source projects and packages. The particulars of installing, running and configuring LDAP servers fall outside the scope of this document.

MuleSoft maintains this connector under the Select support policy.

Prerequisites:

To use the LDAP connector, you must have the following:

Access to either an OpenLDAP, Apache Directory or MicroSoft Active Directory Instance.

Anypoint Studio - Enterprise Edition.

To use the LDAP connector in a production environment, you must have either:

An Enterprise license to use Mule.

A CloudHub Starter, Professional, or Enterprise account.

Compatibility:

The LDAP connector is compatible with:

Application/Service	Version

Mule Runtime :3.6.0 or higher

Java :1.7.0_x or higher

Anypoint Studio:5.2 or higher

Installing the Connector:

To install LDAP connector in Anypoint Studio, follow the steps below:

Open Anypoint Studio and go to Help > Install New Software.

Select Anypoint Connectors Update Site or http://repository.mulesoft.org/connectors/releases/3.5.0.

Locate and select the LDAP connector. 

Click Next and accept the License Agreement.

Restart Studio when prompted.

Now, the LDAP connector should appear in your Studio Palette.

Using the Connector:

The LDAP connector is an operation-based connector, which means that when you add the connector to your flow, you need to configure a specific operation for the connector to execute. The connector currently supports the following operations:

Operation	Description:

Bind : Authenticates against the LDAP server. This occurs automatically before each operation but can also be performed on request.

Unbind : Closes the current connection, forcing the login operation (bind) the next time it is used.

Search : Performs an LDAP search in a base DN with a given filter.

Search one : Performs an LDAP search that is supposed to return a unique result.

Paged result search :Performs an LDAP search and streams result to the rest of the flow.

Lookup : Retrieves a unique LDAP entry.

Exists : Checks whether an LDAP entry exists in the LDAP server or not.

Add Entry : Creates a new LDAP entry.

Add single-valued attribute : Adds a specific single-valued attribute to an existing LDAP entry.

Add multi-valued attribute : Adds a specific multi-valued attribute to an existing LDAP entry.

Modify : Updates an existing LDAP entry.

Modify single-valued attribute

Updates specific single-valued attribute of an existing LDAP entry.

Modify multi-valued attribute : Updates specific multi-valued attribute of an existing LDAP entry.

Rename entry : Renames and existing LDAP entry (moves and entry from a DN to another one).

Delete : Deletes an existing LDAP entry.

LDAP entry to LDI : Transforms a LDAPEntry to a String in LDIF representation (RFC 2849).

Example Use Case with LDAP Connector:

To Lookup for a person in an Organisation:

1.Create a new Mule Project in Anypoint Studio.

2.Add a suitable Mule Inbound Endpoint, such as the HTTP listener or File endpoint at the beginning of the flow.

3.Drag and drop the LDAP connector onto the canvas.

4.In the configure the following properties
Name:provide an unique name
Principal Dn:cn=Manager,dc=maxcrc,dc=com(give the values you have created while installing the openldap server)
Password:*******
Url:ldap://localhost:389
After filling the details check the Test connection, if it is successful it indicates that it is successfully connected to your ldap server)

5.Choose the operation :LookUp

6.In general provide the DN details to which you details you are looking up for
ex:cn=user2,ou=eidiko,dc=maxcrc,dc=com

7.Provide the Structural Object Class of that organization you are looking up for
ex:inetOrgPerson

8.Place a logger for getting that user details

9.Now Test your application 

10.If the existing user exists then it provides the complete details of that user

ex:employeeType: pert
homePhone: 511-111
carLicense: 457
mobile: 6975481
sn: user#
cn: user2
departmentNumber: 769
objectClass: inetOrgPerson
objectClass: organizationalPerson
objectClass: person
objectClass: top
userPassword:: c2FyYXN1MTA=

In a similar way we can perform different operations like exists , adding an entry to the organisation,deleting an entry,renaming an entry

