package testPackage;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BasePackage.BaseTest;
import pageObjectsPackage.OrangeDashboardPageObjects;
import pageObjectsPackage.OrangeLoginPageObjects;
import utilitiesPackage.UtilitiesClass;


public class LoginRegressionTest extends BaseTest {
	
	private UtilitiesClass util; 
	private OrangeLoginPageObjects loginPage;
	private OrangeDashboardPageObjects dashboardPage;
	
  @BeforeMethod
  public void setup() {
	  util = new UtilitiesClass(getDriver());
	  loginPage = new OrangeLoginPageObjects(getDriver());
	  dashboardPage = new OrangeDashboardPageObjects(getDriver());
  }
	
  @Test(priority = 1, dataProviderClass = dataProviders.LoginDataProvider.class, dataProvider = "OrangeHRM_LoginData")
  public void validLogin(String username, String password, String expectedResult) {
	  loginPage.insertUsername(username);
	  loginPage.insertPassword(password);
	  loginPage.clickLoginBtn();
	  util.takeScreenShot("Login Screenshot");
	 
      Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Login was not successful.");
      String actualDashboardTxt = dashboardPage.getDashboardTxt();
      Assert.assertEquals(actualDashboardTxt, expectedResult);
  }
  
  @Test(priority = 0, dataProviderClass = dataProviders.LoginDataProvider.class, dataProvider = "OrangeHRM_LoginData")
   public void invalidLogin(String username, String password, String expectedErrorMessage) {
	  
	  Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Did not land on Login page");
	  loginPage.insertUsername(username);
	  loginPage.insertPassword(password);
	  loginPage.clickLoginBtn();
	  util.takeScreenShot("Invalid Login Screenshot");
	  
	  Assert.assertTrue(loginPage.isErrorMsgDisplayed(), "Expected error message was not displayed.");
	  String actualErrorMsg = loginPage.getErrorMsgText();
	  Assert.assertEquals(actualErrorMsg, expectedErrorMessage);
  }
  
}
