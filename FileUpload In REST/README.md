File Uploading process in REST Api:
This is a simple example for file uploading process in a REST Api in Mule

At first we have to create a RAML file in our Anypoint Studio with the method  'post' and defining the content-type to be as multipart/formdata.
The following is the simple example for creating RAML file for the above scenerio:
title: SampleAPI 
/file:
  post:
    description: 
      operation to send file to be stored
    body:
      multipart/form-data:
        formParameters:
          file:
            required: true
            example: 
              {
                 "filename": "file to be uploaded"
              }
                
    responses:
     200:
      body:
       application/json:
         example:
           {
                 "Status": "file uploaded successfully"
           }
          

In the above RAML file the content-type is defined as multipart/form-data.Here multipart/formdata is used as the files are sent as an attachment,we can even send an attachment by using binary instead of multipart/form-data but we cannot send the file details along with the attachment and formparameters are defined as file for the above case.
After creating the RAML file in Anypoint Studio right click on the file and select->Mule->generate flows for RAML,flows will be generated for the RAML created. It consists of an HTTP inbound endpoint configured to handle requests on port 8081, an APIKit Router,an Exception mapping flow and a post/file flow.
In the post:/file flow there will be a Setpayload component by default, in that component give the value as #[message.inboundAttachments.file] (file name is 'file' for the above defined RAML) for getting the attachments into our flow that are sent.And then the sent file is stored using the file component with the file name given .pdf extension or you can further process the file according to the requirement.
Finally  to perform the test open postman,select formparameter  and select the file to be uploaded and hit the Url of the flow.
