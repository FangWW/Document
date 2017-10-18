 分页在任何系统中都是非常头疼的事情，有的数据库在语法上支持分页（比如MYSQL：select  * from xxx limit 0,5 表示从第1条记录开始显示5条记录)，而有的数据库则需要使用可滚动游标来实现，并且在不支持可滚动游标的系统上只能使用单向游标逐步接近要取得的数据。 
    而Hibernate提供了一个支持跨系统的分页机制，这样无论底层是什么样的数据库都能用统一的接口进行分页操作。 不用写Oracle专用的3层嵌套是一件多么幸运的事啊……

    举个例子

 比如

public List cutPage(String pageHql,int firstResult,int MaxResults)
{
  
  session = HibernateSessionFactory.currentSession();
  query = session.createQuery(pageHql);
  query.setFirstResult(firstResult);
  query.setMaxResults(MaxResults);
  return query.list();
}

    至于底层是怎么实现的完全不必去管，节省了很多时间，要是自己写SQL的话，如果是ORACLE，那3层嵌套要写死人。这样我要分页的时候就调用这个方法，传入HQL语句，再决定从第几条记录开始每页显示多少条。然后，再写个方法：

public int getCountPage(String pageHql,int MaxResults)
{
  int count = 0;
  
  session = HibernateSessionFactory.currentSession();
  query = session.createQuery(pageHql);
  count = ((Integer)query.iterate().next()).intValue();
  double countPage = count/(MaxResults*1.0);
  
  int countpage=(int)Math.ceil(countPage);
  System.out.println("总共有"+count+"条记录每页显示"
    +MaxResults+"条总共"+countpage+"页");
  return countpage;
  
}
调用这个方法传入HQL语句和每页显示的条数以获得总页数

    这样就可以在JSP和Action或Servlet之前取得和传递当前页数，每页显示条数以及总页数的值了
关于HQL中关于select count(*)的写法，参照"select count(*) from UserInfoPO in class com.fw.po.UserInfoPO"  其中UserInfoPO是一个数据库表的映射model类 放在包com.fw.po下面
需要注意的是countPage 是double 的，也就是带小数点的。比如，如果每页显示3条，总共有16条记录的话，那么应该有6页，最后一页显示1条记录。也就是从记录条数除以每页显示条数如16/3=5余1，可以判断对16/3取模，大于0的话就让获得的类型为整型的总页数+1。而参照上面的方法16/3的结果是一个double型，因此就用Math.ceil（）方法将结果向上变为整数。 

 




package org.crazyit.common.hibernate3.support;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.HibernateException;
import java.sql.SQLException;
import java.util.List;

/**
 * Description: <br/>
 * Copyright (C), 2001-2012, Yeeku.H.Lee <br/>
 * This program is protected by copyright laws. <br/>
 * Program Name: <br/>
 * Date:
 * 
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class YeekuHibernateDaoSupport extends HibernateDaoSupport {
	/**
	 * 使用hql语句进行分页查询
	 * 
	 * @param hql
	 *            需要查询的hql语句
	 * @param offset=(curr-1)*pageSize+1
	 *            第一条记录索引
	 * @param pageSize
	 *            每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	public List findByPage(final String hql, final int offset,
			final int pageSize) {
		// 通过一个HibernateCallback对象来执行查询
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			// 实现HibernateCallback接口必须实现的方法
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// 执行Hibernate分页查询
				List result = session.createQuery(hql).setFirstResult(offset)
						.setMaxResults(pageSize).list();
				return result;
			}
		});
		return list;
	}

	/**
	 * 使用hql语句进行分页查询
	 * 
	 * @param hql
	 *            需要查询的hql语句
	 * @param value
	 *            如果hql有一个参数需要传入，value就是传入hql语句的参数
	 * @param offset=(curr-1)*pageSize+1
	 *            第一条记录索引
	 * @param pageSize
	 *            每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	public List findByPage(final String hql, final Object value,
			final int offset, final int pageSize) {
		// 通过一个HibernateCallback对象来执行查询
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			// 实现HibernateCallback接口必须实现的方法
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// 执行Hibernate分页查询
				List result = session.createQuery(hql)
						// 为hql语句传入参数
						.setParameter(0, value).setFirstResult(offset)
						.setMaxResults(pageSize).list();
				return result;
			}
		});
		return list;
	}

	/**
	 * 使用hql语句进行分页查询
	 * 
	 * @param hql
	 *            需要查询的hql语句
	 * @param values
	 *            如果hql有多个个参数需要传入，values就是传入hql的参数数组
	 * @param offset=(curr-1)*pageSize+1
	 *            第一条记录索引
	 * @param pageSize
	 *            每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	public List findByPage(final String hql, final Object[] values,
			final int offset, final int pageSize) {
		// 通过一个HibernateCallback对象来执行查询
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			// 实现HibernateCallback接口必须实现的方法
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// 执行Hibernate分页查询
				Query query = session.createQuery(hql);
				// 为hql语句传入参数
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
				List result = query.setFirstResult(offset)
						.setMaxResults(pageSize).list();
				return result;
			}
		});
		return list;
	}
}
