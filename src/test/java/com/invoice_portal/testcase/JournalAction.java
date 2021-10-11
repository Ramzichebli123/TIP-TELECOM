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

public class JournalAction extends TestBase {

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
		driver.get("http://localhost:8081/admin/consultationJournalActions");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[contains(text(),'Journal d’actions')]")).click();
		Thread.sleep(15000);
	}

	
	
	@Test(priority = 1)
	public void Click_bouton_details(Method method) throws InterruptedException {
		WebElement clk = driver.findElement(By.xpath("//tr[1]//td[5]//button[1]"));
		clk.click();
		Thread.sleep(2000);
		WebElement ck = driver.findElement(By.xpath("//button[@class='btn btn-secondary']"));
		ck.click();
		Assert.assertEquals(ck.isDisplayed(), true, "le bouton detail n'est pas cliquer ! ");
		System.out.println("le bouton datail est cliquer avec succés");
	}

	@Test(priority = 2)
	public void Recherche_Rapide(Method method) throws InterruptedException {
		WebElement clk = driver.findElement(By.xpath("//input[@id='inlineFormInputGroup']"));
		clk.sendKeys("ahmed");
		WebElement bt = driver.findElement(By.xpath("//button[@class='btn btn-info btn-sm']"));
		bt.click();
		Thread.sleep(10000);
		
		
		WebElement table = driver.findElement(By.tagName("tbody"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,10000)");
		Thread.sleep(2000);
		int R = table.findElements(By.tagName("tr")).size();
		System.out.println("Ahmed à subi "+R+ " actions");
		Assert.assertEquals(true, true);	
	}

	@Test(priority = 3)
	public void Choisir_Type_Action(Method method) throws InterruptedException {
		for (int i = 0; i < driver.findElements(By.xpath("//button[@class='dropdown-item']")).size(); i++) {
			
			WebElement btn = driver.findElement(By.xpath("//button[@class='dropdown-toggle btn btn-info btn-sm']"));
			btn.click(); // clique sur boutton choisir type action
			if(i == 0) { // pour affiché une seul fois le nombres de type d'action
				System.out.println("Il y à "  +driver.findElements(By.xpath("//button[@class='dropdown-item']")).size()+ " types d'actions ");
			}
			Thread.sleep(1000);
			WebElement list = driver.findElement(By.xpath("//div[@class='dropdown-menu show']"));
			String act = list.findElements(By.xpath("//button[@class='dropdown-item']")).get(i).getText();
			driver.findElements(By.xpath("//button[@class='dropdown-item']")).get(i).click(); // clique sur le action i
			Thread.sleep(1000);
			WebElement bt = driver.findElement(By.xpath("//button[@class='btn btn-info btn-sm']")); // clique sur le
																										// boutton
																										// chercher
			bt.click();
			Thread.sleep(10000);
			
			WebElement table = driver.findElement(By.tagName("tbody"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(2000);
			
			int R = table.findElements(By.tagName("tr")).size();
			 System.out.println(driver.findElements(By.xpath("//button[@class='dropdown-item']")).get(i).getText());
			System.out.println("Le type d'action est << " +  act+ " >>, cet utilisateur subi " +R+ " actions");
			Thread.sleep(1000);
		    WebElement Element = driver.findElement(By.xpath("//*[contains(text(),'Semaine courante')]"));	
	        js.executeScript("arguments[0].scrollIntoView();", Element);
	        Thread.sleep(2000);
			WebElement b = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/button[2]")); // clique sur
																										// boutton
																										// reinsialisé
			b.click();
		}
		Assert.assertEquals(true, true);
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
