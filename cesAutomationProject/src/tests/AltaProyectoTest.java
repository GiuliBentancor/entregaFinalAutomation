package tests;


import org.junit.jupiter.api.Test;


import pages.ProjectPage;


public class AltaProyectoTest extends baseTest {	
	ProjectPage projectPage;
	
	@Test
	public void altaProyConNombreyFechaDeInicio() throws InterruptedException {
		projectPage = new ProjectPage(driver);		 
		String startDateProject= properties.getProperty("startDate") + " " + properties.getProperty("startHour");
		projectPage.GoProjectPage();
		String id = projectPage.addNewProject();
		projectPage.addProjectName(properties.getProperty("projectName"));
		projectPage.addProjectStartDate(startDateProject);
		projectPage.saveChanges_PM();
		projectPage.FindProjectById(id);
		projectPage.verifyProjectId(id);
		projectPage.closeProjectTab();		
	}
}
