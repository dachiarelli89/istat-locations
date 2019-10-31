package it.davidechiarelli.istat_locations;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.sniffy.socket.DisableSockets;
import io.sniffy.test.junit.SniffyRule;
import it.davidechiarelli.istat_locations.exception.ISTATWebSiteUnreachableException;
import it.davidechiarelli.istat_locations.model.LocationMapEnum;
import it.davidechiarelli.istat_locations.service.IAnagService;
import it.davidechiarelli.istat_locations.service.impl.AnagService;
import it.davidechiarelli.istat_locations.util.FileManagementUtil;


public class AppTest{
	
	@Test
    public void testSuccessLocationParsing() {
    	IAnagService anagService = new AnagService();
    	Map<LocationMapEnum, List> locations = anagService.getLocations();
    	assertNotNull(locations);
		assertTrue(locations.get(LocationMapEnum.CITY).size()>6000);
		assertTrue(locations.get(LocationMapEnum.PROVINCE).size()>90);
		assertTrue(locations.get(LocationMapEnum.REGION).size()==20);
    }
    
    @Rule public SniffyRule sniffyRule = new SniffyRule();
    
    @Test
    @DisableSockets
    public void testUnsuccessfullLocationParsing() {
    	IAnagService anagService = new AnagService();
    	try {
    		anagService.getLocations();
        } catch (ISTATWebSiteUnreachableException e) {
            assertNotNull(e);
        }
    	
    }
}
