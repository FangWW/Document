package com.javapatterns.multilingual.number;

import java.text.NumberFormat;
import java.util.Locale;

public class PercentFormatTester
{
    static public void displayPercent(Double amount, Locale currentLocale)
    {

       NumberFormat formatter;
       String amountOut;

       formatter = NumberFormat.getPercentInstance(currentLocale);
       amountOut = formatter.format(amount);

       System.out.println(amountOut + "   " + currentLocale.toString());
    }

    static public void main(String[] args)
    {
       displayPercent(new Double(4567.89), new Locale("en", "US"));
       displayPercent(new Double(4567.89), new Locale("de", "DE"));
       displayPercent(new Double(4567.89), new Locale("fr", "FR"));
    }
}

