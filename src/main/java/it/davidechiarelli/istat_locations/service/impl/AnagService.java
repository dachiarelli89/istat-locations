package it.davidechiarelli.istat_locations.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import it.davidechiarelli.istat_locations.csv.ElencoComuniCSV;
import it.davidechiarelli.istat_locations.model.City;
import it.davidechiarelli.istat_locations.model.LocationMapEnum;
import it.davidechiarelli.istat_locations.model.Province;
import it.davidechiarelli.istat_locations.model.Region;
import it.davidechiarelli.istat_locations.service.IAnagService;
import it.davidechiarelli.istat_locations.util.FileManagementUtil;


public class AnagService implements IAnagService {
	private FileManagementUtil fmu = new FileManagementUtil();
	
	private static final Logger logger = LogManager.getLogger(AnagService.class);

	@Override
	public Map<LocationMapEnum, List> getLocations() {
		
		Map<LocationMapEnum, List> locations = new HashMap<LocationMapEnum, List>();
			
		try {
			logger.info("Starting reading of geo anagraph.");
			List<ElencoComuniCSV> listCsvObj = getCsvObject();
			
			List<Region> listRegion = parseRegion(listCsvObj);
			logger.info(listRegion.size()+" regions parsed");
			locations.put(LocationMapEnum.REGION, listRegion);
			
			
			if(logger.isDebugEnabled())
				listRegion.forEach(region -> logger.debug(region));
			
			List<Province> listProvince = parseProvince(listCsvObj, listRegion);					
			logger.info(listProvince.size()+" provinces parsed");
			locations.put(LocationMapEnum.PROVINCE, listProvince);
			
			if(logger.isDebugEnabled())
				listProvince.forEach(province -> logger.debug(province));
			
			List<City> listCity = parseCity(listCsvObj, listProvince);	
			logger.info(listCity.size()+" cities parsed");
			locations.put(LocationMapEnum.CITY, listCity);
			
			if(logger.isDebugEnabled())
				listCity.forEach(city -> logger.debug(city));

		} catch (Exception e) {
			logger.error("Error during cities reading.");
			logger.error(e.toString());
			return null;
		}
		logger.info("Finish reading geo anagraph.");
		return locations;
	}

	private List<City> parseCity(List<ElencoComuniCSV> listCsvObj, List<Province> listProvince) {
		List<City> listCity = new ArrayList<City>();
		
		listCsvObj.forEach(item -> {
			if(listCity.size()==0) {
				listCity.add(new City(
						item.getDenominazione(), 
						item.getDenominazioneInter(), 
						item.getCodiceComuneAlfanumerico(), 
						item.getIsCapoluogo().equals("1") ? true : false,
						item.getCodiceComune(), 
						item.getCodiceCatastale(), 
						Integer.parseInt(item.getPopolazioneLegale().replace(".","").trim()), 
						item.getNuts1(), 
						item.getNuts2(), 
						item.getNuts3(),
						listProvince.stream().filter(province -> province.getName().toUpperCase().equals(item.getDenominazioneUnitaTerritoriale().toUpperCase())).findFirst().get()
						)
					);
			}
			else {
				if(! listCity.stream().map(cityName -> cityName.getName()).map(String::toUpperCase).collect(Collectors.toList())
					.contains(item.getDenominazione().toUpperCase())) {
					listCity.add(new City(
							item.getDenominazione(), 
							item.getDenominazioneInter(), 
							item.getCodiceComuneAlfanumerico(), 
							item.getIsCapoluogo().equals("1") ? true : false, 
							item.getCodiceComune(), 
							item.getCodiceCatastale(), 
							Integer.parseInt(item.getPopolazioneLegale().replace(".","").trim()), 
							item.getNuts1(), 
							item.getNuts2(), 
							item.getNuts3(),
							listProvince.stream().filter(province -> province.getName().toUpperCase().equals(item.getDenominazioneUnitaTerritoriale().toUpperCase())).findFirst().get()
							)
						);
				}
			}
		});
		
		return listCity;
	}

	private List<Province> parseProvince(List<ElencoComuniCSV> listCsvObj, List<Region> listRegion) {
		List<Province> listProvince = new ArrayList<Province>();
		
		listCsvObj.forEach(item -> {
			if(listProvince.size()==0) {
				listProvince.add(new Province(
						item.getDenominazioneUnitaTerritoriale(), 
						item.getSiglaAutomobilistica(), 
						listRegion.stream().filter(region -> region.getCode().equals(item.getCodiceRegione())).findFirst().get()
						)
					);
			}
			else {
				if(! listProvince.stream().map(provinceName -> provinceName.getName()).map(String::toUpperCase).collect(Collectors.toList())
					.contains(item.getDenominazioneUnitaTerritoriale().toUpperCase())) {
					listProvince.add(new Province(item.getDenominazioneUnitaTerritoriale(), 
							item.getSiglaAutomobilistica(), 
							listRegion.stream().filter(region -> region.getCode().equals(item.getCodiceRegione())).findFirst().get()
							)
						);
				}
			}
		});
		
		return listProvince;
	}

	private List<Region> parseRegion(List<ElencoComuniCSV> listCsvObj) {
		List<Region> listRegion = new ArrayList<Region>();
		
		listCsvObj.forEach(item -> {
			if(listRegion.size()==0) {
				listRegion.add(new Region(item.getDenominazioneRegione(), item.getCodiceRegione()));
			}
			else {
				if(! listRegion.stream().map(regionName -> regionName.getName()).map(String::toUpperCase).collect(Collectors.toList())
					.contains(item.getDenominazioneRegione().toUpperCase())) {
					listRegion.add(new Region(item.getDenominazioneRegione(), item.getCodiceRegione()));
				}
			}
		});
		
		return listRegion;
	}


	private List<ElencoComuniCSV> getCsvObject(){
		ColumnPositionMappingStrategy<ElencoComuniCSV> ms = new ColumnPositionMappingStrategy<ElencoComuniCSV>();
		ms.setType(ElencoComuniCSV.class);

		CsvToBean<ElencoComuniCSV> cb = new CsvToBeanBuilder<ElencoComuniCSV>(fmu.getAnagFileBuffer())
				.withType(ElencoComuniCSV.class)
				.withMappingStrategy(ms)
				.withSkipLines(3)
				.withSeparator(';')
				.withIgnoreQuotations(true)
				.build();
		
		return cb.parse();
	}

}
