package it.davidechiarelli.istat_locations.service;

import java.util.List;
import java.util.Map;


import it.davidechiarelli.istat_locations.model.LocationMapEnum;


public interface IAnagService {
	public Map<LocationMapEnum, List> getLocations();
}
