/**
 * 当HTTP请求无法正确解析时，抛出此异常
 *
 */
public class MalformedRequestException extends Exception {

    MalformedRequestException() { }

    MalformedRequestException(String msg) {
	super(msg);
    }

    MalformedRequestException(Exception x) {
	super(x);
    }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
