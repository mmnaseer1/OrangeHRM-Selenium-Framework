package pageObjectsPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilitiesPackage.UtilitiesClass;

public class OrangeLoginPageObjects {

	WebDriver driver;
	private UtilitiesClass util;
	
	private final By usernameField = By.cssSelector("input[name='username']");
	private final By passwordFiled = By.cssSelector("input[name='password']");
	private final By loginButton = By.cssSelector("button[type='submit']");
	private final By passwordResetLink = By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']");
	private final By loginPageTitle = By.xpath("//h5[@class='oxd-text oxd-text--h5 orangehrm-login-title']");
	private final By errorMessage = By.xpath("//p[text()='Invalid credentials']");
	
	public OrangeLoginPageObjects(WebDriver driver) {
		this.driver = driver;
		util = new UtilitiesClass(driver);
	}
	
	public void insertUsername(String username) {
		util.waitAndInsert(usernameField, username);
	}
	
	public void insertPassword(String password) {
		util.waitAndInsert(passwordFiled, password);
	}
	
	public void clickLoginBtn() {
		util.waitAndClick(loginButton);
	}
	
	public void clickPasswordResetLink() {
		util.waitAndClick(passwordResetLink);
	}
	
    // --- Verification Methods ---
	 /**
     * Checks if the "Invalid credentials" error message is displayed after a failed login attempt.
     * @return true if the error message is visible, false otherwise.
     */
	
	public boolean isErrorMsgDisplayed() {
		try {
			util.waitForTheVisibility(errorMessage);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	  /**
     * Checks if the user is currently on the OrangeHRM Login Page.
     * @return true if the login page's unique title is displayed, false otherwise.
     */
	public boolean isLoginPageDisplayed() {
		try {
			util.waitForTheVisibility(loginPageTitle);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
     * Retrieves the text of the "Invalid credentials" error message.
     * @return The text of the error message if visible, otherwise an empty string.
     */
	public String getErrorMsgText() {
		try {
			util.waitForTheVisibility(errorMessage);
			return driver.findElement(errorMessage).getText();
		} catch (Exception e) {
			return "";
		}
	}
}
