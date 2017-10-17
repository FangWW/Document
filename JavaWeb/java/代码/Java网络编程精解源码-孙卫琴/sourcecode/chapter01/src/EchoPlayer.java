public class EchoPlayer {
  public String echo(String msg) {
    return "echo:"+msg;
  }
  public void talk()throws IOException {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    String msg=null;
    while((msg=br.readLine())!=null){
      System.out.println(echo(msg));
      if(msg.equals("bye"))  //���û����롰bye������������
        break;
    }
  }

  public static void main(String arg[])throws IOException{
    new EchoPlayer().talk();
  }
}
