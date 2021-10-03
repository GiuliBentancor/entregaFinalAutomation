package pages;

import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;


public class ProjectPage{
	   private WebDriver driver;
	   private Actions action;
	   private String proyectID;
	   private String ProName;
	   private String winHandleBefore;
	   private WebDriverWait wait;
	   
	   @FindBy(id="projectmanager_sidebox_header") 
	   WebElement sideboxPM;
	   
	   @FindBy(id="projectmanager-list_add") 
	   WebElement addButton;
	   
	   @FindBy(id="projectmanager-edit_pm_number")
	   WebElement idField;
	   
	   @FindBy(id="projectmanager-edit_pm_title") 
	   WebElement titleField;
	   
	   @FindBy(xpath="//*[@id=\"projectmanager-edit_pm_real_start\"]/input") 
	   WebElement startDateField;
	  
	   @FindBy(id="projectmanager-edit_save") 
	   WebElement saveButton;
	   
	   @FindBy(xpath= "//*[@id=\"projectmanager-list_nm\"]//*[@class=\"et2_textbox\"]") 
	   WebElement searchBox;
	   
	   @FindBy (id= "projectmanager-list_0[pm_title]")
	   WebElement linkTitleI0;
	   
	   @FindBy(id="projectmanager-list_${row}[pm_number]")
	   WebElement idFirstelementProj;
	   
	   @FindBy(xpath = "//*[@id=\"projectmanager-elements-list_${row}[pe_real_start]\"]/time")
	   WebElement startDateCell;
	    
	   @FindBy (xpath = "//*[@src=\"/egroupware/pixelegg/images/edit.svg\"]")
	   WebElement editOption;
	   
	   @FindBy(xpath= "//*[@id=\"projectmanager-edit_tabs\"]/div[1]")
	   WebElement editTabs;
	   
	   @FindBy(xpath="//*[@id=\"projectmanager-edit_tabs\"]/div[1]/span[6]")
	   WebElement linkTab;
	   
	   @FindBy(id="projectmanager-edit_projectmanager-edit-links")
	   WebElement editLink;
	   
	   @FindBy(xpath= "//*[@id=\"projectmanager-edit_link_to_link_entry\"]/input")
	   WebElement searchBoxLinks;
	   
	   @FindBy(xpath="//*[@id=\"projectmanager-edit_link_to\"]/button")
	   WebElement linkButton;
	   
	   @FindBy(xpath="//*[@id=\"link_temp_4\"]/td[2]")
	   WebElement firstElementList;
	   
	   @FindBy(id="projectmanager-egw_fw_ui_tab_header")
	   WebElement projectTab;
	 
	   
	    public ProjectPage (WebDriver driver) {
	        this.driver= driver;
	        PageFactory.initElements(this.driver, this);
	        action = new Actions(this.driver);
	        wait = new WebDriverWait(this.driver, 10);

	    }
	    
	    	    
	    public void GoProjectPage() throws InterruptedException
	    {
	    	Thread.sleep(2000);
	    	wait.until(ExpectedConditions.elementToBeClickable(sideboxPM));
	    	
	    	winHandleBefore = driver.getWindowHandle(); 	
	    	sideboxPM.click();
	        assertEquals("EGroupware [Project Manager]", driver.getTitle());
	    }
	    
	    
	    public void closeProjectTab() throws InterruptedException
	    {
	    	Thread.sleep(5000);
	    	action.moveToElement(projectTab).build().perform(); 
	    	driver.findElement(By.cssSelector("#projectmanager-egw_fw_ui_tab_header > span")).click();	    	    	
	    }

	    
	    public String addNewProject() throws InterruptedException
	    {
	        	    	
	    	wait.until(ExpectedConditions.elementToBeClickable(addButton));
	    	addButton.click();
	        
	    	Thread.sleep(2000);

	        for(String winHandle : driver.getWindowHandles()){
	            driver.switchTo().window(winHandle);
	        }	     
	        
	        
	        assertEquals("EGroupware [Project Manager - Add project]", driver.getTitle());	 
	        proyectID= idField.getAttribute("value");	
			return proyectID;	
	    }
	    
	    public void addProjectName(String nombreDelProyecto)
	    {	        	    	             
	        titleField.sendKeys(nombreDelProyecto);	         
        	        
	    }
	    
	    public void addProjectStartDate( String startDate)
	    {	        	    	             
	        startDateField.sendKeys(startDate);	        
        	        
	    }
	    
	    public void saveChanges_PM()
	    {	       
	    	saveButton.click();	        
	        driver.switchTo().window(winHandleBefore);	        
	        assertEquals("Project saved.", driver.findElement(By.id("egw_message")).getText());   
	    }
	    
	    	    
	    public void FindProjectByName(String nombreDelProyecto)
	    {	  
	    	searchBox.click();
	    	searchBox.sendKeys(nombreDelProyecto);
	    	searchBox.sendKeys(Keys.RETURN);
	    	
	    }
	    
	    public void verifyProjectName(String nombreDelProyecto) 
	    {	      	
	    	wait.until(ExpectedConditions.visibilityOf(linkTitleI0));
	        ProName = linkTitleI0.getText();
	        assertEquals(nombreDelProyecto, ProName);	        
	        linkTitleI0.click();
	    }
	    
	    public void FindProjectById(String id)
	    {	  
	    	searchBox.click();
	    	searchBox.sendKeys(id);
	    	searchBox.sendKeys(Keys.RETURN);     
	    }
	    
	    public void verifyProjectId(String id)
	    {	    
	        String idObt = idFirstelementProj.getText();
	        assertEquals(id, idObt);	        
	        linkTitleI0.click();
	    }
	    
	    
	    public void verifyProjectStartDate(String startDate)
	    {
	    	wait.until(ExpectedConditions.visibilityOf(startDateCell));    
	        String dateObt = startDateCell.getText();
	        assertEquals(startDate, dateObt);
	    }
	
	    public void openEditWindow (String index)
	    {
	    	
	    	winHandleBefore = driver.getWindowHandle();	
	    	
	    	wait.until(ExpectedConditions.visibilityOf(linkTitleI0));

	    	WebElement link = driver.findElement(By.id("projectmanager-list_"+ index +"[pm_title]"));
	    	    	
	    	String proName = link.getText();
	    	action.contextClick(link).perform();	    	   
	    	editOption.click();

	        for(String winHandle : driver.getWindowHandles()){
	            driver.switchTo().window(winHandle);
	        }	     
	        
	        wait.until(ExpectedConditions.visibilityOf(editTabs));
	        assertEquals("projectmanager-edit: " + proName  , driver.getTitle());	
	        
	    }
	    public void moveLinkTab()
	    {
	        action.moveToElement(editTabs).build().perform(); 
	        linkTab.click();	              	        
       
	    }

	    
	    public void addResourcesInLinkTab(String recurso)
	    {
        	        
	        wait.until(ExpectedConditions.visibilityOf(editLink));
	        editLink.click();	        
	        By dropdown = By.xpath("//*[@id=\"projectmanager-edit_link_to_link_entry\"]/select");	        
	        Select dropdownElement = new Select (driver.findElement(dropdown));	         
	        dropdownElement.selectByValue("resources");	      
	        
	        searchBoxLinks.sendKeys(recurso);
	        searchBoxLinks.submit();
	        
	        driver.findElement(By.linkText(recurso)).click();	        
	        linkButton.click();	       	        
       
	    }
	    
	    public void verifyResourceInLinks(String recurso)
	    {
	      	         
	    	wait.until(ExpectedConditions.visibilityOf(firstElementList));
	        assertEquals(recurso, firstElementList.getText());	       
	    }


}
