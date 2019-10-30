package it.davidechiarelli.istat_locations.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "province")
public class City {
	@NonNull
	private String name;
	@NonNull
	private String internationalName;
	@NonNull
	private String foreignName;
	@NonNull
	private String alfanumericalCode;
	@NonNull
	private Boolean isRegionCapital;
	@NonNull
	private String cityCode;
	@NonNull
	private String catastalCode;
	@NonNull
	private Integer population;
	@NonNull
	private String nuts1;
	@NonNull
	private String nuts2;
	@NonNull
	private String nuts3;
	@NonNull
	private String progressiveNumber;
	@NonNull
	private String cityCode110Prov;
	@NonNull
	private String cityCode107Prov;
	@NonNull
	private String cityCode103Prov;
	
	
    @NonNull
	private Province province;
    
    @NonNull
	private GeograficZone geograficZone;
}
