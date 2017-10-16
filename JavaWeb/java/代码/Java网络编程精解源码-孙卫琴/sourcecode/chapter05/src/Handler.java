import java.io.*;
import java.nio.channels.*;

public interface Handler {
    public void handle(SelectionKey key) throws IOException;
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
