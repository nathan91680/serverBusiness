package utils;

import java.util.Properties;

import config.InitialisationSqlQueryHelper;

public class QueryHelper {
	
	static private Properties properties = InitialisationSqlQueryHelper.getProperties();
	
	public static String loadResourceToString(final String path){
		return properties.getProperty( path );
	}

}
