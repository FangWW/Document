package com.javapatterns.proxy.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Vector;

public class VectorProxy implements InvocationHandler
{
    private Object proxyobj;

    /** @link dependency */
    /*#Proxy lnkProxy;*/

    public VectorProxy(Object obj)
    {
        proxyobj = obj;
    }

	public static Object factory(Object obj)
    {
		Class cls = obj.getClass();

        return Proxy.newProxyInstance( cls.getClassLoader(),
            cls.getInterfaces(),
            new VectorProxy(obj) );
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
		System.out.println("before calling " + method);

        if (args != null)
        {
			for (int i=0; i<args.length; i++)
            {
                System.out.println(args[i] + "");
            }
		}

        Object o = method.invoke(proxyobj, args);

		System.out.println("after calling " + method);

        return o;
    }

    public static void main(String[] args)
    {
		List v = null;

        v = (List) factory(new Vector(10));

        v.add("New");
        v.add("York");
    }
}