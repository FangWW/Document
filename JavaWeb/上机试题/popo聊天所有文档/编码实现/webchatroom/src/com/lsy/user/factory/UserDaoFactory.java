/**
 * 
 */
package com.lsy.user.factory;


import com.lsy.user.dao.UserDao;
import com.lsy.user.daoImp.UserDaoImp;


/**
 * @author ÀµÉùÔ¨
 *
 */
public class UserDaoFactory {
	
    private static UserDao userDao;
   
    private UserDaoFactory(){
    	userDao=new UserDaoImp();
    }
    
    public static synchronized UserDao getInstance(){
    	if(userDao==null){
    		userDao=new UserDaoImp();
    	}
    	return userDao;
    }
    
	
}
