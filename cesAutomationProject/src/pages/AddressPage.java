package pages;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddressPage{
	 private WebDriver driver;
	 private String winHandleBefore;
	 private WebDriverWait wait;
	 private Actions action;

	   @FindBy(id="addressbook_sidebox_header") 
	   WebElement sideboxAB;
	   
	   @FindBy(id="addressbook-index_add")
	   WebElement addButton;
	   
	   @FindBy(id="addressbook-edit_n_fn")
	   WebElement nameField;
	   
	   @FindBy(id="addressbook-edit_n_given")
	   WebElement firstNameField;

	   @FindBy(id="addressbook-edit_n_family")
	   WebElement lasttNameField;
	   
	   @FindBy(id="addressbook-edit_button[ok]")
	   WebElement okNamebutton;
	   
	   @FindBy(id="addressbook-edit_tel_work")
	   WebElement businessPhoneField;
	   
	   @FindBy(id="addressbook-edit_email")
	   WebElement emailField;
	   
	   @FindBy(id="addressbook-edit_button[save]")
	   WebElement saveButton;
	  
	   @FindBy(xpath="//*[@id=\"addressbook-index_nm\"]//*[@class=\"et2_textbox\"]")
	   WebElement searchBox;
	   
	   @FindBy(xpath="//*[@id=\"addressbook-index_nm\"]//tr[1]/td[2]//span[1]")
	   WebElement nameData0Cell;
	   
	   @FindBy(id="addressbook-index_${row}[email]")
	   WebElement emailData0Cell;
	   
	   @FindBy(id="addressbook-index_${row}[tel_work]")
	   WebElement phoneData0Cell;
	   
	   @FindBy(id="addressbook-egw_fw_ui_tab_header")
	   WebElement addressTab;
	   

	 public AddressPage (WebDriver driver) {
	       this.driver= driver;
	       PageFactory.initElements(this.driver, this);	     
	       wait = new WebDriverWait(this.driver, 20);
	       action = new Actions(this.driver);
	    }
	 
	    public void goAddressBook() throws InterruptedException
	    {
	    	Thread.sleep(4000);
	    	winHandleBefore = driver.getWindowHandle();
	    	sideboxAB.click();
	        assertEquals("EGroupware [Address Book]", driver.getTitle());
	    }
	    
	    
	    public void closeAddressTab() throws InterruptedException
	    {
	    	Thread.sleep(5000);
	    	action.moveToElement(addressTab).build().perform(); 
	    	driver.findElement(By.cssSelector("#addressbook-egw_fw_ui_tab_header > span")).click();
	    }
	    
	    public void addNewContact() throws InterruptedException
	    {
	    	wait.until(ExpectedConditions.elementToBeClickable(addButton));
	    	addButton.click();	 
	    	
	    	Thread.sleep(2000);
	        for(String winHandle : driver.getWindowHandles()){
	            driver.switchTo().window(winHandle);
	        }
	      
	        assertEquals("EGroupware [Address Book]", driver.getTitle());
	    } 
	    
	    
	    public void addContactNames(String firstname, String lastname)
	    { 
	        nameField.click();; 
	        firstNameField.sendKeys(firstname);
	        lasttNameField.sendKeys(lastname);
	        okNamebutton.click();     
	    }
	    
	    public void addContactBusinessPhone(String businessphone)
	    {
	        businessPhoneField.sendKeys(businessphone);   
	    }
	    
	    public void addContactEmail(String email)
	    {
	        emailField.sendKeys(email);     
	    }
	    
	    public void saveChanges_AB()
	    {
	    	saveButton.click();
	        driver.switchTo().window(winHandleBefore);	        
	        assertEquals("Contact saved", driver.findElement(By.id("egw_message")).getText());   
	    }
	    
	    public void findContactByNames(String firstname, String lastname)
	    {	  
	    	searchBox.click();
	    	searchBox.sendKeys(lastname + " " + firstname);
	    	searchBox.sendKeys(Keys.RETURN);
	    }
	    
	    public void verifyContactNames(String firstname, String lastname)
	    {	  
	    	wait.until(ExpectedConditions.visibilityOf(nameData0Cell));
	        String coName = nameData0Cell.getText();
	        assertEquals(lastname + ", "+ firstname, coName);
	    }
	 
	    public void verifyContactEmail(String email)
	    {	         
	        String coEmail = emailData0Cell.getText();
	        assertEquals(email, coEmail);

	    }
	    
	    public void verifyContactBusinessPhone(String businessPhone)
	    {	         
	        String coPhone = phoneData0Cell.getText();
	        assertEquals(businessPhone, coPhone);

	    }
	    
	    
}

	    
