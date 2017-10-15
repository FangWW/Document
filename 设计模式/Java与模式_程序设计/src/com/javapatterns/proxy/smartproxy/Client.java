package com.javapatterns.proxy.smartproxy;

public class Client
{
    /**
     * @link aggregation
     * @directed 
     */
    private static Searcher searcher;

    public static void main(String[] args)
    {
		searcher = new Proxy();
		String userId = "Admin";
        String searchType = "SEARCH_BY_ACCOUNT_NUMBER";

        String result = searcher.doSearch(userId, searchType);

        System.out.println(result);
    }

}

