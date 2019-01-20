package org.woodwhales.mybatis.config;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Configuration {

	private String jdbcUrl;
	private String jdbcDriver;
	private String jdbcUsername;
	private String jdbcPassword;
	private Map<String, MappedStatement> mappedStatements = new HashMap<String, MappedStatement>();
}
