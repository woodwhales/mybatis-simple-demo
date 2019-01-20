package org.woodwhales.mybatis.util;

import org.junit.Assert;
import org.junit.Test;
import org.woodwhales.simple.entity.User;

public class ReflectionUtilTest {
	@Test
	public void testSetPropToBean() {
		User user = new User();
		ReflectionUtil.setPropToBean(user,"userName","test");
		System.out.println(user);
		Assert.assertEquals("test",user.getUserName());
	}
}
