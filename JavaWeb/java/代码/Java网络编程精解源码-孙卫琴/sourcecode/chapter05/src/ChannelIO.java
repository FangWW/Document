public class ChannelIO {
  protected SocketChannel socketChannel;
  protected ByteBuffer requestBuffer;
  private static int requestBufferSize = 4096;

  public ChannelIO(SocketChannel socketChannel, boolean blocking)
    throws IOException {
    this.socketChannel = socketChannel;
    socketChannel.configureBlocking(blocking); //��������ģʽ
    requestBuffer = ByteBuffer.allocate(requestBufferSize);
  }

  public SocketChannel getSocketChannel() {
    return socketChannel;
  }

  /*
   * ���ԭ��������ʣ�������������ʹ���һ���µĻ�����������Ϊԭ��������,
   * ��ԭ�������������ݿ������»�����
   */
  protected void resizeRequestBuffer(int remaining) {
    if (requestBuffer.remaining() < remaining) {
      // ����������ԭ��������
      ByteBuffer bb = ByteBuffer.allocate(requestBuffer.capacity() * 2);
      requestBuffer.flip();
      bb.put(requestBuffer);  //��ԭ���������е����ݿ������µĻ�����
      requestBuffer = bb;
    }
  }

  /*
   * �������ݣ������Ǵ�ŵ�requestBuffer�У����requsetBuffer��ʣ����������5%��
   * �͵���resizeRequestBuffer()������������
   */
  public int read() throws IOException {
    resizeRequestBuffer(requestBufferSize/20);
    return socketChannel.read(requestBuffer);
  }

  /*
   * ����requestBuffer������������е���������
   */
  public ByteBuffer getReadBuf() {
      return requestBuffer;
  }

  /*
   * ���Ͳ���ָ����ByteBuffer�е�����
   */
  public int write(ByteBuffer src) throws IOException {
    return socketChannel.write(src);
  }

  /*
   * ��FileChannel�е�����д��SocketChannel��
   */
  public long transferTo(FileChannel fc, long pos, long len) throws IOException {
    return fc.transferTo(pos, len, socketChannel);
  }

  /*
   * �ر�SocketChannel
   */
  public void close() throws IOException {
    socketChannel.close();
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
