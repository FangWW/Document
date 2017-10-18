/**
 * 
 */
package zte.lsy.StudentProject.Login;

import org.apache.struts.action.ActionForm;

/**
 * @author Administrator
 *
 */
public class LoginActionForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1847378795884129081L;
	private String Lid;
	private String password;
	private String flag;
	private String number;
	
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
