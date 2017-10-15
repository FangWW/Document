package com.javapatterns.state.login;

public class LogoutState extends UserState
{
    public LogoutState()
    {
        //write your code here
    }

    public boolean login(String userId, String password)
    {
        StringBuffer sql = new StringBuffer(50);
        sql.append("SELECT COUNT(*) FROM user_info WHERE user_id = '")
            .append(userId)
            .append("' AND password = '")
            .append(password)
            .append("'");

        int counting = DBManager.getAggregate(sql.toString());

        if(counting > 0)
        {
            this.setNextPage(UserState.PAGE_WELCOME);
            this.setCurrentState(new LoginState());
            return true;
        }
        else
        {
            this.setNextPage(UserState.PAGE_LOGIN);
            this.setCurrentState(new LogoutState());
            return false;
        }
    }

    public void logout()
    {
        this.setCurrentState(new LogoutState());
     	this.setNextPage(UserState.PAGE_LOGIN);
    }
}
