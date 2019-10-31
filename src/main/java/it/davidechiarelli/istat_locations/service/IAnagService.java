package it.davidechiarelli.istat_locations.service;

import java.util.List;

import it.davidechiarelli.istat_locations.model.City;
import it.davidechiarelli.istat_locations.model.GeograficZone;
import it.davidechiarelli.istat_locations.model.Province;
import it.davidechiarelli.istat_locations.model.Region;


public interface IAnagService {
	public List<GeograficZone> getZones();
	public List<Region> getRegions();
	public List<Province> getProvinces();
	public List<City> getCities();
}
