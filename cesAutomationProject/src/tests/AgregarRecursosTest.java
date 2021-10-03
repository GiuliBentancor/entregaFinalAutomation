package tests;


import org.junit.jupiter.api.Test;

import pages.ProjectPage;


public class AgregarRecursosTest extends baseTest {
	ProjectPage projectPage;
	
	@Test
	public void AgregarRecursosAProyecto() throws InterruptedException {
		projectPage = new ProjectPage(driver);	
		String resource = properties.getProperty("resource");
		projectPage.GoProjectPage();
		projectPage.openEditWindow(properties.getProperty("indexToEdit"));
		projectPage.moveLinkTab();
		projectPage.addResourcesInLinkTab(resource);
		projectPage.verifyResourceInLinks(resource);
		projectPage.saveChanges_PM();
		projectPage.closeProjectTab();
	}

}
