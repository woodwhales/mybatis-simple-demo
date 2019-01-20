package org.woodwhales.mybatis.session;

import java.util.List;

public interface SqlSession {
	<T> T selectOne(String statement, Object parameter);
	
	<F> List<F> selectList(String statement, Object parameter);
	
	<T> T getMapper(Class<T> type);
}
