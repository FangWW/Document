
package mypack;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import javax.xml.namespace.QName;

public class HelloClient
{
   public static void main(String [] args) {
       try {
            String username="Guest";
   	    if(args.length!=0) username=args[0];
           
           String endpoint = 
                    "http://localhost:8080/axis/services/helloservice";
     
           Service  service = new Service();
           Call     call    = (Call) service.createCall();

           call.setTargetEndpointAddress( new java.net.URL(endpoint) );
           call.setOperationName(new QName("urn:helloservice", "sayHello") );
           String ret = (String) call.invoke( new Object[] { username} );

           System.out.println(ret);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
