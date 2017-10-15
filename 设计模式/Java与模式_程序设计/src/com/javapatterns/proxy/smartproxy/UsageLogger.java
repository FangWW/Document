package com.javapatterns.proxy.smartproxy;

public class UsageLogger
{
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public void save()
    {
        String sql = "INSERT INTO USAGE_TABLE (user_id) " +
            " VALUES(" + userId + ")";

        //execute this SQL statement
    }

    public void save(String userId)
    {
        this.userId = userId;

        save();
    }

    private String userId;
}

