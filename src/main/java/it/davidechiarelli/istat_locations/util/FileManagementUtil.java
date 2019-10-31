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
	
	public String getAnagFileContent(String url) {
		URL urlObject;
		try {
			urlObject = new URL(url);
			URLConnection urlConnection = urlObject.openConnection();
			InputStream inputStream = urlConnection.getInputStream();
			return readFromInputStream(inputStream);
		} catch (IOException e) {
			logger.error("Error reading data from URL.", e);
		}
		return null;
	}

	public String getAnagFileContent() {
		return getAnagFileContent(CONFIG_URL);
	}
	
	public Reader getAnagFileBuffer() {
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

	private String readFromInputStream(InputStream inputStream)
			throws IOException {
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br
				= new BufferedReader(new InputStreamReader(inputStream, "utf8"))) {
			String line;
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line).append("\n");
			}
		}
		return resultStringBuilder.toString();
	}
}
