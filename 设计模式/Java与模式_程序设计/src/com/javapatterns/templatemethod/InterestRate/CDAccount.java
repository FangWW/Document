package com.javapatterns.templatemethod.InterestRate;

public class CDAccount extends Account 
{
    public String doCalculateAccountType()
    {
        return "Certificate of Deposite";
    }

    public double doCalculateInterestRate()
    {
        return 0.065D;
    }
}
