/**
 * 
 */
package com.invoice_portal.testcase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class GestionOperation extends TestBase{


	@Parameters({ "URL" })
	@BeforeMethod
	public void SetUp(String URL, Method method) throws InterruptedException {
		logger = extent.startTest(method.getName());
		initialisation(URL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement userNameTextBox = driver.findElement(By.xpath("//input[@id='userName']"));
		userNameTextBox.sendKeys("ahmed");
		WebElement passwordTextBox = driver.findElement(By.id("password"));
		passwordTextBox.sendKeys("ahmed123");
		WebElement cl = driver.findElement(By.tagName("button"));
		cl.click();
		Thread.sleep(15000);
		driver.get("http://localhost:8081/admin/GestionOperations");
		Thread.sleep(12000);
	}
	@Test(priority = 1)
	public void Ajouter_Opérations(Method method) throws InterruptedException {
		WebElement table = driver.findElement(By.tagName("tbody"));
		int taille = table.findElements(By.tagName("tr")).size();
		System.out.println(taille);
		WebElement se = driver.findElement(By.xpath("//input[@name='ccpNameInput']"));
		se.sendKeys("VER");
		Thread.sleep(1000);
		WebElement ck = driver.findElement(By.xpath("//input[@name='ccpLibelleInput']"));
		ck.sendKeys("Versement");
		WebElement clq = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/label[1]"));
		clq.click();
		Thread.sleep(2000);
		WebElement clk = driver.findElement(By.xpath("//input[@type='submit']"));
		clk.click();
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,10000)");
		Thread.sleep(1000);
		int R = table.findElements(By.tagName("tr")).size();
		System.out.println(R);
		System.out.println("Il y à "+R+ " Opérations");
		
		Assert.assertNotEquals(taille, R, "L'opération n'est pas ajouté");
	}
	@Test(priority = 2)
	public void Supprimer_opérations(Method method) throws InterruptedException {
		WebElement table = driver.findElement(By.tagName("tbody"));
		int taille = table.findElements(By.tagName("tr")).size();
		System.out.println("Il y à "+taille+ " opérations avant la supppression");
		WebElement se = driver.findElement(By.xpath("//button[@id='tooltipDeleteCCp0']"));
		se.click();
		Thread.sleep(1000);
		WebElement fenetre = driver.findElement(By.xpath("//div[@class='modal-content']"));
		WebElement ck = fenetre.findElement(By.xpath("(//button[@class='btn btn-secondary'])[1]"));
		ck.click();
		Thread.sleep(15000);
		int R = table.findElements(By.tagName("tr")).size();
		System.out.println("Il y à "+R+ " opérations après la suppression");
	
		Assert.assertNotEquals(taille, R, "L'opération n'est pas supprimée");
		System.out.println("l'opération est supprimé avec succée");
	}
		
	
	@AfterMethod
	public void tearDown(Method method, ITestResult result) {
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
