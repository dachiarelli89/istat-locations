package it.davidechiarelli.istat_locations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.datatype.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.davidechiarelli.istat_locations.util.ApplicationProperties;


public class ApplicationPropertiesNotFoundTest{

	/*@Before
	public void init() throws IOException {
		//ApplicationProperties.clear();
		//Files.copy(Paths.get("src/main/resources/application.properties"), Paths.get("src/main/resources/application.properties_bk"));
    	//Files.delete(Paths.get("src/main/resources/application.properties"));
	}

    @Test
    public void testPropFileNotFound() throws IOException, InterruptedException {  
    	ApplicationProperties.clear();
		Files.copy(Paths.get("src/main/resources/application.properties"), Paths.get("src/main/resources/application.properties_bk"));
		Files.delete(Paths.get("src/main/resources/application.properties"));
		//wait().atMost(5, java.time.Duration.).until(Files.delete(Paths.get("src/main/resources/application.properties")));
    	Thread.sleep(5000);
    	assertEquals("", ApplicationProperties.getInstance().getProperty("anag.url"));
    }
    
    @After
    public void restore() throws IOException {
    	Files.copy(Paths.get("src/main/resources/application.properties_bk"), Paths.get("src/main/resources/application.properties"));
    	Files.delete(Paths.get("src/main/resources/application.properties_bk"));
    }*/

}
