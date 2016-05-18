package com.goeuro.tests;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;

import com.goeuro.framework.ApplicationManager;

public class TestBase {
    
	public ApplicationManager app;

	@BeforeTest(alwaysRun = true)
	public void setUp() throws Exception {
		app = new ApplicationManager();
	  }

	@AfterTest(alwaysRun = true)
	public void tearDown() throws Exception {
		app.stop();	   
	  }

	

}
