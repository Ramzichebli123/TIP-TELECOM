/**
 * 
 */
package com.invoice_portal.testcase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import com.invoice_portal.BaseTest.TestBase;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author Asma GARY
 * 
 */
@Listeners(Listener.Listeners.class)

public class GestionRubrique extends TestBase {

	@Parameters({ "URL" })
	@BeforeMethod
	public void SetUp(String URL, Method method) throws InterruptedException {
		logger = extent.startTest(method.getName());
		initialisation(URL);
		WebElement userNameTextBox = driver.findElement(By.xpath("//input[@id='userName']"));
		userNameTextBox.sendKeys("ahmed");
		WebElement passwordTextBox = driver.findElement(By.id("password"));
		passwordTextBox.sendKeys("ahmed123");
		WebElement cl = driver.findElement(By.tagName("button"));
		cl.click();
		Thread.sleep(10000);
		driver.get("http://localhost:8081/admin/gestionDesRubriques");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(10000);

	}

	@Test(priority = 1)
	public void Parametre__Background(Method method) throws InterruptedException {

		WebElement ck = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/em[1]"));
		ck.click();
		Thread.sleep(2000);
		WebElement colorGreen = driver
				.findElement(By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/ul[1]/li[2]/div[1]/span[3]"));
		colorGreen.click();
		Thread.sleep(2000);
		WebElement colorBlue = driver
				.findElement(By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/ul[1]/li[2]/div[1]/span[2]"));
		colorBlue.click();
		Thread.sleep(2000);
		WebElement colorPrimary = driver
				.findElement(By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/ul[1]/li[2]/div[1]/span[1]"));
		colorPrimary.click();
		Thread.sleep(2000);
		WebElement LightMode = driver
				.findElement(By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/ul[1]/li[4]/span[2]"));
		LightMode.click();
		Thread.sleep(2000);
		WebElement DarkMode = driver
				.findElement(By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/ul[1]/li[4]/span[3]"));
		DarkMode.click();
		Thread.sleep(2000);
		WebElement ck1 = driver.findElement(By.xpath("//ul[@class='dropdown-menu show']"));
		Assert.assertEquals(ck1.isDisplayed(), true, "le parametre side bare background n'est pas cliquer ! ");
		System.out.println("le parametre side bare background est fonctionne correctement");
	}
	@Test(priority = 2 )
	public void Recherche_Rapide(Method method) throws InterruptedException {
		WebElement rech = driver.findElement(By.xpath("//input[@id='inlineFormInputGroup']"));
		rech.sendKeys("العروض الجزافية و الرسوم");
		
		WebElement T = driver.findElement(By.xpath("//input[@value='العروض الجزافية و الرسوم']"));
		Thread.sleep(1000);
		String actuelResultat = T.getAttribute("value");
		String expectedResult = "العروض الجزافية و الرسوم";
		Thread.sleep(1000);
		Assert.assertEquals(actuelResultat, expectedResult , "La recherche non valide ! ");
		System.out.println("la recherche de " + expectedResult +" est valide");
		
	}
	@Test(priority = 3 )
	public void Ajouter_Operation(Method method) throws InterruptedException {
		int actual1 = driver.findElements(By.tagName("tr")).size();
		Thread.sleep(1000);
		System.out.println("taille initiale du tableau : "+actual1);
		WebElement val = driver.findElement(By.xpath("//input[@name='rubriqueFr']"));
		val.sendKeys("chebli_ramzi_Test");
		WebElement val2 = driver.findElement(By.xpath("//input[@name='RubriqueArb']"));
		val2.sendKeys("رمزى_شبلى");
		WebElement bt = driver.findElement(By.xpath("//input[@value='Ajouter une opération']"));
		bt.click();
		Thread.sleep(5000);
		int actual2 = driver.findElements(By.tagName("tr")).size();
		System.out.println("taille du tableau après ajout de la rubrique : "+actual2);
		Assert.assertNotEquals(actual1, actual2, "l'ajout est échouer");
		System.out.println("La rubrique est ajouter avec succes");
	    
	}
	@Test(priority = 4)
public void Supprimer_Ribrique(Method method) throws InterruptedException {
		int actual1 = driver.findElements(By.tagName("tr")).size();
		System.out.println("taille initiale du tableau avant la supprission du rubrique: "+actual1);
		WebElement sup = driver.findElement(By.xpath("//button[@id='tooltipDeleteRubrique8']"));
		sup.click();
		WebElement validation = driver.findElement(By.xpath("//*[contains(text(), 'Oui')]"));
		validation.click();
		Thread.sleep(10000);
		int actual2 = driver.findElements(By.tagName("tr")).size();
		System.out.println("taille du tableau après la supprission du rubrique : "+actual2);
		Assert.assertNotEquals(actual1, actual2, "la supprission est échouer");
		System.out.println("La rubrique est supprimer avec succes");
	}
	@AfterMethod
	public void tearDown(Method method, ITestResult result ) {
		if(result.getStatus() == ITestResult.SUCCESS) {
			logger.log(LogStatus.PASS, "Test PASS");
			
		} else if(result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, result.getThrowable());
			logger.log(LogStatus.FAIL, "<a href='" + result.getName() + ".png" + "'><span class='lable info'>Download Snapshot</span></a>");

		} else {
			logger.log(LogStatus.SKIP, "Test Skipped");
		}
		driver.quit();
		driver = null;
	}
}
