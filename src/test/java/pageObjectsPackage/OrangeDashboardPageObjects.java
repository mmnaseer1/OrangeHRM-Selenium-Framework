package pageObjectsPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilitiesPackage.UtilitiesClass;

public class OrangeDashboardPageObjects {

	WebDriver driver;
	private UtilitiesClass util;
	
	private final By dashboardTxt = By.xpath("//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']");
 
	
	public OrangeDashboardPageObjects(WebDriver driver) {
		this.driver = driver;
		util = new UtilitiesClass(driver);
	}
	
	public boolean isDashboardDisplayed() {
		try {
			util.waitForTheVisibility(dashboardTxt);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getDashboardTxt() {
		return util.waitForTheVisibility(dashboardTxt).getText();
	}
}
