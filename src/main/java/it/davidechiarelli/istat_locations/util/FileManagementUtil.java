package it.davidechiarelli.istat_locations.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileManagementUtil {
	private final String configUrl=ApplicationProperties.getInstance().getProperty("anag.url");
	
	Logger logger = LogManager.getLogger(FileManagementUtil.class);
	
	public String getAnagFileContent(String url) {
		URL urlObject;
		try {
			urlObject = new URL(url);
			URLConnection urlConnection = urlObject.openConnection();
			InputStream inputStream = urlConnection.getInputStream();
			return readFromInputStream(inputStream);
		} catch (MalformedURLException e) {
			logger.error("Error acquiring data from URL.", e);
		} catch (IOException e) {
			logger.error("Error reading data from URL.", e);
		}
		return null;
	}

	public String getAnagFileContent() {
		return getAnagFileContent(configUrl);
	}
	
	public Reader getAnagFileBuffer() {
		URL urlObject;
		try {
			urlObject = new URL(configUrl);
			URLConnection urlConnection = urlObject.openConnection();
			InputStream inputStream = urlConnection.getInputStream();
			return  new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-1"));
		} catch (MalformedURLException e) {
			logger.error("Error acquiring data from URL.", e);
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
