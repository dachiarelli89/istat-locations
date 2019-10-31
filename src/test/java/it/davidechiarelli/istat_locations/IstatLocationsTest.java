package it.davidechiarelli.istat_locations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import it.davidechiarelli.istat_locations.model.City;
import it.davidechiarelli.istat_locations.model.GeograficZone;
import it.davidechiarelli.istat_locations.model.Province;
import it.davidechiarelli.istat_locations.model.Region;
import it.davidechiarelli.istat_locations.service.IAnagService;
import it.davidechiarelli.istat_locations.service.impl.AnagService;


public class IstatLocationsTest{
	private static IAnagService anagService;	
	
	@BeforeClass
	public static void init() {
		anagService = new AnagService();
	}
	
	@Test
    public void testSuccessZoneParsing() {	
    	List<GeograficZone> zones = anagService.getZones();
    	assertNotNull(zones);
		assertEquals(5, zones.size());
    }
	
	@Test
    public void testSuccessProvinceParsing() {		
    	List<Province> provinces = anagService.getProvinces();
    	assertNotNull(provinces);
		assertTrue(provinces.size()==107);
    }
	
	@Test
    public void testSuccessRegionParsing() {	
    	List<Region> regions = anagService.getRegions();
    	assertNotNull(regions);
		assertEquals(20, regions.size());
    }
	
	@Test
    public void testSuccesCityParsing() {	
    	List<City> cities = anagService.getCities();
    	assertNotNull(cities);
		assertTrue(cities.size()==7908);
    }
}
