package it.davidechiarelli.istat_locations.service;

import java.util.List;

import it.davidechiarelli.istat_locations.model.City;
import it.davidechiarelli.istat_locations.model.GeograficZone;
import it.davidechiarelli.istat_locations.model.Province;
import it.davidechiarelli.istat_locations.model.Region;

/**
 * This interface exposes method to retrieve Zones, Regions, Provinces or Cities from ISTAT web site
 * 
 * @author Davide Chiarelli
 * @version 1.0.0
 *
 */
public interface IAnagService {
	/**
	 * This method returns a list of geographic zones
	 * 
	 * @return List
	 */
	public List<GeograficZone> getZones();
	/**
	 * This method returns a list of regions
	 * 
	 * @return List
	 */
	public List<Region> getRegions();
	/**
	 * This method returns a list of provinces
	 * 
	 * @return List
	 */
	public List<Province> getProvinces();
	/**
	 * This method returns a list of cities
	 * 
	 * @return List
	 */
	public List<City> getCities();
}
