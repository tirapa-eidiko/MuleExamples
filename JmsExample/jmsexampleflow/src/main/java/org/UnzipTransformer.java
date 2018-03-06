package org;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.mule.el.context.MessageContext;
public class UnzipTransformer {

    public static MessageContext  m(MessageContext messageContext) throws IOException {
        String zipFilePath = "C:/Users/db2admin.bandaru-PC/Videos/Github/Github.zip";
        
        String destDir = "C:/Users/db2admin.bandaru-PC/Videos/Github";
        
        unzip(zipFilePath, destDir);
		return messageContext;
		
    }
    private static void unzip(String zipFilePath, String destDir) throws IOException {
  	  File dir = new File(destDir);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            do{
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                String nf=newFile.getAbsolutePath();
                System.out.println("file name is"+nf);
                System.out.println("Unzipping to "+newFile.getAbsolutePath());
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
               
                if(nf.contains(".zip"))
                {
              	  zipFilePath=nf;
              	  unzip(zipFilePath, "C:/Users/db2admin.bandaru-PC/Videos/Github");
              	  
                }
  				
                }
                
             
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }while (ze != null);
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
  	
  	
  }

