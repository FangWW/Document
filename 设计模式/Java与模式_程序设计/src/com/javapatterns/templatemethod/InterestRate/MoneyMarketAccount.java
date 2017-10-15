package com.javapatterns.templatemethod.InterestRate;

public class MoneyMarketAccount extends Account 
{
    public String doCalculateAccountType()
    {
        return "Money Market";
    }

    public double doCalculateInterestRate()
    {
        return 0.045D;
    }
}
