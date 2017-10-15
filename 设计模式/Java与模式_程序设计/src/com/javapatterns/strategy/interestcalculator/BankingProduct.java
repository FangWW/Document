package com.javapatterns.strategy.interestcalculator;

public class BankingProduct
{
    public BankingProduct() {
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public void calculateInterest() {
    }

    private String productName;

    /**
     * @link aggregation
     * @directed
     * @clientCardinality 1
     * @supplierCardinality 1 
     */
    private InterestCalculator interestCalculator;
}
