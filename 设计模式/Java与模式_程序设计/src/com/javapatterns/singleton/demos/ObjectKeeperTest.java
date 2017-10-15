package com.javapatterns.singleton.demos;

// Referenced classes of package singleton.demos:
//            ConfigManager, ObjectKeeper

public class ObjectKeeperTest
{

    public static void main(String[] args)
    {
        try
        {
            ConfigManager man = ConfigManager.getInstance();

            ObjectKeeper.keepObject(man);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Type quit to quit");
            do
            {
                System.out.print("Property item to read: ");
                String line = reader.readLine();
                if(line.equals("quit"))
                {
                    break;
                }
                System.out.println(ConfigManager.getInstance().getConfigItem(line, "Not found."));
            } while(true);

            ObjectKeeper.discardObject(man);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
