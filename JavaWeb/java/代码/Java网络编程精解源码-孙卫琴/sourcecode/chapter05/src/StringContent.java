import java.io.*;
import java.nio.*;
import java.nio.charset.*;
/* 字符串形式的内容 */
public class StringContent implements Content {

    private static Charset charset = Charset.forName("GBK");
    private String type;		// MIME type
    private String content;

    public StringContent(CharSequence c, String t) {
	content = c.toString();
	if (!content.endsWith("\n"))
	    content += "\n";
	type = t + "; charset=GBK";
    }

    public StringContent(CharSequence c) {
	this(c, "text/plain");
    }

    public StringContent(Exception x) {
	StringWriter sw = new StringWriter();
	x.printStackTrace(new PrintWriter(sw));
	type = "text/plain; charset=GBK";
	content = sw.toString();
    }

    public String type() {
	return type;
    }

    private ByteBuffer bb = null;

    private void encode() {
	if (bb == null)
	    bb = charset.encode(CharBuffer.wrap(content));
    }

    public long length() {
	encode();
	return bb.remaining();
    }

    public void prepare() {
	encode();
	bb.rewind();
    }

    public boolean send(ChannelIO cio) throws IOException {
	if (bb == null)
	    throw new IllegalStateException();
	cio.write(bb);

	return bb.hasRemaining();
    }

    public void release() throws IOException {}
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
