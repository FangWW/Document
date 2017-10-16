import javax.net.ssl.*;
import javax.net.ssl.SSLEngineResult.*;
import java.io.*;
import java.security.*;
import java.nio.*;

public class SSLEngineDemo {
  private static boolean logging = true;
  private SSLContext sslc;

  private SSLEngine clientEngine; // 客户端Engine
  private ByteBuffer clientOut;	  // 存放客户端发送的应用数据
  private ByteBuffer clientIn;	  // 存放客户端接收到的应用数据

  private SSLEngine serverEngine; //服务器端Engine
  private ByteBuffer serverOut;	  //存放服务器端发送的应用数据
  private ByteBuffer serverIn;	  //存放服务器端接收到的应用数据
    
  private ByteBuffer cTOs;	  //存放客户端向服务器端发送的网络数据
  private ByteBuffer sTOc;	  //存放服务器端向客户端发送的网络数据

  //设置密钥库文件和信任库文件以及口令
  private static String keyStoreFile = "test.keys";
  private static String trustStoreFile = "test.keys";
  private static String passphrase = "654321";

  public static void main(String args[]) throws Exception {
    SSLEngineDemo demo = new SSLEngineDemo();
    demo.runDemo();

    System.out.println("Demo Completed.");
  }

  /** 初始化SSLContext */
  public SSLEngineDemo() throws Exception {
    KeyStore ks = KeyStore.getInstance("JKS");
    KeyStore ts = KeyStore.getInstance("JKS");

    char[] password = passphrase.toCharArray();
    ks.load(new FileInputStream(keyStoreFile), password);
    ts.load(new FileInputStream(trustStoreFile), password);

    KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
    kmf.init(ks, password);

    TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
    tmf.init(ts);

    SSLContext sslCtx = SSLContext.getInstance("TLS");
    sslCtx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

    sslc = sslCtx;
  }

  private void runDemo() throws Exception {
    boolean dataDone = false;

    createSSLEngines();
    createBuffers();

    SSLEngineResult clientResult;	
    SSLEngineResult serverResult;	

    while (!isEngineClosed(clientEngine)|| !isEngineClosed(serverEngine)) {
      log("================");
      clientResult = clientEngine.wrap(clientOut, cTOs); //客户端打包应用数据
      log("client wrap: ", clientResult);
      runDelegatedTasks(clientResult, clientEngine);  //完成握手任务

      serverResult = serverEngine.wrap(serverOut, sTOc);  //服务器端打包应用数据
      log("server wrap: ", serverResult);
      runDelegatedTasks(serverResult, serverEngine); //完成握手任务

      cTOs.flip();
      sTOc.flip();

      log("----");

      clientResult = clientEngine.unwrap(sTOc, clientIn);  //客户端展开网络数据
      log("client unwrap: ", clientResult);
      runDelegatedTasks(clientResult, clientEngine);  //完成握手任务

      serverResult = serverEngine.unwrap(cTOs, serverIn); //服务器端展开网络数据
      log("server unwrap: ", serverResult);
      runDelegatedTasks(serverResult, serverEngine);  //完成握手任务

      cTOs.compact();
      sTOc.compact();

      if (!dataDone && (clientOut.limit() == serverIn.position()) &&
		    (serverOut.limit() == clientIn.position())) {
	checkTransfer(serverOut, clientIn);
	checkTransfer(clientOut, serverIn);

	log("\tClosing clientEngine's *OUTBOUND*...");
	clientEngine.closeOutbound();
	dataDone = true;
      }
    }
  }
  
  /** 创建客户端以及服务器端的SSLEngine */
  private void createSSLEngines() throws Exception {
    serverEngine = sslc.createSSLEngine();
    serverEngine.setUseClientMode(false);
    serverEngine.setNeedClientAuth(true);

    clientEngine = sslc.createSSLEngine("client", 80);
    clientEngine.setUseClientMode(true);
  }

  /** 创建客户端以及服务器端的应用缓冲区和网络缓冲区 */
  private void createBuffers() {
    SSLSession session = clientEngine.getSession();
    int appBufferMax = session.getApplicationBufferSize();
    int netBufferMax = session.getPacketBufferSize();
    clientIn = ByteBuffer.allocate(appBufferMax + 50);
    serverIn = ByteBuffer.allocate(appBufferMax + 50);

    cTOs = ByteBuffer.allocateDirect(netBufferMax);
    sTOc = ByteBuffer.allocateDirect(netBufferMax);

    clientOut = ByteBuffer.wrap("Hi Server, I'm Client".getBytes());
    serverOut = ByteBuffer.wrap("Hello Client, I'm Server".getBytes());
  }
  
  /** 执行SSL握手任务 */ 
  private static void runDelegatedTasks(SSLEngineResult result,
	    SSLEngine engine) throws Exception {
    if(result.getHandshakeStatus() == HandshakeStatus.NEED_TASK) {
      Runnable runnable;
      while((runnable = engine.getDelegatedTask()) != null) {
	log("\trunning delegated task...");
	runnable.run();
      }
      HandshakeStatus hsStatus = engine.getHandshakeStatus();
      if(hsStatus == HandshakeStatus.NEED_TASK) {
	throw new Exception("handshake shouldn't need additional tasks");
      }
      log("\tnew HandshakeStatus: " + hsStatus);
    }
  }
  
  /** 当SSLEngine的输出与输入都关闭，则意味着SSLEngine被关闭 */
  private static boolean isEngineClosed(SSLEngine engine) {
    return(engine.isOutboundDone() && engine.isInboundDone());
  }
  
  /** 判断两个缓冲区内容是否相同 */
  private static void checkTransfer(ByteBuffer a, ByteBuffer b)throws Exception {
    a.flip();
    b.flip();

    if(!a.equals(b)) {
      throw new Exception("Data didn't transfer cleanly");
    }else{
      log("\tData transferred cleanly");
    }

    a.position(a.limit());
    b.position(b.limit());
    a.limit(a.capacity());
    b.limit(b.capacity());
  }

  private static boolean resultOnce = true;

  /** 输出日志，打印SSLEngineResult的结果 */
  private static void log(String str, SSLEngineResult result) {
    if(resultOnce){
      resultOnce = false;
      System.out.println("The format of the SSLEngineResult is: \n" +
		"\t\"getStatus() / getHandshakeStatus()\" +\n" +
		"\t\"bytesConsumed() / bytesProduced()\"\n");
    }
    HandshakeStatus hsStatus = result.getHandshakeStatus();
    log(str +
          result.getStatus() + "/" + hsStatus + ", " +
          result.bytesConsumed() + "/" + result.bytesProduced() +" bytes");
    if (hsStatus == HandshakeStatus.FINISHED) {
       log("\t...ready for application data");
    }
  }

  /** 输出日志*/
  private static void log(String str) {
    System.out.println(str);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
