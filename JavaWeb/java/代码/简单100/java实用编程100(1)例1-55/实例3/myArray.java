/**
 * <p>Title: 数组数据操作</p>
 * <p>Description: 演示一维数组和多维数组的初始化和基本操作</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: myArray.java</p>
 * @author 杜江
 * @version 1.0
 */
 public class  myArray{
   //初始化数组变量
   char[] cNum = {'1','2','3','4','5','6','7','8','9','0'};
   char[] cStr = {'a','b','c','d','e','f','g','h',
                  'i','j','k','l','m','n','o','p',
                  'q','r','s','t','u','v','w','x','y','z'};
   int[] iMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
   String[] sMail = {"@","."};
/**
 *<br>方法说明：校验电子邮件
 *<br>输入参数：String sPara 被校验的电子邮件字符
 *<br>返回类型：boolean 如果校验的格式符合电子邮件格式返回true；否则返回false
 */   
   public boolean isMail(String sPara){
   	for(int i=0;i<sMail.length;i++){
   	  if(sPara.indexOf(sMail[i])==-1)
   	    return false;   	  
   	}
   	return true;
   }
/**
 *<br>方法说明：判断是否是数字
 *<br>输入参数：String sPara。 需要判断的字符串
 *<br>返回类型：boolean。如果都是数字类型，返回true；否则返回false
 */   
   public boolean isNumber(String sPara){
   	 int iPLength = sPara.length();
   	 for(int i=0;i<iPLength;i++){
   	  char cTemp = sPara.charAt(i);
   	  boolean bTemp = false;
   	  for(int j=0;j<cNum.length;j++){
   	    if(cTemp==cNum[j]){
   	      bTemp = true;
   	      break;
   	    }
   	  }
   	  if(!bTemp) return false; 
   	 }
    return true;
   }
/**
 *<br>方法说明：判断是否都是英文字符
 *<br>输入参数：String sPara。要检查的字符
 *<br>返回类型：boolean。如果都是字符返回true；反之为false
 */   
   public boolean isString(String sPara){
   	 int iPLength = sPara.length();
   	 for(int i=0;i<iPLength;i++){
   	  char cTemp = sPara.charAt(i);
   	  boolean bTemp = false;
   	  for(int j=0;j<cStr.length;j++){
   	    if(cTemp==cStr[j]){
   	      bTemp = true;
   	      break;
   	    }
   	  }
   	  if(!bTemp) return false; 
   	 }
    return true;
   }
/**
 *<br>方法说明：判断是否是闰年
 *<br>输入参数：int iPara。要判断的年份
 *<br>返回类型：boolean。如果是闰年返回true，否则返回false
 */   
   public boolean chickDay(int iPara){
     return iPara%100==0&&iPara%4==0;
   }
/**
 *<br>方法说明：检查日期格式是否正确
 *<br>输入参数：String sPara。要检查的日期字符
 *<br>返回类型：int。0 日期格式正确，-1 月或这日不合要求， -2 年月日格式不正确 
 */
   public int chickData(String sPara){
   	boolean bTemp = false;
   	//所输入日期长度不正确
   	if(sPara.length()!=10) return -2;
   	//获取年
   	String sYear = sPara.substring(0,4);
   	//判断年是否为数字
   	if(!isNumber(sYear)) return -2;
   	//获取月份
   	String sMonth = sPara.substring(5,7);
   	//判断月份是否为数字
   	if(!isNumber(sMonth)) return -2;
   	//获取日
   	String sDay = sPara.substring(8,10);
   	//判断日是否为数字
   	if(!isNumber(sDay)) return -2;
   	//将年、月、日转换为数字
   	int iYear = Integer.parseInt(sYear);
   	int iMon = Integer.parseInt(sMonth);
   	int iDay = Integer.parseInt(sDay);
   	if(iMon>12) return -1;
   	//闰年二月处理
   	if(iMon==2&&chickDay(iYear)){
   	  if(iDay>29) return 2;
   	}else{
   	  if(iDay>iMonth[iMon-1]) return -1;
   	}
   	return 0;
   }
/**
 *<br>方法说明：主方法，测试用
 *<br>输入参数：
 *<br>返回类型：
 */ 
   public static void main(String[] arges){
     myArray mA = new myArray();
     //校验邮件地址
     boolean bMail = mA.isMail("tom@163.com");
     System.out.println("1 bMail is "+bMail);
     bMail = mA.isMail("tom@163com");
     System.out.println("2 bMail is "+bMail);
     //演示是否是数字
     boolean bIsNum = mA.isNumber("1234");
     System.out.println("1：bIsNum="+bIsNum);
     bIsNum = mA.isNumber("123r4");
     System.out.println("2：bIsNum="+bIsNum);
     //演示是否是英文字符
     boolean bIsStr = mA.isString("wer");
     System.out.println("1：bIsStr="+bIsStr);
     bIsStr = mA.isString("wer3");
     System.out.println("2：bIsStr="+bIsStr);
     //演示检查日期
     int iIsTime = mA.chickData("2003-12-98");
     System.out.println("1：iIsTime="+iIsTime);
     iIsTime = mA.chickData("2003-111-08");
     System.out.println("2：iIsTime="+iIsTime);
     iIsTime = mA.chickData("2003-10-08");
     System.out.println("3：iIsTime="+iIsTime);
     iIsTime = mA.chickData("2000-02-30");
     System.out.println("4：iIsTime="+iIsTime);
   }
 }