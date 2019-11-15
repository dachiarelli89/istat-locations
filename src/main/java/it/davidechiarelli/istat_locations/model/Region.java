package it.davidechiarelli.istat_locations.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * This class rappresents an italian region model
 * 
 * @author Davide Chiarelli
 * @version 1.0.0
 *
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Region {
	@NonNull
	private String name;
	@NonNull
	private String code;
}
