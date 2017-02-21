package org.mybatis.jpetstore.cucumber;


import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.mybatis.jpetstore.selenium.VideoRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class LogOutSteps {
	
	  private WebDriver dr;
	  private String baseUrl;
	  private VideoRecord recorder;
	  WebElement userName;
	  WebElement passWord;

	 
	  @Given("^navigate to jpetstore page to Log in and logout$")
	  
	  public void navigate(){
	  
		  System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		    dr = new ChromeDriver();
		    dr.manage().window().maximize();
		    baseUrl = System.getenv("JSTOREURL");
		    dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	         dr.get(baseUrl);         
	        // dr.findElement(By.name("signon")).click();
	         }
	 
	  
	  @When("^user logged in using username And password  And log out$")
	  public void user_logged_in_using_username_And_password_And_log_out() throws Throwable {
		 
		  dr.findElement(By.name("username")).sendKeys("j2ee");
		  dr.findElement(By.name("password")).sendKeys("j2ee");
		  dr.findElement(By.name("signon")).click();
		  
	         dr.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	         Thread.sleep(2000);
	         dr.findElement(By.linkText("Sign Out")).click();
	  }
	  
	  
	  
	  
	  @Then("^log in  page should be displayed with$")
	  
	  public void verifySuccessful(){
	  
	        String expectedText="Sign In";
	        WebElement link  = dr.findElement(By.linkText("Sign In"));
	        String actualText=   link.getText();
	 
	        Assert.assertTrue("Sign In",expectedText.equals(actualText));
			 dr.quit();
	        }

	
	
}
