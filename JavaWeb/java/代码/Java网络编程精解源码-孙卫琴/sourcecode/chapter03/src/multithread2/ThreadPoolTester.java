package multithread2;
public class ThreadPoolTester {
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println(
      "用法: java ThreadPoolTest numTasks poolSize");
      System.out.println(
      "  numTasks - integer: 任务的数目");
      System.out.println(
      "  numThreads - integer: 线程池中的线程数目");
      return;
    }
    int numTasks = Integer.parseInt(args[0]);
    int poolSize = Integer.parseInt(args[1]);

   ThreadPool threadPool = new ThreadPool(poolSize);  //创建线程池

    // 运行任务
    for (int i=0; i<numTasks; i++)
      threadPool.execute(createTask(i));
 
    threadPool.join();  //等待工作线程完成所有的任务
    // threadPool.close(); //关闭线程池
  }//#main()
  
  /**  定义了一个简单的任务(打印ID)   */
  private static Runnable createTask(final int taskID) {
    return new Runnable() {
      public void run() {
        System.out.println("Task " + taskID + ": start");
        try {
          Thread.sleep(500);  //增加执行一个任务的时间
        } catch (InterruptedException ex) { }
        System.out.println("Task " + taskID + ": end");
      }
    };
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
