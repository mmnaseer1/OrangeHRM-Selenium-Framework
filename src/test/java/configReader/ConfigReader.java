package configReader;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

	private Properties properties;
	
	public ConfigReader() {
		
		properties = new Properties();
		
		String environment = System.getProperty("env","qa");
		String configFilePath = environment + "_config.properties";
		
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFilePath)){
			
			if (inputStream == null) {
				throw new RuntimeException("Could not find the config file " + configFilePath);
			}
			
			properties.load(inputStream);
			
		} catch (Exception e) {
			throw new RuntimeException("Could not load the config file " + configFilePath,e);
		}
	}
	public String getBaseUrl() {
		return properties.getProperty("baseUrl");
	}
	public String getBrowser() {
		return properties.getProperty("browser");
	}
	public int getImplicitWaitTimeout() {
		return Integer.parseInt(properties.getProperty ("implicitwait","10"));
	}
}
