import javax.net.ssl.SSLEngineResult.*;

public class SSLEngineDemo {
  private static boolean logging = true;
  private SSLContext sslc;

  private SSLEngine clientEngine; // �ͻ���Engine
  private ByteBuffer clientOut;	  // ��ſͻ��˷��͵�Ӧ������
  private ByteBuffer clientIn;	  // ��ſͻ��˽��յ���Ӧ������

  private SSLEngine serverEngine; //��������Engine
  private ByteBuffer serverOut;	  //��ŷ������˷��͵�Ӧ������
  private ByteBuffer serverIn;	  //��ŷ������˽��յ���Ӧ������
    
  private ByteBuffer cTOs;	  //��ſͻ�����������˷��͵���������
  private ByteBuffer sTOc;	  //��ŷ���������ͻ��˷��͵���������

  //������Կ���ļ������ο��ļ��Լ�����
  private static String keyStoreFile = "test.keys";
  private static String trustStoreFile = "test.keys";
  private static String passphrase = "654321";

  public static void main(String args[]) throws Exception {
    SSLEngineDemo demo = new SSLEngineDemo();
    demo.runDemo();

    System.out.println("Demo Completed.");
  }

  /** ��ʼ��SSLContext */
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
      clientResult = clientEngine.wrap(clientOut, cTOs); //�ͻ��˴��Ӧ������
      log("client wrap: ", clientResult);
      runDelegatedTasks(clientResult, clientEngine);  //�����������

      serverResult = serverEngine.wrap(serverOut, sTOc);  //�������˴��Ӧ������
      log("server wrap: ", serverResult);
      runDelegatedTasks(serverResult, serverEngine); //�����������

      cTOs.flip();
      sTOc.flip();

      log("----");

      clientResult = clientEngine.unwrap(sTOc, clientIn);  //�ͻ���չ����������
      log("client unwrap: ", clientResult);
      runDelegatedTasks(clientResult, clientEngine);  //�����������

      serverResult = serverEngine.unwrap(cTOs, serverIn); //��������չ����������
      log("server unwrap: ", serverResult);
      runDelegatedTasks(serverResult, serverEngine);  //�����������

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
  
  /** �����ͻ����Լ��������˵�SSLEngine */
  private void createSSLEngines() throws Exception {
    serverEngine = sslc.createSSLEngine();
    serverEngine.setUseClientMode(false);
    serverEngine.setNeedClientAuth(true);

    clientEngine = sslc.createSSLEngine("client", 80);
    clientEngine.setUseClientMode(true);
  }

  /** �����ͻ����Լ��������˵�Ӧ�û����������绺���� */
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
  
  /** ִ��SSL�������� */ 
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
  
  /** ��SSLEngine����������붼�رգ�����ζ��SSLEngine���ر� */
  private static boolean isEngineClosed(SSLEngine engine) {
    return(engine.isOutboundDone() && engine.isInboundDone());
  }
  
  /** �ж����������������Ƿ���ͬ */
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

  /** �����־����ӡSSLEngineResult�Ľ�� */
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

  /** �����־*/
  private static void log(String str) {
    System.out.println(str);
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
