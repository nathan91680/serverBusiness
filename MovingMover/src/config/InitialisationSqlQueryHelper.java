package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextListener;

import dao.exception.DAOConfigurationException;

public class InitialisationSqlQueryHelper implements ServletContextListener{
	
	
    private static final String FICHIER_PROPERTIES       = "/main/resources/queries.properties";
    
    public static Properties getProperties() {
    	
    	Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );

        if ( fichierProperties == null ) {
            throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        }

        try {
            properties.load( fichierProperties );
        } catch ( IOException e ) {
            throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
        }
        
        return properties;
    	
    }

}
