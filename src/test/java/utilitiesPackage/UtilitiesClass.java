package utilitiesPackage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilitiesClass {

	WebDriver driver;
	private WebDriverWait wait;
	private Alert alert;
	
	public UtilitiesClass(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public void waitAndClickWithScroll(By locator) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}
	
	public void waitAndClick(By locator) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}
	
	public void waitAndInsert(By locator, String txtToInsert) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.clear();
		element.sendKeys(txtToInsert);
	}
	
	public void acceptAlert() {
		alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public void cancelAlert() {
		alert = driver.switchTo().alert();
		alert.dismiss();
	}
	
	public void takeScreenShot(String baseFileName) {
		File sourceFileName = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String finalFileName = baseFileName + "_" + timestamp + ".png";
		File destinationFile = new File("screenshots/" + finalFileName);
		destinationFile.getParentFile().mkdirs();
		try {
			FileHandler.copy(sourceFileName, destinationFile);
			System.out.println("Screenshot is saved " + destinationFile.getAbsolutePath());
		} catch (Exception e) {
			System.out.println("Failed to save screenshot" + e.getMessage());
		}
	}
	
	public void selectOption(WebElement a, int i) {
		Select sel = new Select(a);
		a.click();
		sel.selectByIndex(i);
	}
	
	// --- NEW METHOD: Wait for an element to be visible ---
	public WebElement waitForTheVisibility(By locator) {
		if (wait == null) {
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		}
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); 
	}
	
	// --- NEW METHOD: Check if an element is present (useful for verification without hard waits) ---
	public boolean isElementPresent(By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
