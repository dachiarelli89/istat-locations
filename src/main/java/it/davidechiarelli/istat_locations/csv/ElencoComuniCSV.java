package it.davidechiarelli.istat_locations.csv;

import com.opencsv.bean.CsvBindByPosition;

import lombok.Data;

@Data
public class ElencoComuniCSV {
	@CsvBindByPosition(position = 0)
	private String codiceRegione;
	@CsvBindByPosition(position = 1)
	private String codiceUnitaTerritoriale;
	@CsvBindByPosition(position = 2)
	private String codiceProcincia;
	@CsvBindByPosition(position = 3)
	private String progressivoComune;
	@CsvBindByPosition(position = 4)
	private String codiceComuneAlfanumerico;
	@CsvBindByPosition(position = 5)
	private String denominazioneInter;
	@CsvBindByPosition(position = 6)
	private String denominazione;
	@CsvBindByPosition(position = 7)
	private String denominazioneStraniera;
	@CsvBindByPosition(position = 8)
	private String codiceRipartGeo;
	@CsvBindByPosition(position = 9)
	private String ripartizioneGeografica;
	@CsvBindByPosition(position = 10)
	private String denominazioneRegione;
	@CsvBindByPosition(position = 11)
	private String denominazioneUnitaTerritoriale;
	@CsvBindByPosition(position = 12)
	private String isCapoluogo;
	@CsvBindByPosition(position = 13)
	private String siglaAutomobilistica;
	@CsvBindByPosition(position = 14)
	private String codiceComune;
	@CsvBindByPosition(position = 15)
	private String codiceComune110Prov;
	@CsvBindByPosition(position = 16)
	private String codiceComune107Prov;
	@CsvBindByPosition(position = 17)
	private String codiceComune103Prov;
	@CsvBindByPosition(position = 18)
	private String codiceCatastale;
	@CsvBindByPosition(position = 19)
	private String popolazioneLegale;
	@CsvBindByPosition(position = 20)
	private String nuts1;
	@CsvBindByPosition(position = 21)
	private String nuts2;
	@CsvBindByPosition(position = 22)
	private String nuts3;
}
