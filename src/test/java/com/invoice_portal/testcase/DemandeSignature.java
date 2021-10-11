/**
 * 
 */
package com.invoice_portal.testcase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import com.invoice_portal.BaseTest.TestBase;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author Ramzi CHEBLI
 * 
 */
@Listeners(Listener.Listeners.class)

public class DemandeSignature extends TestBase {

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
		driver.get("http://localhost:8081/admin/Demande-signature");
		Thread.sleep(12000);
	}

	@Test(priority = 1 , enabled = false) // test incomplit
	public void Upload_File(Method method) throws InterruptedException {
		WebElement upload_file = driver.findElement(By.xpath("//input[@id='docUpload']"));
		Thread.sleep(2000);
		upload_file.sendKeys("/home/chebli/Téléchargements/pom.xml");
		Thread.sleep(2000);
		WebElement ck = driver.findElement(By.xpath("//*[contains(text(),'Confirmer')]"));
		ck.click();
	}

	@Test(priority = 2, enabled = false)
	public void Drag_And_Drop(Method method) {
		// Actions class method to drag and drop
		Actions builder = new Actions(driver);
		WebElement from = driver.findElement(By.id("column-a "));
		WebElement to = driver.findElement(By.id("column-b"));
		// Perform drag and drop
		builder.dragAndDrop(from, to).perform();
		// verify text changed in to 'Drop here' box
		String textTo = to.getText();
		if (textTo.equals("Dropped!")) {
			System.out.println("PASS: File is dropped to target as expected");
		} else {
			System.out.println("FAIL: File couldn't be dropped to target as expected");
		}
	}

	@Test(priority = 3)
	public void Clique_Voir_Historique(Method method) throws InterruptedException {
		WebElement ck = driver.findElement(By.xpath("//*[contains(text(),'Voir historique')]"));
		ck.click();
		Thread.sleep(15000);
		WebElement table = driver.findElement(By.tagName("tbody"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,10000)");
		Thread.sleep(1000);
		int R = table.findElements(By.tagName("tr")).size();
		System.out.println("Il y à " + R + " documents signés");
		Thread.sleep(1000);
		WebElement clk = driver.findElement(By.xpath("(//i[@class='fas fa-eye fa-2x'])[1]"));
		clk.click();
		Thread.sleep(13000);
		WebElement C = driver.findElement(By.xpath("//i[@class='far fa-times-circle fa-2x']"));
		C.click();
		WebElement affiche = driver.findElement(By.xpath("//div[@class='modal-body']"));
		boolean displayed = affiche.isDisplayed();
		AssertJUnit.assertTrue(displayed);

	}

	@Test(priority = 4)
	public void Documents_Non_Signes(Method method) throws InterruptedException {
		WebElement ck = driver.findElement(By.xpath("//*[contains(text(),'Voir historique')]"));
		ck.click();
		Thread.sleep(20000);
		WebElement clk =driver.findElement(By.xpath("//*[contains(text(),'Documents non signés')]"));
		clk.click();
		Thread.sleep(7000);
		WebElement table = driver.findElement(By.tagName("tbody"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,10000)");
		Thread.sleep(1000);
		int R = table.findElements(By.tagName("tr")).size();
		System.out.println("Il y à "+R+ " documents non signés");
		String actualResulat = "http://localhost:8081/admin/HistoriqueUploadAnce/";
		String expectedRessult = driver.getCurrentUrl();
		AssertJUnit.assertEquals(actualResulat, expectedRessult, "La page documents non signés non charger");
	}
	@Test(priority = 5)
	public void Documents_Supprimes(Method method) throws InterruptedException {
		WebElement ck = driver.findElement(By.xpath("//*[contains(text(),'Voir historique')]"));
		ck.click();
		Thread.sleep(20000);
		WebElement clk =driver.findElement(By.xpath("//*[contains(text(),'Documents supprimés')]"));
		clk.click();
		Thread.sleep(7000);
		WebElement table = driver.findElement(By.tagName("tbody"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,10000)");
		Thread.sleep(1000);
		int R = table.findElements(By.tagName("tr")).size();
		System.out.println("Il y à "+R+ " documents supprimés");
		String actualResulat = "http://localhost:8081/admin/HistoriqueUploadAnce/";
		String expectedRessult = driver.getCurrentUrl();
		AssertJUnit.assertEquals(actualResulat, expectedRessult, "La page documents supprimés non charger");
	}
	@Test(priority = 6)
	public void Documents_Verifier(Method method) throws InterruptedException {
		WebElement se = driver.findElement(By.xpath("(//a[@class='nav-link active'])[1]"));
		se.click();
		Thread.sleep(2000);
		WebElement ck = driver.findElement(By.xpath("(//a[@class='nav-link'])[7]"));
		ck.click();
		Thread.sleep(15000);
		WebElement table = driver.findElement(By.tagName("tbody"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,10000)");
		Thread.sleep(1000);
		int R = table.findElements(By.tagName("tr")).size();
		System.out.println("Il y à "+R+ " documents verifiés");
		String actualResulat = "http://localhost:8081/admin/historiqueVerify";
		String expectedRessult = driver.getCurrentUrl();
		AssertJUnit.assertEquals(actualResulat, expectedRessult, "La page documents vérifiés non charger");
	}
	@Test(priority = 7)
	public void Documents_Non_Verifies(Method method) throws InterruptedException {
		WebElement se = driver.findElement(By.xpath("(//a[@class='nav-link active'])[1]"));
		se.click();
		Thread.sleep(2000);
		WebElement ck = driver.findElement(By.xpath("(//a[@class='nav-link'])[7]"));
		ck.click();
		Thread.sleep(20000);
		WebElement clk =driver.findElement(By.xpath("//*[contains(text(),'Documents non vérifiés')]"));
		clk.click();
		Thread.sleep(7000);
		WebElement table = driver.findElement(By.tagName("tbody"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,10000)");
		Thread.sleep(1000);
		int R = table.findElements(By.tagName("tr")).size();
		System.out.println("Il y à "+R+ " documents non vérifiés");
		String actualResulat = "http://localhost:8081/admin/historiqueVerify";
		String expectedRessult = driver.getCurrentUrl();
		AssertJUnit.assertEquals(actualResulat, expectedRessult, "La page documents non vérifiés non charger");
	}
	
	@Test(priority = 8)
	public void Documents_Supprimes_pour_verifiés(Method method) throws InterruptedException {
		WebElement se = driver.findElement(By.xpath("(//a[@class='nav-link active'])[1]"));
		se.click();
		Thread.sleep(2000);
		WebElement ck = driver.findElement(By.xpath("(//a[@class='nav-link'])[7]"));
		ck.click();
		Thread.sleep(20000);
		WebElement clk =driver.findElement(By.xpath("//*[contains(text(),'Documents supprimés')]"));
		clk.click();
		Thread.sleep(7000);
		WebElement table = driver.findElement(By.tagName("tbody"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,10000)");
		Thread.sleep(1000);
		int R = table.findElements(By.tagName("tr")).size();
		System.out.println("Il y à "+R+ " documents supprimés pour vérification");
		String actualResulat = "http://localhost:8081/admin/historiqueVerify";
		String expectedRessult = driver.getCurrentUrl();
		AssertJUnit.assertEquals(actualResulat, expectedRessult, "La page documents supprimés pour vérifiés non charger");
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
