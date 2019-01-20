package org.woodwhales.mybatis.executor;

import java.util.List;

import org.woodwhales.mybatis.config.MappedStatement;

public interface Executor {

	<E> List<E> query(MappedStatement ms, Object parameter);
}
