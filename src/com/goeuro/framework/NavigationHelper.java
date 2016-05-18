package com.goeuro.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.goeuro.tests.SearchData;

public class NavigationHelper extends HelperBase {

	public NavigationHelper(ApplicationManager manager) {
		super(manager, driver);
	}

	public void openHomePage() {
	manager.driver.get(manager.baseUrl);
	}

	public void fillFromToFields(ApplicationManager applicationManager, SearchData search) {
		manager.driver.findElement(By.id("from_filter")).click();
	    manager.driver.findElement(By.id("from_filter")).clear();
	    manager.driver.findElement(By.id("from_filter")).sendKeys(search.from);
	    manager.driver.findElement(By.id("from_filter")).sendKeys(Keys.ENTER);
		manager.driver.findElement(By.id("to_filter")).click();
	    manager.driver.findElement(By.id("to_filter")).clear();
	    manager.driver.findElement(By.id("to_filter")).sendKeys(search.to);
	    manager.driver.findElement(By.id("to_filter")).sendKeys(Keys.ENTER);
	    manager.driver.findElement(By.id("departure_date")).click();
	    manager.driver.findElement(By.linkText("25")).click();    
	}

}
