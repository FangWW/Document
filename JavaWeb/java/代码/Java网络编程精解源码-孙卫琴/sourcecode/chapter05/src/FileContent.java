/*�ļ���ʽ����Ӧ����*/
public class FileContent implements Content {
  //�ٶ��ļ��ĸ�Ŀ¼Ϊ"root"
  private static File ROOT = new File("root");
  private File file;

  public FileContent(URI uri) {
    file = new File(ROOT,
                  uri.getPath()
                  .replace('/',File.separatorChar));
  }

  private String type = null;

  /* ȷ���ļ����� */
  public String type() {
    if (type != null) return type;
    String nm = file.getName();
    if (nm.endsWith(".html")|| nm.endsWith(".htm"))
        type = "text/html; charset=GBK";  //HTML��ҳ
    else if ((nm.indexOf('.') < 0) || nm.endsWith(".txt"))
        type = "text/plain; charset=GBK";  //�ı��ļ�
    else
        type = "application/octet-stream";  //Ӧ�ó���
    return type;
  }

  private FileChannel fileChannel = null;
  private long length = -1;  //�ļ�����
  private long position = -1; //�ļ��ĵ�ǰλ��

  public long length() {
      return length;
  }

  /* ����FileChannel����*/
  public void prepare() throws IOException {
    if (fileChannel == null)
        fileChannel = new RandomAccessFile(file, "r").getChannel();
    length = fileChannel.size();
    position = 0;
  }

  /* �������ģ����������ϣ��ͷ���false�����򷵻�true */
  public boolean send(ChannelIO channelIO) throws IOException {
    if (fileChannel == null)
        throw new IllegalStateException();
    if (position < 0)
        throw new IllegalStateException();

    if (position >= length) {
        return false;  //���������ϣ��ͷ���false
    }

    position += channelIO.transferTo(fileChannel, position, length - position);
    return (position < length);
  }

  public void release() throws IOException {
    if (fileChannel != null){
        fileChannel.close();  //�ر�fileChannel
        fileChannel = null;
    }
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
