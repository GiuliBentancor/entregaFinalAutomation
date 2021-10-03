package tests;
import org.junit.jupiter.api.Test;


import pages.ProjectPage;


public class ConsultarProyectoTest extends baseTest {
	ProjectPage projectPage;

	@Test
	public void ConsultarDatosProyecto() throws InterruptedException {
		projectPage = new ProjectPage(driver);		
		/*Precondicion: Debe existir un proyecto con estos datos en la lista de proyectos*/
		String startDate= properties.getProperty("startDate");
		String projectName = properties.getProperty("projectNameToFind");		
		projectPage.GoProjectPage();
		projectPage.FindProjectByName(projectName);
		projectPage.verifyProjectName(projectName);
		projectPage.verifyProjectStartDate(startDate);
		projectPage.closeProjectTab();
	}
}
