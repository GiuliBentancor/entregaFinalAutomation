package pages;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(name="login") 
	WebElement usernameField;
    
    @FindBy(name="passwd") 
	WebElement passwordField;
    
    @FindBy(name="submitit") 
	WebElement loginButton;
    
    @FindBy(id="topmenu_info_user_avatar")
    WebElement userAvatar;
      
    @FindBy(id="topmenu_logout") 
   	WebElement logout;

    
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(this.driver,20);

    }
    
    
    public void login(String usuario, String clave)
    {
        
      usernameField.sendKeys(usuario);
      passwordField.sendKeys(clave);
      loginButton.click();

    }
    
    public void validarUsuarioLogueado()
    {
      wait.until(ExpectedConditions.visibilityOf(userAvatar));
      assertTrue(userAvatar.isDisplayed());
    }
    
    public void logout()
    {
	  userAvatar.click();
	  logout.click();	
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginScreenMessage")));
	  assertEquals("[Login]", driver.getTitle());
    }


}
