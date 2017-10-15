package com.javapatterns.singleton.demos;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
* Only once instance of the class may be created during the
* execution of any given program. Instances of this class should
* be aquired through the getInstance() method. Notice that there
* are no public constructors for this class.
*/
public class ConfigManager
{
   /**
	* The private constructor (enforces single instance)
	*/
	private ConfigManager()
	{
		m_file = new File(PFILE);
		m_lastModifiedTime = m_file.lastModified();

		if(m_lastModifiedTime == 0)
		{
            System.err.println(PFILE + " file does not exist!");
        }

		m_props = new Properties();

		try
		{
			m_props.load(new FileInputStream(PFILE));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	* Returns the singleton ConfigManager
	*
	* @return The one and only instance of the ConfigManager
	*/
	synchronized public static ConfigManager getInstance()
	{
		return m_instance;
	}

	/**
	* Gets a configuration item
	*
	* @param name The name of the item
	* @param defaultVal The default value if name is not found
	* @return The value for the specified name
	*/
	final public Object getConfigItem(String name, Object defaultVal)
	{
		long newTime = m_file.lastModified();

		// Check to see if configuration file has been modified
		// since the previous request. If so, then read in the new
		// contents
		if(newTime == 0)
		{
			// The props file was deleted or does not exist (!!)
			if(m_lastModifiedTime == 0)
            {
				System.err.println(PFILE + " file does not exist!");
            }
			else
            {
				System.err.println(PFILE + " file was deleted!!");
            }
			return defaultVal;
		}
		else if(newTime > m_lastModifiedTime)
		{
			m_props.clear();	// Get rid of the old properties
			try
			{
				m_props.load(new FileInputStream(PFILE));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		m_lastModifiedTime = newTime;

		Object val = m_props.getProperty(name);
		if( val == null )
        {
			return defaultVal;
        }
		else
        {
			return val;
        }
	}

	/**
	* The fully qualified name of the properties file
	*/
	private static final String PFILE = System.getProperty("user.dir") +  "/Singleton.properties";

	/**
	* The File object corresponding to the file that contains the properties
	*/
	private File m_file = null;

	/**
	* The last modified time of the properties file
	*/
	private long m_lastModifiedTime = 0;

	/**
	* The cached properties
	*/
	private Properties m_props = null;

	/**
	* The only instance of this class
	* @label Creates
	*/
	private static ConfigManager m_instance = new ConfigManager();

}
