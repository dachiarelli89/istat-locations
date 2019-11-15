package it.davidechiarelli.istat_locations.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This support class is used to load raw data from ISTAT site and returns a reader of entities
 * 
 * @author Davide Chiarelli
 * @version 1.0.0
 *
 */
public class FileManagementUtil {
	private static final String CONFIG_URL="https://www.istat.it/storage/codici-unita-amministrative/Elenco-comuni-italiani.csv";
	
	Logger logger = LogManager.getLogger(FileManagementUtil.class);
	
	/**
	 * This method open a stream with ISTAT web site and returns a Java reader
	 * 
	 * @return Reader: A reader of entities from ISTAT site
	 */
	public Reader getAnagReader() {
		URL urlObject;
		try {
			urlObject = new URL(CONFIG_URL);
			URLConnection urlConnection = urlObject.openConnection();
			InputStream inputStream = urlConnection.getInputStream();
			return  new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
		} catch (IOException e) {
			logger.error("Error acquiring data from URL.", e);
		} 
		return null;
	}
}
