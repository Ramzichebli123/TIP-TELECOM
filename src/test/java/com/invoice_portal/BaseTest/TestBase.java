/**
 * 
 */
package com.invoice_portal.BaseTest;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Ramzi CHEBLI
 * 
 */
public class TestBase {

	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest logger;

	@Parameters({ " URL " })
	public void initialisation(String URL) {
		// System.setProperty("webdriver.chrome.driver","/home/imex/Téléchargements/navigateur/chromedriver");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(URL);
	}

	public void GetScreenshot(String MethodName) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile,
					new File("/home/chebli/eclipse-workspace/TIP-TELECOM/Test_Reports/" + MethodName + ".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
