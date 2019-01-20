package org.woodwhales.mybatis.executor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.woodwhales.mybatis.config.Configuration;
import org.woodwhales.mybatis.config.MappedStatement;
import org.woodwhales.mybatis.util.ReflectionUtil;

public class DefaultExecutor implements Executor {
	private Configuration conf;

	public DefaultExecutor(Configuration configuration) {
		this.conf = configuration;
	}

	public <E> List<E> query(MappedStatement ms, Object parameter) {
		List<E> result = new ArrayList<E>();
		
		try {
			Class.forName(conf.getJdbcDriver());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			conn = DriverManager.getConnection(conf.getJdbcUrl(), conf.getJdbcUsername(), conf.getJdbcPassword());
			preparedStatement = conn.prepareStatement(ms.getSql());
			parameterize(preparedStatement, parameter);
			resultSet = preparedStatement.executeQuery();
			
			handlerResultSet(resultSet, result, ms.getResultType());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	private <E> void handlerResultSet(ResultSet resultSet, List<E> result, String className) {
		Class<E> clazz = null;
		try {
			clazz = (Class<E>)clazz.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			while(resultSet.next()) {
				Object entity = clazz.newInstance();
				ReflectionUtil.setPropToBeanFromResultSet(entity, resultSet);
				result.add((E) entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void parameterize(PreparedStatement preparedStatement, Object parameter) throws SQLException {
		if(parameter instanceof Integer) {
			preparedStatement.setInt(1, (Integer)parameter);
		} else if(parameter instanceof Long) {
			preparedStatement.setLong(1, (Long)parameter);
		} else if(parameter instanceof String) {
			preparedStatement.setString(1, (String)parameter);
		}
	}
	

}
