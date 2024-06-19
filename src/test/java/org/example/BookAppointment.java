package org.example;
import com.codoid.products.exception.FilloException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.com.base.Base;
import org.example.com.pageObjects.AppointmentConfPage;
import org.example.com.pageObjects.BookAppointmentPage;
import org.example.com.pageObjects.HomePage;
import org.example.com.pageObjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class BookAppointment extends Base {
	
	public WebDriver driver;
	public Actions actions;
	
	private Logger log = LogManager.getLogger(BookAppointment.class.getName());
	private HashMap<String, String> data;

	@BeforeTest
	public void initialize() throws IOException, FilloException {
		
		driver = initializeDriver();
		log.info("Driver is initialized.");
		data = new Utils().getTestData("TC2");
		actions = new Actions(driver);
			
	}

	@Test
	public void bookAppointment() {
		
		HomePage hp = new HomePage(driver);
		LoginPage lp = new LoginPage(driver);
		BookAppointmentPage ba = new BookAppointmentPage(driver);
		AppointmentConfPage ac = new AppointmentConfPage(driver);
		
		actions.navigateTo(prop.getProperty("url"));
		actions.click(hp.getMenuBtn());
		actions.click(hp.getLogin());
		actions.enterText(lp.getUsername(), data.get("Username"));
		actions.enterText(lp.getPassword(), data.get("Password"));
		actions.click(lp.getLoginBtn());
		//add a wait function
		actions.selectFromDropdown(ba.getFacilityDD(), data.get("Facility"));
		actions.click(ba.getReadmission());
		actions.click(ba.getMedicaid());
		actions.enterText(ba.getVisitDate(), data.get("Visit Date"));
		actions.enterText(ba.getComment(), data.get("Comment"));
		actions.click(ba.getBookBtn());
		
		Assert.assertTrue(ac.getTitle().isDisplayed());

	}


	@AfterTest
	public void teardown() {
		driver.close();
		log.info("Driver is closed");
	}
}
