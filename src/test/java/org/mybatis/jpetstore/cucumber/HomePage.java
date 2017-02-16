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



public class HomePage {
	
	  private WebDriver dr;
	  private String baseUrl;
	  private VideoRecord recorder;
	  WebElement userName;
	  WebElement passWord;

	 
	  @Given("^navigate to jpetstore page to Log inn$")
	  
	  public void navigate(){
	  
		  System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		    dr = new ChromeDriver();
		    dr.manage().window().maximize();
		    baseUrl = "http://jp-dev.salientcrgt-devops.com/jpetstore/actions/Account.action?signonForm=";
		    dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	         dr.get(baseUrl);         
	        // dr.findElement(By.name("signon")).click();
	         }
	  @When("^user logged inn using username And password$")
	  public void user_logged_in_using_username_And_password() throws Throwable {
		  String className = this.getClass().getName();
		  recorder=new VideoRecord();
			 recorder.startRecording(className,"verify Log in operation ");	
		 dr.findElement(By.name("username")).sendKeys("j2ee");
		 dr.findElement(By.name("password")).sendKeys("j2ee");	 
		 dr.findElement(By.name("signon")).click();
		  
	         dr.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	         Thread.sleep(2000);
	         recorder.stopRecording();
	  }
	 
	  @Then("^home page shouldn be displayed$")
	  
	  public void verifySuccessful(){
	  
	        String expectedText="Sign Out";
	        WebElement link  = dr.findElement(By.linkText("Sign Out"));
	        String actualText=   link.getText();
	 
	        Assert.assertTrue("Sign Outt",expectedText.equals(actualText));
	        dr.quit();
	        }

	
	
}
