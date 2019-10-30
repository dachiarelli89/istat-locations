package it.davidechiarelli.istat_locations.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApplicationProperties {
    private static Properties properties;
    private static ApplicationProperties instance;
    
    Logger logger = LogManager.getLogger(ApplicationProperties.class);
    
    

    private ApplicationProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
        	logger.error(e.getMessage(), e);
        }
    }
    
    public static ApplicationProperties getInstance() {
    	if(instance == null) {
    		instance = new ApplicationProperties();
    	}
    	return instance;
    }

    public String getProperty(String propName) {
        return properties.getProperty(propName);
    }
}