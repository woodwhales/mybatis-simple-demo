package org.woodwhales.mybatis.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MappedStatement {
	private String nameSpace;
	private String sourceId;
	private String resultType;
	private String sql;
	
}
