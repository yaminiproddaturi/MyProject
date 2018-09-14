package myPackage;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class HiltonHomePage {
	WebDriver driver;

	@BeforeMethod
	public void getbrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
		// Creating a driver object for Chrome browser
		driver = new ChromeDriver();
	}

	@Test
	public void searchbrowser() {

		driver.get("https://hiltonhonors3.hilton.com/en/index.html");// calling the Hilton WebPage
		System.out.println(driver.getTitle()); // Verifying if we are landed in the Right Website
		WebElement search = driver.findElement(By.id("hotelSearchOneBox"));
		search.sendKeys("Tampa,FL");
		search.sendKeys(Keys.TAB);
		System.out.println("Successfully entered the Location in the search box");

		// Arrival Date
		driver.findElement(By.name("arrivalDate")).click();
		List<WebElement> dates = driver.findElements(By.className("ui-state-default"));
		int count = driver.findElements(By.className("ui-state-default")).size();

		for (int i = 0; i < count; i++) {
			String text = driver.findElements(By.className("ui-state-default")).get(i).getText();
			if (text.equalsIgnoreCase("17")) {
				driver.findElements(By.className("ui-state-default")).get(i).click();
				break;
			}
		}
		System.out.println("Arrival date is taken");
		// Departure Date
		driver.findElement(By.name("departureDate")).click();
		List<WebElement> Depdate = driver.findElements(By.className("ui-state-default"));
		int counting = driver.findElements(By.className("ui-state-default")).size();

		for (int j = 0; j < count; j++) {
			String date = driver.findElements(By.className("ui-state-default")).get(j).getText();
			if (date.equalsIgnoreCase("22")) {
				driver.findElements(By.className("ui-state-default")).get(j).click();
				break;
			}
		}
		System.out.println("Departure date is taken");
		driver.findElement(By.xpath(".//*[@id=\"expanded_form_view\"]/div[5]/a")).click();

		// tried the second way
		/*
		 * WebElement myDynamicElement = (new WebDriverWait(driver, 30))
		 * .until(ExpectedConditions.visibilityOfElementLocated(
		 * By.xpath(".//*[@id=\\\"expanded_form_view\\\"]/div[5]/a")));
		 * 
		 * try { Actions act = new Actions(driver); act.moveToElement(myDynamicElement);
		 * act.click(); act.build().perform(); } catch (Exception e) {
		 * e.printStackTrace(); throw e; }
		 */

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,2000)", "");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("The list of hotels are displayed");

		driver.navigate().back();//navigating back to the web home page

		System.out.println("We are Back on the home Page!!Woohoo!!");
	}

	@AfterMethod
	public void aftermethod() {
		System.out.println("the first scenario is completed");
		driver.quit();
	}
}
