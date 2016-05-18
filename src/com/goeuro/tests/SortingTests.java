package com.goeuro.tests;

import java.util.List;
import java.util.regex.Pattern;

import org.testng.Reporter;
import org.testng.annotations.*;
import static org.testng.Assert.*;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class SortingTests extends TestBase {
  @Test
  public void testResultsSortAscending() throws Exception {
	    app.navigationHelper.openHomePage();
	    SearchData search = new SearchData("Berlin", "Dresden");
		app.navigationHelper.fillFromToFields(app, search);
	    app.searchResultsHelper.performSearch();  
	    app.searchResultsHelper.analyzeResultsByVehicle(app, app.TRAIN, app.ASCENDINGORDER);
	    app.searchResultsHelper.analyzeResultsByVehicle(app, app.AIR, app.ASCENDINGORDER);
	    app.searchResultsHelper.analyzeResultsByVehicle(app, app.BUS, app.ASCENDINGORDER);

  }
  
  @Test
  public void testResultsSortDescending() throws Exception {
	    app.navigationHelper.openHomePage();
	    SearchData search = new SearchData("Berlin", "Dresden");
		app.navigationHelper.fillFromToFields(app, search);
	    app.searchResultsHelper.performSearch();  
	    app.searchResultsHelper.analyzeResultsByVehicle(app, app.TRAIN, app.DESCENDINGORDER);
	    app.searchResultsHelper.analyzeResultsByVehicle(app, app.AIR, app.DESCENDINGORDER);
	    app.searchResultsHelper.analyzeResultsByVehicle(app, app.BUS, app.DESCENDINGORDER);
	  }
} 