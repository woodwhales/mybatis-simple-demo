package org.woodwhales.simple.mapper;

import java.util.List;

import org.woodwhales.simple.entity.User;

public interface UserMapper {
	User selectByPrimaryKey(String id);

	List<User> selectAll();
}
