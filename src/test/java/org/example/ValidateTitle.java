package org.example;
import com.codoid.products.exception.FilloException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.com.base.Base;
import org.example.com.pageObjects.HomePage;
import org.example.com.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;

public class ValidateTitle extends Base {
	
	public WebDriver driver;
	public Actions actions;
	
	private Logger log = LogManager.getLogger(ValidateTitle.class.getName());
	private HashMap<String, String> data;

	@BeforeTest
	public void initialize() throws IOException, FilloException {
		
		driver = initializeDriver();
		log.info("Driver is initialized.");
		data = new Utils().getTestData("TC3");
		actions = new Actions(driver);
		actions.navigateTo(prop.getProperty("url"));
	}

	@Test
	public void checkTitle() throws IOException {


		HomePage hp = new HomePage(driver);

		String actualTitle = hp.getTitle().getText();
		String expectedTitle = data.get("Title");

		Assert.assertEquals(actualTitle, expectedTitle);
		log.info("Successfully validated Title");

	}

	@AfterTest
	public void teardown() {
		driver.close();
		log.info("Driver is closed");
	}
}
