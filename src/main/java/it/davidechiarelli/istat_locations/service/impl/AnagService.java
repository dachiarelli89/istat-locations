package it.davidechiarelli.istat_locations.service.impl;

import java.io.Reader;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import it.davidechiarelli.istat_locations.model.csv.ElencoComuniCSV;
import it.davidechiarelli.istat_locations.exception.ISTATWebSiteUnreachableException;
import it.davidechiarelli.istat_locations.model.City;
import it.davidechiarelli.istat_locations.model.GeograficZone;
import it.davidechiarelli.istat_locations.model.LocationMapEnum;
import it.davidechiarelli.istat_locations.model.Province;
import it.davidechiarelli.istat_locations.model.Region;
import it.davidechiarelli.istat_locations.service.IAnagService;
import it.davidechiarelli.istat_locations.util.FileManagementUtil;

/**
 * This class is the basic implementation for IAnagService, which contains methods to retrieve zones, regions, provinces
 * and cities plus some internal fuctions used for managing data.
 * 
 * @author Davide Chiarelli
 * @version 1.0.0
 *
 */
@SuppressWarnings("rawtypes")
public class AnagService implements IAnagService {
	private FileManagementUtil fmu = new FileManagementUtil();
	
	private Map<LocationMapEnum, List> locations;

	private static final Logger logger = LogManager.getLogger(AnagService.class);

	/**
	 * This private method is used to load data from ISTAT web site if they aren't already loaded in previous execution
	 */
	private void loadLocations() {
		if(locations == null) {
			locations = new EnumMap<>(LocationMapEnum.class);

			try {
				logger.info("Starting reading of geo anagraph.");
				List<ElencoComuniCSV> listCsvObj = getCsvObject();

				if(listCsvObj.isEmpty())
					throw new ISTATWebSiteUnreachableException();

				List<GeograficZone> listGeoZone = parseGeoZone(listCsvObj);
				logger.info("{} zones parsed", listGeoZone.size());
				locations.put(LocationMapEnum.ZONE, listGeoZone);


				listGeoZone.forEach(logger::debug);

				List<Region> listRegion = parseRegion(listCsvObj);
				logger.info("{} regions parsed", listRegion.size());
				locations.put(LocationMapEnum.REGION, listRegion);


				listRegion.forEach(logger::debug);

				List<Province> listProvince = parseProvince(listCsvObj, listRegion);					
				logger.info("{} provinces parsed", listProvince.size());
				locations.put(LocationMapEnum.PROVINCE, listProvince);

				listProvince.forEach(logger::debug);

				List<City> listCity = parseCity(listCsvObj, listProvince, listGeoZone);	
				logger.info("{} cities parsed", listCity.size());
				locations.put(LocationMapEnum.CITY, listCity);

				listCity.forEach(logger::debug);

			} catch (Exception e) {
				logger.error("Error during cities reading.");
				logger.error(e.toString());
			}
			logger.info("Finish reading geo anagraph.");
		}
	}

	/**
	 * This private method, using raw data from ISTAT, parses GeograficZone list
	 * 
	 * @param listCsvObj
	 * @return List<GeograficZone>
	 */
	private List<GeograficZone> parseGeoZone(List<ElencoComuniCSV> listCsvObj) {
		List<GeograficZone> listZones = new ArrayList<>();

		listCsvObj.forEach(item -> {
			if(listZones.isEmpty() ||
					(! listZones.stream().map(GeograficZone::getCode).collect(Collectors.toList())
							.contains(item.getCodiceRipartGeo()))) {
				listZones.add(new GeograficZone(item.getRipartizioneGeografica(), item.getCodiceRipartGeo()));
			}
		});

		return listZones;
	}

	/**
	 * This private method, using raw data from ISTAT, parses City list
	 * 
	 * @param listCsvObj
	 * @return List<City>
	 */
	private List<City> parseCity(List<ElencoComuniCSV> listCsvObj, List<Province> listProvince, List<GeograficZone> listGeoZone) {
		List<City> listCity = new ArrayList<>();

		listCsvObj.forEach(item -> {
			if(listCity.isEmpty() ||
					(! listCity.stream().map(City::getCityCode).collect(Collectors.toList())
							.contains(item.getCodiceComune()))) {
				listCity.add(new City(
						item.getDenominazione(), 
						item.getDenominazioneInter(), 
						item.getDenominazioneStraniera(),
						item.getCodiceComuneAlfanumerico(), 
						item.getIsCapoluogo().equals("1"),
						item.getCodiceComune(), 
						item.getCodiceCatastale(), 
						Integer.parseInt(item.getPopolazioneLegale().replace(".","").trim()), 
						item.getNuts1(), 
						item.getNuts2(), 
						item.getNuts3(),
						item.getProgressivoComune(),
						item.getCodiceComune110Prov(),
						item.getCodiceComune107Prov(),
						item.getCodiceComune103Prov(),
						listProvince.stream().filter(province -> province.getName().equalsIgnoreCase(item.getDenominazioneUnitaTerritoriale())).findFirst().get(),
						listGeoZone.stream().filter(geoZone -> geoZone.getCode().equals(item.getCodiceRipartGeo())).findFirst().get()
						)
						);
			}
		});

		return listCity;
	}

