package store;
public class StoreApp {
  public static void main(String args[])throws Exception{ 
    StoreModel model=new StoreModelImpl();
    StoreView view=new StoreViewImpl(model);
    StoreController ctrl=new StoreControllerImpl(model,view);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
