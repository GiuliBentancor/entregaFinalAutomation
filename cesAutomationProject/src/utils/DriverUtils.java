package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import pages.LoginPage;

public class DriverUtils {
	public static LoginPage loginPage;
    public WebDriver driver;
	public Properties properties;
	static String chrome = "Chrome";
	
	 public DriverUtils (WebDriver driver) throws FileNotFoundException, IOException{
		this.driver = driver;
		properties = setProperties(properties);	   
	 }
	 
	 public Properties setProperties(Properties properties) throws FileNotFoundException, IOException {
			properties = new Properties();
			properties.load(new FileReader("src/resources/config.properties"));	
		   
			return properties;
		 }
	
	public  WebDriver setDriver() {			
		/*Para cambiar el navegador donde desea correr las pruebas debe editar el archivo config en resources*/
		String browser = properties.getProperty("browser") ;	
		if (browser.equals(chrome)) {
			System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver.exe");
			driver = new ChromeDriver();	
			return driver;
		}		
		else  {
			System.setProperty("webdriver.gecko.driver", "src/resources/geckodriver.exe");
			driver = new FirefoxDriver();	
			return driver;
		}      	
	}
	 
	 public void manageDriver() throws InterruptedException
	    {
			driver.manage().deleteAllCookies();
	        driver.manage().window().maximize();	
	        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);   
	    }
	 
	 public void quit() throws InterruptedException
	    {
	    	Thread.sleep(3000);
	        driver.quit();
	    }
	 	
	    public void goHomePage(){
		 	driver.get(properties.getProperty("url_base"));
	        loginPage = new LoginPage(driver);
	    }
	    
	    public void login(){
		    loginPage.login(properties.getProperty("user"), properties.getProperty("pass"));
		    loginPage.validarUsuarioLogueado();
	    }
	    
	    public void logout(){
	    	loginPage.logout();
	    }
}
