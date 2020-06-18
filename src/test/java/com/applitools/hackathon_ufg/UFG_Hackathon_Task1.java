package com.applitools.hackathon_ufg;


import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class UFG_Hackathon_Task1 {

	public static void main(String[] args) throws InterruptedException {
		// Create a new chrome web driver
		// Add your broser path
		System.setProperty("webdriver.chrome.driver", "Add your browser path here");

		WebDriver webDriver = new ChromeDriver();

		// Create a runner with concurrency of 1
		VisualGridRunner runner = new VisualGridRunner(10);

		// Create Eyes object with the runner, meaning it'll be a Visual Grid eyes.
		Eyes eyes = new Eyes(runner);

		setUp(eyes);

		try {
			
			Task1(webDriver, eyes);

		} catch(Exception e)  {

			System.out.println(e);
		}
		Thread.sleep(5000);
		tearDown(webDriver, runner);
	}

	public static void setUp(Eyes eyes) {

		// Initialize eyes Configuration
		Configuration config = new Configuration();

		// You can get your api key from the Applitools dashboard
		config.setApiKey("Please add your API key");
		

		// create a new batch info instance and set it to the configuration
		config.setBatch(new BatchInfo("UFG Hackathon Task-1"));

		// Add browsers with different viewports
		config.addBrowser(800, 600, BrowserType.CHROME);
		config.addBrowser(700, 500, BrowserType.FIREFOX);
		config.addBrowser(1600, 1200, BrowserType.IE_11);
		config.addBrowser(1024, 768, BrowserType.EDGE);
		config.addBrowser(800, 600, BrowserType.SAFARI);

		// Add mobile emulation devices in Portrait mode
		config.addDeviceEmulation(DeviceName.iPhone_X, ScreenOrientation.PORTRAIT);
		config.addDeviceEmulation(DeviceName.Pixel_2, ScreenOrientation.PORTRAIT);

		// Set the configuration object to eyes
		eyes.setConfiguration(config);

	}
	
	//Task 1

	public static void Task1(WebDriver webDriver, Eyes eyes) throws InterruptedException {

		try {

			
			

			// Call Open on eyes to initialize a test session
			eyes.open(webDriver, "Hackathon App", "Task 1", new RectangleSize(800, 600));
			
			// Navigate to the url we want to test
			//Version-1 URL
			webDriver.get("https://demo.applitools.com/gridHackathonV1.html");
			// ⭐️ Note to see visual bugs, run the test using the above URL for the 1st run.
						// but then change the above URL to https://demo.applitools.com/index_v2.html
						// (for the 2nd run)
		  
			

			eyes.checkWindow("Cross-Device Elements Test");
			
	      	  eyes.closeAsync();
	  		Thread.sleep(5000);
 
		} catch(Exception e)  {

			System.out.println(e);
		}

	}
	
	

	private static void tearDown(WebDriver webDriver, VisualGridRunner runner) {
		// Close the browser
		webDriver.quit();

		// we pass false to this method to suppress the exception that is thrown if we
		// find visual differences
		TestResultsSummary allTestResults = runner.getAllTestResults(false);
		System.out.println(allTestResults);
	}

}





