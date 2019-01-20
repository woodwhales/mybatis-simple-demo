package org.woodwhales.mybatis.bind;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;

import org.woodwhales.mybatis.session.SqlSession;

public class MappedProxy implements InvocationHandler {

	private SqlSession session;

	public MappedProxy(SqlSession session) {
		super();
		this.session = session;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 获取方法的返回类型
		Class<?> returnType = method.getReturnType();

		// 判断方法的返回类型是不是集合, 是集合则表示查询所有
		if (Collection.class.isAssignableFrom(returnType)) {
			return session.selectList(method.getDeclaringClass().getName() + "." + method.getName(),
					args == null ? null : args[0]);
		} else {
			return session.selectOne(method.getDeclaringClass().getName() + "." + method.getName(),
					args == null ? null : args[0]);
		}
	}

}
