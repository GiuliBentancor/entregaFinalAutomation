package tests;


import org.junit.jupiter.api.Test;


import pages.AddressPage;


public class AgregarContactoTest extends baseTest {
	AddressPage addressPage;
		
	@Test
	public void AgregarContactoaLaAgenda() throws InterruptedException {
		addressPage = new AddressPage(driver);			   
		String firstname = properties.getProperty("firstname");
		String lastname = properties.getProperty("lastname");
		String email = firstname + lastname + properties.getProperty("email");
		String businessphone= properties.getProperty("businessphone");	
	    addressPage.goAddressBook(); 
		addressPage.addNewContact();
		addressPage.addContactNames(firstname, lastname);
		addressPage.addContactBusinessPhone(businessphone);
		addressPage.addContactEmail(email);
		addressPage.saveChanges_AB();
		addressPage.findContactByNames(firstname, lastname);
		addressPage.verifyContactNames(firstname, lastname);
		addressPage.verifyContactEmail(email);
		addressPage.verifyContactBusinessPhone(businessphone);
		addressPage.closeAddressTab();
	}

}
