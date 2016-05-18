package com.goeuro.framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SearchResultsHelper extends HelperBase {

	public SearchResultsHelper(ApplicationManager manager) {
		super(manager, driver);
	}

	public List<Float> getSearchData (int vehicle) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(manager.driver, 15);  
		List<Float> searchresults = new ArrayList<Float>();
		List<WebElement> priceboxes = null;
		String myxpath = new String();
		
		//picking right tab depending on vehicle
		switch (vehicle)
		{
		case 1 :
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//dd[@id='tab_train']/div[2]")));
		    manager.driver.findElement(By.xpath("//dd[@id='tab_train']/div[2]")).click();
		    myxpath = ".//*[@id='results-train']//span[@class='price-no ']";
		    break;
		case 2:
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//dd[@id='tab_flight']/div[2]")));
		    manager.driver.findElement(By.xpath("//dd[@id='tab_flight']/div[2]")).click();
		    myxpath = ".//*[@id='results-flight']//span[@class='price-no ']";
		    break;
		case 3:
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//dd[@id='tab_bus']/div[2]")));
		    manager.driver.findElement(By.xpath("//dd[@id='tab_bus']/div[2]")).click();
		    myxpath = ".//*[@id='results-bus']//span[@class='price-no ']";
		    break;
		}
		
		if(manager.driver.findElements(By.xpath(myxpath)).size() != 0)//if we have search results - proceed
		{
		while (manager.driver.findElements(By.linkText("Next")).size() != 0)//switch between pages until they exist
		{
	
			priceboxes = manager.driver.findElements(By.xpath(myxpath));		 
			for (WebElement pricebox : priceboxes) {
			Float result;
			//getting rid of currency and , in numbers more than 1000
			result=Float.parseFloat(pricebox.getText().split(" ")[1].replace(",", ""));
			searchresults.add(result);
		}
			
		manager.driver.findElement(By.linkText("Next")).click();
		Thread.sleep(1000);
		}	
		}
		else
			searchresults = null;
		return searchresults;
	}

	public void performSearch() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(manager.driver, 15); 
		wait.until(ExpectedConditions.elementToBeClickable(By.id("search-form__submit-btn"))); 
		Thread.sleep(2000);
		manager.driver.findElement(By.id("search-form__submit-btn")).click();
		Thread.sleep(2000);
	}

	public void orderSearchResults(List<Float> copy, int order) throws InterruptedException {
	    if (copy != null)
	    {
	    	// ascending order
	    	if (order == 1) Collections.sort(copy);
	    	//descending order
	    	if (order == 0)Collections.reverse(copy);
	    }
	    else
	    	System.out.println("Data for sorting is empty");	  
	}

	public void analyzeResultsByVehicle(ApplicationManager applicationManager, int vehicle, int order) throws InterruptedException {
		List<Float> result=getSearchData(vehicle);
		List<Float> orderedcopy = manager.searchResultsHelper.getCopyData(result);
		switch (order)
		{
		
			case 1:
			orderSearchResults(result, applicationManager.ASCENDINGORDER);
			break;
			
			case 0:
			orderSearchResults(result, applicationManager.DESCENDINGORDER);
			break;
		}
			
		manager.searchResultsHelper.compareResults(result, orderedcopy);
	}

	public void compareResults(List<Float> searchresults, List<Float> orderedsearchresults) {
		if(searchresults != null && orderedsearchresults != null)  
		    Assert.assertEquals(searchresults, orderedsearchresults);
	}

	public List<Float> getCopyData(List<Float> source) {
		List<Float> copy = null;
		if(source != null)  
		{
			copy = new ArrayList<Float>(source.size());
			for(Float result : source) {
		        copy.add(new Float(result));
		    }
		}
		else
			System.out.println("Initial search result is empty");
		return copy;
	}

}
