package com.goeuro.framework;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ApplicationManager {
	
	public  WebDriver driver;
	public  String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	public NavigationHelper navigationHelper;
	public SearchResultsHelper searchResultsHelper;
	public final int ASCENDINGORDER = 1;
	public final int DESCENDINGORDER = 0;
	public final int TRAIN = 1;
	public final int AIR = 2;
	public final int BUS = 3;

	public ApplicationManager() {
		    driver = new FirefoxDriver();
		    baseUrl = "http://www.goeuro.com/";
		    WebDriverWait wait = new WebDriverWait(driver, 15); 
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    navigationHelper = new NavigationHelper(this);
		    searchResultsHelper = new SearchResultsHelper(this);
	}
	
	public void stop() {
		 driver.quit();
		    
	}}
	



	