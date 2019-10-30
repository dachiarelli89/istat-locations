package it.davidechiarelli.istat_locations.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "region")
public class Province {
	@NonNull
	private String name;
	@NonNull
	private String carCode;
	@NonNull
	private Region region;
}
