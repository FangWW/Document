///////////////////////////////////////////////////////////
// DeJaved by mDeJava v1.0. Copyright 1999 MoleSoftware. //
//       To download last version of this software:      //
//            http://molesoftware.hypermatr.net          //
//               e-mail:molesoftware@mail.ru             //
///////////////////////////////////////////////////////////

package org.apache.struts.upload;

import java.io.*;

// Referenced classes of package org.apache.struts.upload:
//            FormFile

public class DiskFile
    implements FormFile
{

    protected String filePath = null;
    protected String contentType = null;
    protected int fileSize = 0;
    protected String fileName = null;

    public DiskFile(String filePath)
    {
        this.filePath = filePath;
    }

    public byte[] getFileData()
        throws FileNotFoundException, IOException
    {
        byte bytes[] = new byte[getFileSize()];
        FileInputStream fis = new FileInputStream(filePath);
        fis.read(bytes);
        fis.close();
        return bytes;
    }

    public byte[] getFileData(int bufferSize)
        throws FileNotFoundException, IOException
    {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        FileInputStream fis = new FileInputStream(filePath);
        int readLength = 0;
        int totalLength = 0;
        int offset = 0;
        byte bytes[] = new byte[bufferSize];
        while((readLength = fis.read(bytes, offset, bufferSize)) != -1) 
        {
            byteStream.write(bytes, offset, bufferSize);
            totalLength += readLength;
            offset += readLength;
        }

        bytes = byteStream.toByteArray();
        fis.close();
        byteStream.close();
        return bytes;
    }

    public void destroy()
    {
        File tempFile = new File(filePath);
        if(tempFile.exists())
            tempFile.delete();
    }

    public String getFilePath()
    {
        return filePath;
    }

    public void setFileName(String filename)
    {
        fileName = filename;
    }

    public void setContentType(String contentType)
    {
        this.contentType = contentType;
    }

    public void setFileSize(int fileSize)
    {
        this.fileSize = fileSize;
    }

    public String getFileName()
    {
        return fileName;
    }

    public String getContentType()
    {
        return contentType;
    }

    public int getFileSize()
    {
        return fileSize;
    }

    public InputStream getInputStream()
        throws FileNotFoundException, IOException
    {
        return new FileInputStream(filePath);
    }
}
