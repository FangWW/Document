package remotecall;
import java.util.Date;
public class HelloServiceImpl implements HelloService{
  public String echo(String msg){
    return "echo:"+msg;
  }
  public Date getTime(){
    return new Date();
  }
} 


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
