package tests;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;


import utils.DriverUtils;

public class baseTest {
	public static DriverUtils utils;
    public static WebDriver driver;
	public static Properties properties;

	@BeforeAll
	static void firstset() throws IOException{
		properties = new Properties();
		utils = new DriverUtils(driver);		
	}

	
	@BeforeEach
	public void setup() throws InterruptedException, FileNotFoundException, IOException {			
		driver = utils.setDriver();
		properties = utils.setProperties(properties);
		utils.manageDriver();
		utils.goHomePage();
		utils.login();           	
	}
	
	 @AfterEach
	    public void tearDown() throws InterruptedException{
		 Thread.sleep(2000);  
		 utils.logout();
		 utils.quit();
	    }
}
