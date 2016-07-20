package edu.emory.cci.aiw.cvrg.eureka.cas;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;

/**
 * Created by akalsan on 7/19/16.
 */
public class ServiceURLRegexGenerator {
	public String getServiceRegEx() {
		CasProperties casProperties = new CasProperties();
		Set<String> serverList = new HashSet<String>();

		String defaultRegex = "^(https)://.*";
		String generatedRegex = defaultRegex;
		String eurekaWebappURL = casProperties.getEurekaWebappURL();
		String eurekaServiceURL = casProperties.getEurekaServiceURL();
		String eurekaETLURL = casProperties.getEurekaETLURL();
		String i2b2URL = casProperties.getI2b2URL();
		if (eurekaWebappURL != null && eurekaServiceURL != null && eurekaETLURL != null && i2b2URL != null
				&& !eurekaWebappURL.isEmpty() && !eurekaServiceURL.isEmpty() && !eurekaETLURL.isEmpty() && !i2b2URL.isEmpty()) {
			try {
				serverList.add(getServerName(eurekaWebappURL));
				serverList.add(getServerName(eurekaServiceURL));
				serverList.add(getServerName(eurekaETLURL));
				serverList.add(getServerName(i2b2URL));
				generatedRegex = createRegExForServiceURL(serverList,defaultRegex);
			} catch (MalformedURLException ex) {
				generatedRegex = defaultRegex;
				ex.printStackTrace();
			}
		}
		return generatedRegex;
	}

	private String getServerName(String url) throws MalformedURLException{
			URL newUrl = new URL(url);
			String hostName = newUrl.getHost();
			return hostName.startsWith("www.") ? hostName.substring(4) : hostName;
	}

	private String createRegExForServiceURL(Set<String> serverList,String defaultRegex) {
		StringBuilder uniqueServerNamesFromListBuilder = new StringBuilder();
		for (String serverName : serverList) {
			uniqueServerNamesFromListBuilder.append(serverName + "|");
		}
		uniqueServerNamesFromListBuilder.setLength(uniqueServerNamesFromListBuilder.length()-1);
		StringBuilder regexBuilder = new StringBuilder(defaultRegex);
		regexBuilder.append("(").append(uniqueServerNamesFromListBuilder.toString()).append(").*");
		return regexBuilder.toString();
	}
}
