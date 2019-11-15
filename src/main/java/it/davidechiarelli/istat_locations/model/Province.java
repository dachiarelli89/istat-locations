package it.davidechiarelli.istat_locations.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * This class rappresents an italian province model
 * 
 * @author Davide Chiarelli
 * @version 1.0.0
 *
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "region")
public class Province {
	@NonNull
	private String name;
	@NonNull
	private String provinceCode;
	@NonNull
	private String oldProvinceCode;
	@NonNull
	private String carCode;
	@NonNull
	private Region region;
}
