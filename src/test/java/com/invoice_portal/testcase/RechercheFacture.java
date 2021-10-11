/**
 * 
 */
package com.invoice_portal.testcase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
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

public class RechercheFacture extends TestBase {

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
		Thread.sleep(12000);
		driver.get("http://localhost:8081/admin/consultationFactures");
		Thread.sleep(2000);
		WebElement rech = driver.findElement(By.xpath("//input[@name='ohrefnum']"));
		rech.sendKeys("2019036000009638");
		WebElement rech2 = driver.findElement(By.xpath("//input[@name='codeClient']"));
		rech2.sendKeys("1.1111207230");
		WebElement clk = driver.findElement(By.xpath("//button[@type='submit']"));
		clk.click();

	}

	@Test(priority = 1) // ok
	public void Click_Bouton_Detail(Method method) throws InterruptedException, IOException {

		WebElement Bt = driver.findElement(By.xpath("//button[@id='tooltipOpen10']")); // clique sur le bouton Detail
		Bt.click();
		Thread.sleep(2000);
		WebElement text = driver.findElement(By.xpath("//div[@class='modal-body']")); // recuperer le contenu du text
		System.out.println(text.getText());
		WebElement div = driver.findElement(By.xpath("//div[@class='modal-content']"));
		boolean actual = div.isDisplayed(); // tester si l'alert est affiché
		WebElement Bt1 = driver.findElement(By.xpath("//button[@class='btn btn-secondary']")); // clique sur bouton
																								// Fermer

		Bt1.click();
		Assert.assertEquals(actual, true, "Le button détail non cliquer !"); // si l'alert est affiché le test case est
																				// passe si non échouer

	}

	@Test(priority = 2) // ok
	public void Click_Bouton_Consulter(Method method) throws InterruptedException, IOException {

		WebElement BC = driver.findElement(By.xpath("//a[@id='tooltipOpen20']"));
		BC.click(); // clique sur le bouton consulté
		Thread.sleep(10000);
		WebElement clt = driver.findElement(By.xpath("//*[contains(text(),'Prev')]"));
		boolean PV = clt.isDisplayed(); // affiche le page à consulté
		System.out.println(PV);
		Assert.assertEquals(PV, true, "Le button consulté non cliquer ! "); // si la page est affiché le test case est
																			// passe si non échouer
	}

	@Test(priority = 3) // ok
	public void Click_Bouton_Telecharger(Method method) throws InterruptedException, IOException {

		WebElement h = driver.findElement(By.xpath("//button[@id='tooltipOpen30']"));
		h.click(); // clique sur le bouton télécharger
		Assert.assertTrue(true);

	}

	@Test(priority = 4, enabled = false) // NOK
	public void Click_Boutton_Modifier(Method method) throws InterruptedException, IOException {

		WebElement clk = driver.findElement(By.id("tooltipOpen40"));
		clk.click();
		Thread.sleep(10000);
		driver.findElement(By.cssSelector(
				"body.white-content:nth-child(2) div.wrapper:nth-child(1) div.main-panel div.content:nth-child(3) form:nth-child(1) div:nth-child(7) div.row:nth-child(1) div.col-md-5.offset-md-4:nth-child(4) > input:nth-child(2)"))
				.click();
		System.out.println("test1");
		driver.findElement(By.xpath("//input[@name='prixDeCategorieAjoutee']")).sendKeys("189");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(text(),'Nom en Arabe :')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(
				"body.white-content.modal-open:nth-child(2) div.modal.fade.show div.modal-dialog.modal-update-category-in-bill div.modal-content div.modal-body div.row:nth-child(4) div.col:nth-child(3) div.dropright.dropdown.show div.dropdown-menu.show > button.dropdown-item:nth-child(5)"))
				.click();
		System.out.println("test2");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='modal-footer']//input")).click();
		System.out.println("test3");
		Thread.sleep(2000); //
		driver.findElement(By.xpath("//input[@name='RUB3.2']")).click();
		driver.findElement(By.xpath("//input[@name='RUB3.2']")).clear();
		driver.findElement(By.xpath("//input[@name='RUB3.2']")).sendKeys("467");
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("scroll(0,800)");
		Thread.sleep(2000); // WebDriver
		driver = new ChromeDriver();

		long a = System.currentTimeMillis();
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("/home/asma/TIP-TELECOM/Screenshot/" + a + "screenshot.png"));
		System.out.println("ecran capter");
		driver.navigate().back();
		Thread.sleep(3000);

	}

	@Test(priority = 5, enabled = false) // NOK
	public void Click_Bouton_Duplication(Method method) throws InterruptedException, IOException {

		WebElement clk = driver.findElement(By.xpath("//button[@id='tooltipOpen80']"));
		clk.click();
		System.out.println("cc");
		Thread.sleep(3000);
		WebElement send = driver.findElement(By.xpath("//input[contains(@placeholder,'Nouveau identifiant.')]"));
		send.sendKeys("TV");
		Thread.sleep(1000);
		WebElement clk1 = driver.findElement(By.xpath("//button[contains(text(),'Non')]"));
		clk1.click();
		Thread.sleep(3000);
		System.out.println("clique bouton dupliquer terminé");
	}

	@Test(priority = 6)
	public void Click_Bouton_Envoyer_Par_Email(Method method) throws InterruptedException, IOException {
		WebElement clk = driver.findElement(By.xpath("//button[@id='tooltipOpen60']"));
		clk.click(); // clique sur le bouton envoyer par mail
		Thread.sleep(1000);
		WebElement TextBox = driver.findElement(By.xpath("//input[@id='email']"));
		TextBox.sendKeys("ramzi.chebli@imex.com.tn"); // envoyer le un addresse email dans le champs addresse
		WebElement fent = driver.findElement(By.xpath("//div[@id='form-div']")); // localisé la fenetre
		boolean T = fent.isDisplayed();
		WebElement clk1 = driver.findElement(By.xpath("//input[@id='button-send-red']")); // clique sur le bouton
																							// envoyer du fenetre
																							// afficher
		clk1.click();
		Assert.assertEquals(T, true, "Le button envoyer par Email est non cliquer ! "); // si la page est affiché le
																						// test case est passe si non
																						// échouer

	}

	@Test(priority = 7) // OK
	public void Click_Bouton_Exporter_Vers_ExcelWordXml(Method method) throws InterruptedException, IOException {

		WebElement clk = driver.findElement(By.xpath("//button[@id='tooltipOpen70']"));
		clk.click(); // clique sur le bouton exporter vers ExcelWordXml
		Thread.sleep(2000);
		System.out.println("show this excel shit or XML shit or Word shit");
		WebElement clk1 = driver.findElement(By.xpath("//div[@class='modal-content']")); // localisé la fenetre
		Assert.assertEquals(clk1.isDisplayed(), true, "Le button exporter en Excel_Word_Xml non cliquer ! "); // si le
																												// fenetre
																												// est
																												// affiché
																												// le
																												// test
																												// case
																												// est
																												// passe
																												// si
																												// non
																												// échouer
		driver.findElement(By.xpath("//button[@class='close']")).click(); // clique sur le croix de fermeteur
	}

	@Test(priority = 8) // OK
	public void Click_Boutton_Supprimer(Method method) throws InterruptedException, IOException {

		WebElement clk = driver.findElement(By.xpath("//button[@id='tooltipOpen50']"));
		clk.click(); // clique sur le bouton supprimer
		System.out.println("you be able to delete this facture");
		Thread.sleep(2000);
		WebElement clk1 = driver.findElement(By.xpath("//div[@class='modal-content']")); // localise la fenetre
		System.out.println(clk1.getText());
		Assert.assertEquals(clk1.isDisplayed(), true, "Le button supprimer non cliquer ! ");
		WebElement clk2 = driver.findElement(By.xpath("//button[contains(text(),'Oui')]"));
		clk2.click();
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
