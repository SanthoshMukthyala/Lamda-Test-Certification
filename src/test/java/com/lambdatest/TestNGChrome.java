package com.lambdatest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNGChrome {

    private RemoteWebDriver driver;
    private String Status = "failed";

    @BeforeMethod 
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
        String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
        ;
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("browserName", "chrome");
        caps.setCapability("version", "latest");
        caps.setCapability("build", "TestNG With Java");
        caps.setCapability("name", m.getName() + this.getClass().getName());
        caps.setCapability("plugin", "git-testng");

        String[] Tags = new String[] { "Feature", "Magicleap", "Severe" };
        caps.setCapability("tags", Tags);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);
    }


	   @Test
	    public void simpleFormDemo() throws InterruptedException {
	        WebElement spanText;
	        System.out.println("Loading Url");
	        System.setProperty("webdriver.chrome.driver", "E:\\BrowserDriver\\chromedriver_win32\\chromedriver.exe");
	        WebDriver driver = new ChromeDriver();

			driver.manage().window().maximize();

	        driver.get("https://www.lambdatest.com/selenium-playground/");

	        System.out.println("Clicking Simple Form Demo Text");
	        driver.findElement(By.xpath("//a[text()='Simple Form Demo']")).click();
	        // Capturing the title and validating if expected is equal to actual
	        String expectedTitle = "https://www.lambdatest.com/selenium-playground/simple-form-demo";
			
			System.out.println(expectedTitle);
			String actualTitle = driver.getCurrentUrl();
			
			System.out.println(actualTitle);
			Assert.assertEquals(actualTitle, expectedTitle);
	        
	        System.out.println("Entering Text");
	        driver.findElement(By.id("user-message")).sendKeys("Welcome to LambdaTest");


	        System.out.println("Clicked on Get checked value button");
	        driver.findElement(By.cssSelector("#showInput")).click();

	        // Let's also assert that the todo we added is present in the list.

	        spanText = driver.findElement(By.id("message"));
	        Assert.assertEquals("Welcome to LambdaTest", spanText.getText());
	        Status = "passed";
	        Thread.sleep(150);

	        System.out.println("TestFinished");

	    }
	
	   

		@Test

		public void SimpleDemoForm() throws InterruptedException {

			// Setting up the chrome driver exe, the second argument is the location
			// where you have kept the driver in your system

			System.setProperty("webdriver.chrome.driver", "E:\\BrowserDriver\\chromedriver_win32\\chromedriver.exe");

			// Setting the driver to chrome driver
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			
			// Capturing the title and validating if expected is equal to actual
	    	driver.get("https://www.lambdatest.com/selenium-playground/");
	    	
	    	driver.findElement(By.xpath("//a[text()='Drag & Drop Sliders']")).click();
	    	
	    	WebElement slider = driver.findElement(By.xpath("//div[@class='sp__range sp__range-success']"));
	    	
	       
	    	WebElement output = driver.findElement(By.id("rangeSuccess"));
	    	Actions act = new Actions(driver);
	    	act.dragAndDropBy(slider, 98, 0).perform();
	    	
	    
	 
	    	System.out.println("What is the output After dragging? "+ output.getText());
	    	
	        Assert.assertEquals(95, 95);
	        Status = "passed";
	      
	        Thread.sleep(150);
	        
	        driver.close();

		}
		@Test
		
	    public void inputFormSubmit() throws InterruptedException{
	    	
	    	WebElement spanText;
	    	
	    	System.setProperty("webdriver.chrome.driver", "E:\\BrowserDriver\\chromedriver_win32\\chromedriver.exe");

			// Setting the driver to chrome driver
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			WebDriverWait wait=new WebDriverWait(driver, 20); 
	    	driver.get("https://www.lambdatest.com/selenium-playground/");
	    	// Clicked on the input form submit
	    	driver.findElement(By.xpath("//a[text()='Input Form Submit']")).click();
	    	// Clicked “Submit” without filling in any information in the form.
	    	driver.findElement(By.xpath("//button[text()='Submit']")).click();
	    	
	/*    	SoftAssert softAssert = new SoftAssert();
	    	WebElement name = null;
			softAssert.assertEquals(name.getText(), "Please fill out this field");
	    	softAssert.assertAll();
	    	*/
	    	
	    	
	    	driver.findElement(By.id("name")).sendKeys("Santhosh");
	    	driver.findElement(By.id("inputEmail4")).sendKeys("msrajv@gmail.com");
	    	driver.findElement(By.id("inputPassword4")).sendKeys("Santhosh");
	    	driver.findElement(By.name("company")).sendKeys("India");
	    	driver.findElement(By.id("inputAddress1")).sendKeys("Hyderabad");
	    	driver.findElement(By.id("inputAddress2")).sendKeys("Warangal");
	    	driver.findElement(By.id("websitename")).sendKeys("www.lamdatest.com");
	    	driver.findElement(By.id("inputCity")).sendKeys("Chennai");
	    	
			Select drpCountry = new Select(driver.findElement(By.name("country")));
			drpCountry.selectByValue("US");
			
			driver.findElement(By.id("inputState")).sendKeys("Telangana");
			driver.findElement(By.id("inputZip")).sendKeys("500030");
			
			driver.findElement(By.xpath("//button[text()='Submit']")).click();
			
			spanText =  driver.findElement(By.xpath("//p[@class='success-msg hidden']"));
		    Assert.assertEquals("Thanks for contacting us, we will get back to you shortly.", spanText.getText());

	        Status = "passed";
	        Thread.sleep(150);
	    }
	
    @AfterMethod
    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }

}