	/**
	 * This private method, using raw data from ISTAT, parses Province list
	 * 
	 * @param listCsvObj
	 * @return List<Province>
	 */
	private List<Province> parseProvince(List<ElencoComuniCSV> listCsvObj, List<Region> listRegion) {
		List<Province> listProvince = new ArrayList<>();

		listCsvObj.forEach(item -> {
			if(listProvince.isEmpty() || 
					(! listProvince.stream().map(Province::getName).map(String::toUpperCase).collect(Collectors.toList())
							.contains(item.getDenominazioneUnitaTerritoriale().toUpperCase()))) {
				listProvince.add(new Province(
						item.getDenominazioneUnitaTerritoriale(), 
						item.getCodiceUnitaTerritoriale(),
						item.getCodiceProcincia(),
						item.getSiglaAutomobilistica(), 
						listRegion.stream().filter(region -> region.getCode().equals(item.getCodiceRegione())).findFirst().get()
						)
						);
			}
		});

		return listProvince;
	}

	/**
	 * This private method, using raw data from ISTAT, parses Region list
	 * 
	 * @param listCsvObj
	 * @return List<Region>
	 */
	private List<Region> parseRegion(List<ElencoComuniCSV> listCsvObj) {
		List<Region> listRegion = new ArrayList<>();

		listCsvObj.forEach(item -> {
			if(listRegion.isEmpty() ||
					(! listRegion.stream().map(Region::getName).map(String::toUpperCase).collect(Collectors.toList())
							.contains(item.getDenominazioneRegione().toUpperCase()))) {
				listRegion.add(new Region(item.getDenominazioneRegione(), item.getCodiceRegione()));
			}
		});

		return listRegion;
	}


	/**
	 * This private method transform Reader object to a list of ElencoComuniCSV (a list of CSV rows)
	 * 
	 * @return List<ElencoComuniCSV>
	 */
	private List<ElencoComuniCSV> getCsvObject(){
		ColumnPositionMappingStrategy<ElencoComuniCSV> ms = new ColumnPositionMappingStrategy<>();
		ms.setType(ElencoComuniCSV.class);

		Reader anagReader=fmu.getAnagReader();
		List<ElencoComuniCSV> listCitiesCsv = new ArrayList<>();

		if(anagReader != null){
			CsvToBean<ElencoComuniCSV> cb = new CsvToBeanBuilder<ElencoComuniCSV>(fmu.getAnagReader())
					.withType(ElencoComuniCSV.class)
					.withMappingStrategy(ms)
					.withSkipLines(3)
					.withSeparator(';')
					.withIgnoreQuotations(true)
					.build();

			listCitiesCsv = cb.parse();
		}
		return listCitiesCsv;
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * This method load data from ISTAT site (if they aren't loaded yet) and returns a list of GeograficZone
	 * 
	 * @return List<GeograficZone>
	 */
	public List<GeograficZone> getZones() {
		loadLocations();
		return locations.get(LocationMapEnum.ZONE);
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * This method load data from ISTAT site (if they aren't loaded yet) and returns a list of Region
	 * 
	 * @return List<Region>
	 */
	public List<Region> getRegions() {
		loadLocations();
		return locations.get(LocationMapEnum.REGION);
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * This method load data from ISTAT site (if they aren't loaded yet) and returns a list of Province
	 * 
	 * @return List<Province>
	 */
	public List<Province> getProvinces() {
		loadLocations();
		return locations.get(LocationMapEnum.PROVINCE);
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * This method load data from ISTAT site (if they aren't loaded yet) and returns a list of City
	 * 
	 * @return List<City>
	 */
	public List<City> getCities() {
		loadLocations();
		return locations.get(LocationMapEnum.CITY);
	}
}
