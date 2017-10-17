public class RequestHandler implements Handler {
  private ChannelIO channelIO;
  private ByteBuffer requestByteBuffer = null;  //���HTTP����Ļ�����

  private boolean requestReceived = false;  //�Ƿ��Ѿ����յ������е�HTTP����
  private Request request = null;  //��ʾHTTP����
  private Response response = null;  //��ʾHTTP��Ӧ

  RequestHandler(ChannelIO channelIO) {
    this.channelIO = channelIO;
  }

  /* 
   * ����HTTP��������Ѿ����յ������е�HTTP�������ݣ��ͷ���true,���򷵻�false
   */
  private boolean receive(SelectionKey sk) throws IOException {
    ByteBuffer tmp = null;

    if (requestReceived)return true;  //����Ѿ����յ�����HTTP�������ݣ�����true
    
    //����Ѿ�����ͨ����ĩβ�������Ѿ�����HTTP�������ݵ�ĩβ��־��\r\n�����ͷ���true
    if ((channelIO.read() < 0) || Request.isComplete(channelIO.getReadBuf())) {
      requestByteBuffer = channelIO.getReadBuf();
      return (requestReceived = true);
    }
    return false;
  }

  /*
   * ͨ��Request���parse()����������requestByteBuffer�е�HTTP�������ݣ�������Ӧ��Request���� 
   */
  private boolean parse() throws IOException {
    try {
      request = Request.parse(requestByteBuffer);
      return true;
    } catch (MalformedRequestException x) {  
      //���HTTP����ĸ�ʽ����ȷ���ͷ��ʹ�����Ϣ
      response = new Response(Response.Code.BAD_REQUEST,
                          new StringContent(x));
    }
    return false;
  }

  /*
   * ����HTTP��Ӧ 
   */
  private void build() throws IOException {
    Request.Action action = request.action();
    //����֧��GET��HEAD����ʽ
    if ((action != Request.Action.GET) &&
            (action != Request.Action.HEAD)){
       response = new Response(Response.Code.METHOD_NOT_ALLOWED,
                          new StringContent("Method Not Allowed"));
    }else{
       response = new Response(Response.Code.OK,
                      new FileContent(request.uri()), action);
    }
  }
  
  /*  ����HTTP���󣬷���HTTP��Ӧ */
  public void handle(SelectionKey sk) throws IOException {
    try {
        if (request == null) { //�����û�н��յ�HTTP�������������
            //����HTTP����      
            if (!receive(sk))return;
            requestByteBuffer.flip();
     
            //����ɹ�������HTTP���󣬾ʹ���һ��Response����
            if (parse())build();
     
            try {
                response.prepare();  //׼��HTTP��Ӧ������
            } catch (IOException x) {
                response.release();  
                response = new Response(Response.Code.NOT_FOUND,
                                  new StringContent(x));
                response.prepare();
            }

            if (send()) {  
               //���HTTP��Ӧû�з�����ϣ�����Ҫע��д�����¼���
               //�Ա���д�����¼�����ʱ������������
               sk.interestOps(SelectionKey.OP_WRITE);
            } else {
               //���HTTP��Ӧ������ϣ��ͶϿ��ײ�����ӣ������ͷ�Responseռ�õ���Դ
               channelIO.close();
               response.release();
            }
        } else {  //����Ѿ����յ�HTTP�������������
            if (!send()) {  //���HTTP��Ӧ�������
              channelIO.close();
              response.release();
            }
        }
    } catch (IOException x) {
        x.printStackTrace();
        channelIO.close();
        if (response !=  null) {
            response.release();
        }
    }
  }

  /* ����HTTP��Ӧ�����ȫ��������ϣ��ͷ���false�����򷵻�true */  
  private boolean send() throws IOException {
    return response.send(channelIO);
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
