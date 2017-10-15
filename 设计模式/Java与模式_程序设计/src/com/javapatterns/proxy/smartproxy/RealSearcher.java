package com.javapatterns.proxy.smartproxy;

class RealSearcher implements Searcher
{
    public RealSearcher()
    {
    }

    public String doSearch(String userId, String keyValue)
    {
        String sql = "SELECT * FROM data_table WHERE key_col = '" + keyValue + "'";

        //execute this SQL Statement and concatenate a result string
        return "result set";
    }
}
