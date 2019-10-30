package it.davidechiarelli.istat_locations.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Region {
	@NonNull
	private String name;
	@NonNull
	private String code;
}
