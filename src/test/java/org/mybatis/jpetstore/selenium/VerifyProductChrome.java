package org.mybatis.jpetstore.selenium;


import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.*;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;






public class VerifyProductChrome {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private VideoRecord recorder;


  @Before
  public void setUp() throws Exception {
 
	  
	  System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    baseUrl = "http://jp-dev.salientcrgt-devops.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

  }
  @Test
  public void verifyProductExists() throws Exception {
	  String className = this.getClass().getName();
	  
		 recorder=new VideoRecord();
	 recorder.startRecording(className,"verifyProductExists");	
 driver.get(baseUrl + "/jpetstore/actions/Account.action?signonForm=");
 Thread.sleep(2000);
 driver.findElement(By.name("username")).sendKeys("j2ee");
 driver.findElement(By.name("password")).sendKeys("j2ee");
 driver.findElement(By.name("signon")).click();
 
 Thread.sleep(2000);
 driver.findElement(By.cssSelector("#SidebarContent > a > img")).click();
 Thread.sleep(2000);
WebElement element=driver.findElement(By.linkText("FI-FW-01")); 
String str=element.getText();
if (str.equals("FI-FW-01")){
	 
	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	// File dir=new File("target\\surefire-reports\\"+className);
	FileUtils.copyFile(scrFile, new File("target\\surefire-reports\\"+className+"\\"+"verifyProductExists.png"));
	Thread.sleep(2000);
	recorder.stopRecording();
}
 
 driver.findElement(By.linkText("FI-FW-09")).click(); 
 Thread.sleep(2000);
 driver.findElement(By.linkText("Add to Cart")).click();
 Thread.sleep(2000);
 driver.findElement(By.linkText("Proceed to Checkout")).click();  
 Thread.sleep(2000);
 driver.findElement(By.name("newOrder")).click();   
 Thread.sleep(2000);
 driver.findElement(By.linkText("Confirm")).click();
 Thread.sleep(2000);
// driver.findElement(By.linkText("Sign Out")).click();
 //Thread.sleep(2000);
 recorder.stopRecording();
   
  }
  @Test
  public void verifyTransactionComplete() throws Exception {
	  String className = this.getClass().getName();
	  
		 recorder=new VideoRecord();
	 recorder.startRecording(className,"verifyTransactionComplete");	
 driver.get(baseUrl + "/jpetstore/actions/Account.action?signonForm=");
 Thread.sleep(2000);
 driver.findElement(By.name("username")).sendKeys("j2ee");
 driver.findElement(By.name("password")).sendKeys("j2ee");
 driver.findElement(By.name("signon")).click();
 Thread.sleep(2000);
 driver.findElement(By.cssSelector("#SidebarContent > a > img")).click();
 Thread.sleep(2000);

 
 driver.findElement(By.linkText("FI-FW-01")).click(); 
 Thread.sleep(2000);
 driver.findElement(By.linkText("Add to Cart")).click();
 Thread.sleep(2000);
 driver.findElement(By.linkText("Proceed to Checkout")).click();  
 Thread.sleep(2000);
 driver.findElement(By.name("newOrder")).click();   
 Thread.sleep(2000);
 driver.findElement(By.linkText("Confirm")).click();
 Thread.sleep(2000);
 //driver.findElement(By.linkText("Sign Out")).click();
// Thread.sleep(2000);
 recorder.stopRecording();
   
  }

  @Test
  public void verifyLoginOperation() throws Exception{
	  String className = this.getClass().getName();
	  recorder=new VideoRecord();
		 recorder.startRecording(className,"verify Log in operation ");	
		 driver.get(baseUrl + "/jpetstore/actions/Account.action?signonForm=");
	  driver.findElement(By.name("username")).sendKeys("j2ee");
	  driver.findElement(By.name("password")).sendKeys("j2ee");		 
	  driver.findElement(By.name("signon")).click();
	  
      driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
      Thread.sleep(2000);
      recorder.stopRecording();
  }

  @Test
  public void verifyLogOutOperation() throws Exception{
	  String className = this.getClass().getName();
	  recorder=new VideoRecord();
		 recorder.startRecording(className,"verify Log Out operation ");	
		 driver.get(baseUrl + "/jpetstore/actions/Account.action?signonForm=");
		 driver.findElement(By.name("username")).sendKeys("j2ee");
		 driver.findElement(By.name("password")).sendKeys("j2ee");
		 driver.findElement(By.name("signon")).click();
		  
         driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
         Thread.sleep(2000);
         driver.findElement(By.linkText("Sign Out")).click();
      recorder.stopRecording();
  }
  @After
  public void tearDown() throws Exception {
	  driver.quit();
   
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
