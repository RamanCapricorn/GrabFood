package Com.Testcases.RestaurantDetail;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Com.base.BaseTest;

public class LoadRestaurants extends BaseTest {
	
	int c = 0;

	@BeforeSuite
	public void setProperties(){
		propertiesFile = "RestaurantDetail";
	}

	@BeforeMethod
	public void Testbefore() throws IOException, InterruptedException, AWTException {

		if (c == 0) {
			TestBeforeMethod();
			Set<String> windIDs = driver.getWindowHandles();
			if(windIDs.size()>1) {
				closeAllBrowsersButOrg();
			}
		}
		c = c + 1;
	}

	@AfterMethod
	public void afterMethod() {

		if (c == 0 || c == RowsCount()) {
			TestAfterMethod();
		}
	}
	
	@Test(dataProvider = "Test_LoadRestaurant", dataProviderClass = DataProviderClass.class)
	public void Test_LoadRestaurant(Hashtable<String, String> data)	throws InterruptedException, ClassNotFoundException, ParseException {
		try {
			ImplicitWait();
			testCaseName = "Test_LoadRestaurant";
			Date d = new Date();

			String TestCaseName = data.get("TestcaseName") + "_" + data.get("Environment") + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
			String TestCaseDesc = data.get("TestcaseName") + "_" + data.get("Environment");
			String TestCaseComDesc = data.get("TestcaseName") + "_" + data.get("Environment");

			StartReport(TestCaseDesc, TestCaseComDesc);
			isTestRunnable(data);

			String PrintValue = customReportName(TestCaseName);

			Thread.sleep(2000);
			waitForElementToBeClickable("location_input");
			click("location_input");
			clear("location_input");
			TypeInField("location_input", data.get("location"));
			Thread.sleep(500);
			List <WebElement> locations = getLocators(GetObjectPath("location_list"));
			if(locations.size()<1) {
				clear("location_input");
				TypeInField("location_input", data.get("location"));
				Thread.sleep(500);
			}
			
			click("location_fourth");
			Thread.sleep(100);
			test.log(LogStatus.PASS, "User is on Grab food home screen");
			CaptureScreen();
			KeyPress("Tab");
			KeyPress("Tab");
			KeyPress("Enter");
			
			waitForElementToBeClickable("PopularNearYou");
			KeyPress("down");
			KeyPress("down");
			KeyPress("down");
			test.log(LogStatus.PASS, "User is on Restaurant screen");
			CaptureScreen();
			
			List <WebElement> restaurants = getLocators(GetObjectPath("LoadRestaurantList"));
			HashMap<Integer, String> initialList = new HashMap<Integer, String>();
			int i;
			for(i=1; i<=restaurants.size(); i++) {
				String restaurantName = driver.findElement(By.cssSelector("div.ant-col-24.RestaurantListCol___1FZ8V.ant-col-md-12.ant-col-lg-6:nth-of-type("+i+") > a > div > div:nth-of-type(2) > h6")).getText();
				initialList.put(i, restaurantName);
			}

			moveToElement("LoadMoreBtn");
			click("LoadMoreBtn");
			Thread.sleep(5000);
			test.log(LogStatus.PASS, "User Load More Restaurants.");
			CaptureScreen();
			moveToElement("LoadMoreBtn");
			Thread.sleep(2000);
			
			restaurants = getLocators(GetObjectPath("LoadRestaurantList"));
			System.out.println("After load value of i: "+ i);
			for(; i<=restaurants.size(); i++) {
				String restaurantName = driver.findElement(By.cssSelector("div.ant-col-24.RestaurantListCol___1FZ8V.ant-col-md-12.ant-col-lg-6:nth-of-type("+i+") > a > div > div:nth-of-type(2) > h6")).getText();
				initialList.put(i, restaurantName);
			}
		    
		    Set<Object> uniqueValues = new HashSet<Object>(initialList.values());
		    System.out.println("Initial List: "+ initialList.size() + ", Unique List: "+uniqueValues.size());
		    
		    if (initialList.size()!=uniqueValues.size()) {
	        	System.out.println("Duplicate Restaurant displayed.");
	        	test.log(LogStatus.FAIL, "Duplicate Restaurant displayed.");
	        }
		    else {
		    	System.out.println("All Restaurants are Uniquely displayed.");
		    	test.log(LogStatus.PASS, "All Restaurants are Uniquely displayed.");
		    }
			
			int rowNum = Integer.parseInt(data.get("row"));
			Result_to_Xls(xls, "TestData", rowNum, "Pass", "Result");
//			Result_to_Xls(xls, "TestData", rowNum, "N", "Runmode");


		} catch (Exception e) {

			System.out.println("Error Message:" + e.getMessage());

			if (e.getMessage().equals("Skipping the test as runmode is N")) {
				reportSkip(e.getMessage());
				int rowNum = Integer.parseInt(data.get("row"));
				if (data.get("Result").equals(""))
					Result_to_Xls(xls, "TestData", rowNum, "Skip", "Result");
			} else {
				reportFail(e.getMessage());
				CaptureScreen();
				int rowNum = Integer.parseInt(data.get("row"));
				Result_to_Xls(xls, "TestData", rowNum, "Fail", "Result");
			}
		}
	}
}
