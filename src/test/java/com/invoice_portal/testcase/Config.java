package com.invoice_portal.testcase;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.invoice_portal.BaseTest.TestBase;
import com.relevantcodes.extentreports.ExtentReports;

public class Config extends TestBase {
	public Config() {
		super();
	}
	@BeforeSuite
	public void Start() {
		extent = new ExtentReports("/home/chebli/eclipse-workspace/TIP-TELECOM/Test_Reports/index.html", true);
		extent.addSystemInfo("OS", "UBUNTU");
		extent.addSystemInfo("Owner", "CHEBLI Ramzi");
		extent.addSystemInfo("Test Name", "Invoice Portal");
		extent.addSystemInfo("Languge", "Sélénuim JAVA");
		extent.addSystemInfo("Framework Design", "TestNG");

	}

	@AfterSuite
	public void End() {
		extent.flush();

	}
}
