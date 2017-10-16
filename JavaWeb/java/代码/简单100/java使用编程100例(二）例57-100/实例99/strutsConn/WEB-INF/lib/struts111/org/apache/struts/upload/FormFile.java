///////////////////////////////////////////////////////////
// DeJaved by mDeJava v1.0. Copyright 1999 MoleSoftware. //
//       To download last version of this software:      //
//            http://molesoftware.hypermatr.net          //
//               e-mail:molesoftware@mail.ru             //
///////////////////////////////////////////////////////////

package org.apache.struts.upload;

import java.io.*;

public interface FormFile
{

    public abstract String getContentType();

    public abstract void setContentType(String s);

    public abstract int getFileSize();

    public abstract void setFileSize(int i);

    public abstract String getFileName();

    public abstract void setFileName(String s);

    public abstract byte[] getFileData()
        throws FileNotFoundException, IOException;

    public abstract InputStream getInputStream()
        throws FileNotFoundException, IOException;

    public abstract void destroy();
}
