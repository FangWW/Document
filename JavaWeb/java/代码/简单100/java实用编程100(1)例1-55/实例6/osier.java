/**
 * <p>Title: 树参数</p>
 * <p>Description: 使用继承类，柳树就是树</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: </p>
 * @author 杜江
 * @version 1.0
 */
class tree
{
/**
 *<br>方法说明：树的树根
 *<br>输入参数：
 *<br>返回类型：
 */
  public void root()
  {
    String sSite = "土壤中";
    String sFunction = "吸收养份";
    print("位置："+sSite);
    print("功能："+sFunction);
  }
/**
 *<br>方法说明：树的树干
 *<br>输入参数：
 *<br>返回类型：
 */
  public void bolo()
  {
    String sSite = "地面";
    String sFunction = "传递养份";
    print("位置："+sSite);
    print("功能："+sFunction);
  }
/**
 *<br>方法说明：树的树枝
 *<br>输入参数：
 *<br>返回类型：
 */
  public void branch()
  {
    String sSite = "树干上";
    String sFunction = "传递养份";
    print("位置："+sSite);
    print("功能："+sFunction);
  }
/**
 *<br>方法说明：树的叶子
 *<br>输入参数：
 *<br>返回类型：
 */
  public void leaf()
  {
    String sSite = "树梢";
    String sFunction = "光合作用";
    String sColor = "绿色";
    print("位置："+sSite);
    print("功能："+sFunction);
    print("颜色："+sColor);
  }
/**
 *<br>方法说明：显示信息
 *<br>输入参数：Object oPara 显示的信息
 *<br>返回类型：
 */
  public void print(Object oPara)
  {
    System.out.println(oPara);
  }
/**
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
 */
  public static void  main(String[] arges)
  {
    tree t = new tree();
    t.print("描述一棵树：");
    t.print("树根：");
    t.root();
    t.print("树干：");
    t.bolo();
    t.print("树枝：");
    t.branch();
    t.print("树叶：");
    t.leaf();
  }
}
/**
 * <p>Title: 柳树参数</p>
 * <p>Description: 描述柳树的参数</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: </p>
 * @author 杜江
 * @version 1.0
 */
class osier extends tree
{
 /**
 *<br>方法说明：过载树的树叶
 *<br>输入参数：
 *<br>返回类型：
 */
  public void leaf()
  {
    super.leaf();
    String sShape = "长形";
    super.print("形状："+sShape);
  }
  /**
 *<br>方法说明：扩展树的花
 *<br>输入参数：
 *<br>返回类型：
 */
  public void flower()
  {
    print("哈哈，柳树没有花！！");
  }
/**
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
 */
  public static void  main(String[] args)
  {
    osier o = new osier();
    o.print("柳树树根：");
    o.root();
    o.print("柳树树干：");
    o.bolo();
    o.print("柳树树枝：");
    o.branch();
    o.print("柳树树叶：");
    o.leaf();
    o.print("柳树花：");
    o.flower();
  }
}