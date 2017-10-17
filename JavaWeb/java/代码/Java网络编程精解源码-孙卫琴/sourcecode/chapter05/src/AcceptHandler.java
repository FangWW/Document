public class AcceptHandler implements Handler {
  public void handle(SelectionKey key) throws IOException {
    ServerSocketChannel serverSocketChannel=(ServerSocketChannel)key.channel();
    SocketChannel socketChannel = serverSocketChannel.accept();
    if (socketChannel== null)return;
    System.out.println("���յ��ͻ����ӣ�����:" +
                   socketChannel.socket().getInetAddress() +
                   ":" + socketChannel.socket().getPort());

    ChannelIO cio =new ChannelIO(socketChannel, false/*������ģʽ*/);
    RequestHandler rh = new RequestHandler(cio);
    socketChannel.register(key.selector(), SelectionKey.OP_READ, rh);
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
