package org.woodwhales.mybatis.session;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.woodwhales.mybatis.config.Configuration;
import org.woodwhales.mybatis.config.MappedStatement;

/**
 * 1、加载配置信息(xml配置和数据库配置) 2、产生 sqlSession
 */
public class SqlSessionFactory {
	private Configuration config = new Configuration();

	public SqlSessionFactory() {
		loadDBInfo();
		loadMappersInfo();
	}

	public static final String MAPPER_CONFIG_LOCATION = "mappers";
	public static final String DB_CONFIG_FILE = "db.properties";

	private void loadMappersInfo() {
		File mappers = getFile(MAPPER_CONFIG_LOCATION);
		if(mappers.isDirectory()) {
			File[] listFiles = mappers.listFiles();
			
			for (File file : listFiles) {
				loadMapperInfo(file);
			}
		}
	}

	private void loadMapperInfo(File file) {
		SAXReader reader = new SAXReader();
		Document document = null;
		
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		Element root = document.getRootElement();
		String nameSpace = root.attribute("namespace").getData().toString();
		List<Element> selects = root.elements();
		
		for (Element element : selects) {
			String id = getElementAttributeDataByName(element, "id");
			String sourceId = nameSpace + "."  + id;
			String resultType = getElementAttributeDataByName(element, "resultType");
			String sql = element.getData().toString();
			
			MappedStatement mappedStatement = new MappedStatement(nameSpace, sourceId, resultType, sql);
			config.getMappedStatements().put(sourceId, mappedStatement);
		}
	}

	public String getElementAttributeDataByName(Element element, String name) {
		return element.attribute(name).getData().toString();
	}
	
	private void loadDBInfo() {
		Properties pops = getProperties(DB_CONFIG_FILE);
		config.setJdbcUrl(pops.get("jdbc.url").toString());
		config.setJdbcDriver(pops.get("jdbc.driver").toString());
		config.setJdbcUsername(pops.get("jdbc.username").toString());
		config.setJdbcPassword(pops.get("jdbc.password").toString());
	}

	public Properties getProperties(String name) {
		InputStream ins = SqlSessionFactory.class.getClassLoader().getResourceAsStream(name);
		Properties properties = new Properties();
		try {
			properties.load(ins);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	public File getFile(String name) {
		URL resource = SqlSessionFactory.class.getClassLoader().getResource(name);
		File file = new File(resource.getFile());
		return file;
	}
	
	public SqlSession openSqlSession() {
		return new DefaultSqlSession(config);
	}

}
