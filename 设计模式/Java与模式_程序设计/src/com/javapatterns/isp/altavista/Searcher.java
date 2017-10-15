package com.javapatterns.isp.altavista;

public interface Searcher
{
    void search(String[] keywords);

    void getResultset();

    /**
     * @link aggregation
     * @directed 
     */
    /*#Resultset lnkResultset;*/
}
