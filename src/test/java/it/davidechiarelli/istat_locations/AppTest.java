package it.davidechiarelli.istat_locations;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.davidechiarelli.istat_locations.model.LocationMapEnum;
import it.davidechiarelli.istat_locations.service.IAnagService;
import it.davidechiarelli.istat_locations.service.impl.AnagService;


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
}
