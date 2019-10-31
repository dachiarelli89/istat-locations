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

public class FileManagementUtil {
	private static final String CONFIG_URL="https://www.istat.it/storage/codici-unita-amministrative/Elenco-comuni-italiani.csv";
	
	Logger logger = LogManager.getLogger(FileManagementUtil.class);
	
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
