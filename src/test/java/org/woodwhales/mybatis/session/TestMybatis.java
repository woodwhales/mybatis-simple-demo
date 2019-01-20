package org.woodwhales.mybatis.session;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.woodwhales.simple.entity.User;
import org.woodwhales.simple.mapper.UserMapper;

public class TestMybatis {
	
	@Test
	public void testSession() {
		SqlSessionFactory factory = new SqlSessionFactory();
		SqlSession session = factory.openSqlSession();
		System.out.println(session.toString());
	}
	
	@Test
	public void testMapper() {
		SqlSessionFactory factory = new SqlSessionFactory();
		SqlSession session = factory.openSqlSession();
		
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = mapper.selectByPrimaryKey("1");
		List<User> userList = mapper.selectAll();
		
	}
	
	@Test
	public void testSelectByPrimaryKey() {
		SqlSessionFactory factory = new SqlSessionFactory();
		SqlSession session = factory.openSqlSession();
		
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = mapper.selectByPrimaryKey("1");
		System.out.println(user);
		Assert.assertEquals("1", user.getId());
	}
	
	@Test
	public void testSelectAll() {
		SqlSessionFactory factory = new SqlSessionFactory();
		SqlSession session = factory.openSqlSession();
		
		UserMapper mapper = session.getMapper(UserMapper.class);
		List<User> list = mapper.selectAll();
		for (User user : list) {
			System.out.println(user);
		}
	}
}
