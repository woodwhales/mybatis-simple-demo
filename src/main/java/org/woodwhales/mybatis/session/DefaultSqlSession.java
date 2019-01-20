package org.woodwhales.mybatis.session;

import java.lang.reflect.Proxy;
import java.util.List;

import org.woodwhales.mybatis.bind.MappedProxy;
import org.woodwhales.mybatis.config.Configuration;
import org.woodwhales.mybatis.config.MappedStatement;
import org.woodwhales.mybatis.executor.DefaultExecutor;
import org.woodwhales.mybatis.executor.Executor;

import lombok.Data;

@Data
public class DefaultSqlSession implements SqlSession {

	private Configuration configuration;
	
	private Executor executor;
	
	public DefaultSqlSession(Configuration configuration) {
		this.configuration = configuration;
		this.executor = new DefaultExecutor(configuration);
	}

	public <T> T selectOne(String statement, Object parameter) {
		List<Object> selectList = this.selectList(statement, parameter);
		if(selectList == null || selectList.isEmpty()) {
			return null;
		}
		
		if(selectList.size() == 1) {
			return (T) selectList.get(0);
		} else {
			throw new RuntimeException("too many result!");
		}
	}

	public <F> List<F> selectList(String MappedStatement, Object parameter) {
		MappedStatement ms = configuration.getMappedStatements().get(MappedStatement);
		return executor.query(ms, parameter);
	}

	/**
	 * 核心技术
	 */
	public <T> T getMapper(Class<T> type) {
		MappedProxy mp = new MappedProxy(this);
		return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[] {type}, mp);
	}

}
