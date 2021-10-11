/**
 * 
 */
package com.invoice_portal.testcase;


import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.invoice_portal.BaseTest.TestBase;
import com.relevantcodes.extentreports.LogStatus;



@Listeners(Listener.Listeners.class)
public class Authentification extends TestBase {
	
	@Parameters({ "URL" })
	@BeforeMethod()
	public void login(String URL, Method method) throws InterruptedException {
		logger = extent.startTest(method.getName());
		initialisation(URL);
	}

	@Test(dataProvider = "Mydata")
	public void SetUp_Authentification(String username, String passWord, Method method ) throws InterruptedException {
		
		SoftAssert softassert = new SoftAssert();
		WebElement user = driver.findElement(By.xpath("//input[@id='userName']"));
		user.sendKeys(username); 
		Thread.sleep(1000);
		WebElement pass = driver.findElement(By.id("password"));
		pass.sendKeys(passWord);
		Thread.sleep(1000);
		WebElement clk = driver.findElement(By.tagName("button"));
		clk.click();
        Thread.sleep(10000);
		String expectedResult = "http://localhost:8081/admin/dashboard";
		String actuelResult = driver.getCurrentUrl();
		

		if (actuelResult.contains("dashboard")) {
			softassert.assertTrue(true);
			System.out.println(actuelResult);
			System.out.println("Connexion réussite");

		} else {

			softassert.assertEquals(actuelResult, expectedResult,
					"it was failed because userName and password is false then you will be able to sign up");
			System.out.println(actuelResult);
			System.out.println("sign up please");
			driver.findElement(By.xpath("//span[contains(text(),'Inscription')]")).click();
			Thread.sleep(2000);
			driver.findElement(By.name("nom")).sendKeys("chebli");
			Thread.sleep(1000);
			driver.findElement(By.name("prenom")).sendKeys("ramzi");
			Thread.sleep(1000);
			driver.findElement(By.name("email")).sendKeys("ramzi.chebli@imex.com.tn");
			Thread.sleep(1000);
			driver.findElement(By.name("cin")).sendKeys("07700438");
			Thread.sleep(1000);
			driver.findElement(By.name("password")).sendKeys("12345678");
			Thread.sleep(1000);
			driver.findElement(By.name("doublePassword")).sendKeys("12345678");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@class='submit-btn-signUp']")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//img[@class='back-btn-signUp']")).click();

			softassert.assertAll("ok");
		}
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
	}

	@DataProvider
	public Object[][] Mydata() {
		Object[][] data = new Object[2][2];
		data[0][0] = "ahmed";
		data[0][1] = "ahmed123";
		data[1][0] = "Ramzi";
		data[1][1] = "ahmed123";
		return data;
	}

}
