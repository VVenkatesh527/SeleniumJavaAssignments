package com.amazon.dev.FirstAssignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
	
	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait =null;
	public static FileInputStream inputStream = null;
	private static final String defaultPropertiesFile = System.getProperty("user.dir")+"//config.properties";

	public BasePage() {
		
		File file = new File(defaultPropertiesFile);
		prop = new Properties();
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static WebDriver openBrowser() {
		
		String browser = prop.getProperty("browser");
		
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("edge")){
			driver = new EdgeDriver();
		}else if(browser.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
	
	public static void JSClick(WebElement element) {

		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].click()", element);

	}

	public static void JSSendKeys(WebElement element, String str) {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].value=arguments[1] ", element, str);
	}
	
	public static void elementIsDisplayed(WebElement element,long Seconds) {
			
			wait = new WebDriverWait(driver, Duration.ofSeconds(Seconds));
			wait.until(ExpectedConditions.visibilityOf(element));
	}
}
