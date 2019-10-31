package it.davidechiarelli.istat_locations;

import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;

import io.sniffy.socket.DisableSockets;
import io.sniffy.test.junit.SniffyRule;
import it.davidechiarelli.istat_locations.exception.ISTATWebSiteUnreachableException;
import it.davidechiarelli.istat_locations.service.IAnagService;
import it.davidechiarelli.istat_locations.service.impl.AnagService;


public class NetworkIssuesTest{
	private static IAnagService anagService;	
	
    @Rule public SniffyRule sniffyRule = new SniffyRule();
    
    @Test
    @DisableSockets
    public void testUnsuccessfullLocationParsing() {
    	anagService = new AnagService();
    	try {
    		anagService.getRegions();
        } catch (ISTATWebSiteUnreachableException e) {
            assertNotNull(e);
        }
    	
    }
}
