package com.javapatterns.state.login;

public class LoginState extends UserState
{
    public LoginState()
    {
        //write code here
    }

    public boolean login(String userId, String password)
    {
        setNextPage(UserState.PAGE_WELCOME);
        setCurrentState( new LoginState() );
        return true;
    }

    public void logout()
    {
     	setNextPage(UserState.PAGE_LOGIN);
        setCurrentState( new LogoutState() );
    }
}
