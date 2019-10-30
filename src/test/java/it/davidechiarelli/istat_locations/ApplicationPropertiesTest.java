package it.davidechiarelli.istat_locations;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.davidechiarelli.istat_locations.util.ApplicationProperties;


public class ApplicationPropertiesTest{
    @Test
    public void testSuccessGetProp() {
    	
    	String url = ApplicationProperties.getInstance().getProperty("anag.url");
    	
    	assertNotNull(url);
		assertTrue(url.length()>0);
    }
    
    @Test
    public void testUnsuccessGetProp() {
    	
    	String url = ApplicationProperties.getInstance().getProperty("no.url");
    	
    	assertNull(url);
    }
}
