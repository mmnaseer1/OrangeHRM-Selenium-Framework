package BasePackage;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import configReader.ConfigReader;

public class BaseTest {

	private static ThreadLocal<WebDriver> TL = new ThreadLocal<>();
	private ConfigReader configReader;
	
	@BeforeMethod
	public void testSetup() {
		configReader = new ConfigReader();
		String browserName = configReader.getBrowser();
		WebDriver driver = DriverFactory.createDriver(browserName);
		TL.set(driver);
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(configReader.getBaseUrl());
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(configReader.getImplicitWaitTimeout()));
		
	}
	public static WebDriver getDriver() {
		return TL.get();
	}
	
	@AfterMethod
	public void teardown() {
		WebDriver driver = getDriver();
		if (driver != null) {
			driver.quit();
			TL.remove();
		}
	}
}