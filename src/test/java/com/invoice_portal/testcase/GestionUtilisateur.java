
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

public class GestionUtilisateur extends TestBase {
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
		Thread.sleep(10000);
		driver.get("http://localhost:8081/admin/gestionProfils");
		Thread.sleep(5000);
	}

	@Test(priority = 1 , enabled = false)
	public void Test_User_Ahmed(Method method) throws InterruptedException {
		WebElement ck = driver.findElement(By.id("inlineFormInputGroup"));
		ck.sendKeys("ahmed");
		Thread.sleep(2000);
		WebElement count = driver.findElement(By.tagName("tbody"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		int count1 = count.findElements(By.tagName("tr")).size();
		System.out.println(count1);
		Thread.sleep(2000);
		boolean t = driver.findElement(By.id("chkahmed")).isEnabled();
		if (count1 > 0 && t) {
			
				System.out.println("les droits pour ahmed est activé");
			
		} else {
			System.out.println("Recherche pour ahmed invalide");
		}
		Assert.assertTrue(true);
	}

	@Test(priority = 2)
	public void Test_User_Amira(Method method) throws InterruptedException {
		
		driver.findElement(By.id("inlineFormInputGroup")).sendKeys("amira");
		Thread.sleep(1000);
		WebElement count = driver.findElement(By.tagName("tbody"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		int count1 = count.findElements(By.tagName("tr")).size();
		System.out.println(count1);
		Thread.sleep(2000);
		boolean t = driver.findElement(By.id("chkamira.hasnaoui@tunisietelecom.tn")).isSelected();
		if (count1 > 0 && t) {
			System.out.println("les droits pour amira est activé");
			driver.findElement(By.id("img-profile-rights0")).click();
			Thread.sleep(15000);
			if (driver.findElement(By.id("btn-for-role-disabled0")).isEnabled()) 
			{
				driver.findElement(By.id("btn-for-role-disabled0")).click();
			} else {
				driver.findElement(By.id("btn-for-role0")).click();
			}
			Thread.sleep(10000);

			if (driver.findElement(By.id("btn-for-role-disabled1")).isEnabled()) {
				driver.findElement(By.id("btn-for-role-disabled1")).click();
			} else {
				driver.findElement(By.id("btn-for-role1")).click();
			}
			Thread.sleep(10000);

			if (driver.findElement(By.id("btn-for-role-disabled2")).isEnabled()) {
				driver.findElement(By.id("btn-for-role-disabled2")).click();
			} else {
				driver.findElement(By.id("btn-for-role2")).click();
			}
			Thread.sleep(10000);

			if (driver.findElement(By.id("btn-for-role-disabled3")).isEnabled()) {
				driver.findElement(By.id("btn-for-role-disabled3")).click();
			} else {
				driver.findElement(By.id("btn-for-role3")).click();
			}
			Thread.sleep(10000);

			if (driver.findElement(By.id("btn-for-role-disabled4")).isEnabled()) {
				driver.findElement(By.id("btn-for-role-disabled4")).click();
			} else {
				driver.findElement(By.id("btn-for-role4")).click();
			}
			Thread.sleep(10000);

			if (driver.findElement(By.id("btn-for-role-disabled5")).isEnabled()) {
				driver.findElement(By.id("btn-for-role-disabled5")).click();
			} else {
				driver.findElement(By.id("btn-for-role5")).click();
			}
			Thread.sleep(10000);

			if (driver.findElement(By.id("btn-for-role-disabled6")).isEnabled()) {
				driver.findElement(By.id("btn-for-role-disabled6")).click();
			} else {
				driver.findElement(By.id("btn-for-role6")).click();
			}
			Thread.sleep(10000);
			WebElement bt = driver.findElement(By.xpath("//*[contains(text(),'Fermer')]"));
            boolean aux = bt.isDisplayed();
            Assert.assertEquals(aux, true);
			driver.findElement(By.xpath("//*[contains(text(),'Fermer')]")).click();
		} else {
			System.out.println("les droits pour amira n'est pas activé et on doit l'activé");
			driver.findElement(By.id("chk0")).click();
			Thread.sleep(10000);

		}
		
	}

	@Test(priority = 3, enabled = false)
	public void Test_Boutton_Recherche(Method method) throws InterruptedException {
		WebElement ck = driver.findElement(By.xpath("//button[@id='search-button']"));
		ck.click();
		Thread.sleep(1000);
		WebElement cl = driver.findElement(By.xpath("//input[@class='form-control']"));
		cl.sendKeys("mahdi");
		boolean valide = cl.isDisplayed();
		Thread.sleep(1000);
		WebElement close = driver.findElement(By.xpath("//button[@class='close']"));
		close.click();
		Thread.sleep(1000);
		WebElement nav = driver.findElement(By.xpath("//a[@aria-label='Next']"));
		nav.click();
		Thread.sleep(1000);
		WebElement nav2 = driver.findElement(By.xpath("//a[@aria-label='Previous']"));
		nav2.click();
		Assert.assertTrue(valide, "le boutton rechercher non cliquer");
		

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
