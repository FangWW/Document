package com.javapatterns.dip;

public class Account
{
    public Account(AccountType acctType)
    {
        //write your code here
    }

    public void deposit(float amt)
    {
        //write your code here
    }

    /**
     * @link aggregation
     * @directed 
     */
    private AccountType accountType;

    /**
     * @link aggregation
     * @directed 
     */
    private AccountStatus accountStatus;
}
