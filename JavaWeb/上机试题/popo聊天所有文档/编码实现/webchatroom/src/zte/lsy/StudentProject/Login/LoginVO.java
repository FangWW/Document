package zte.lsy.StudentProject.Login;

public class LoginVO {
   private String Lid;
   private String flag;
   private String password;
   private String number;
   private String name;


/**
 * @return the name
 */
public String getName() {
	return name;
}
/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}
/**
 * @param password the password to set
 */
public void setPassword(String password) {
	this.password = password;
}
/**
 * @return the password
 */
public String getPassword() {
	return password;
}
/**
 * @param flag the flag to set
 */
public void setFlag(String flag) {
	this.flag = flag;
}
/**
 * @return the flag
 */
public String getFlag() {
	return flag;
}
/**
 * @param number the number to set
 */
public void setNumber(String number) {
	this.number = number;
}
/**
 * @return the number
 */
public String getNumber() {
	return number;
}
/**
 * @param lid the lid to set
 */
public void setLid(String lid) {
	Lid = lid;
}
/**
 * @return the lid
 */
public String getLid() {
	return Lid;
}
}
