package com.javapatterns.proxy.smartproxy;

public class Proxy implements Searcher 
{
    /**
     * @link aggregation
     * @directed 
     */
    private RealSearcher searcher;

    /**
     * @link aggregation
     * @directed 
     */
    private UsageLogger usageLogger;

    /**
     * @link aggregation
     * @directed 
     */
    private AccessValidator accessValidator;

    public Proxy()
    {
		searcher = new RealSearcher();
    }

    public String doSearch(String userId, String keyValue)
    {
        if (checkAccess(userId))
        {
	        String result = searcher.doSearch(null, keyValue);
    	    logUsage(userId);

	        return result;
        }
        else
        {
            return null;
        }
    }

    private boolean checkAccess(String userId)
    {
		accessValidator = new AccessValidator();

        return accessValidator.vaidateUser(userId);
    }

    private void logUsage(String userId)
    {
		UsageLogger logger = new UsageLogger();

        logger.setUserId(userId);

        logger.save();
    }

}
