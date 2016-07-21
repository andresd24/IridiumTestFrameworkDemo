package SeleniumTests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.List;

public class SeleniumSuite  {

	
   	 private List<WebElement> listOfPuppiesPosts = null; 
 	 private String listOfPuppiesPostsXPath =   "//div[@class='puppy_list']/div";
 	 private String getPuppyPostAtNamePositionByString =   "//div[@class='puppy_list'][%1$d]/div/div[@class='name']/h3";
 	 private String getPuppyPostViewAtPositionByString =   "//div[@class='puppy_list'][%1$d]/div/div[@class='view']/form/div/input[@class='rounded_button']";
	
	 WebDriver driver = null;

	 private List<WebElement> getListOfPuppiePosts(WebDriver webDriver)
	 {
		 List<WebElement> listOfPuppiesPosts = webDriver.findElements(By.xpath(listOfPuppiesPostsXPath));
		 
		 this.listOfPuppiesPosts = listOfPuppiesPosts;
		 
		 return listOfPuppiesPosts;
	 }
	 
	 private int getPositionOfDogByName(WebDriver webDriver, String name)
	 {
		 int position = -1;
		 for (int i = 1; i <= this.listOfPuppiesPosts.size(); i++)
		 {
			 try
			 {
				 WebElement nameDiv = webDriver.findElement(By.xpath(String.format(getPuppyPostAtNamePositionByString, i)));
				 String currentName = nameDiv.getText();
						 
				 if (currentName.toLowerCase().equals(name.toLowerCase()))
				 {
					 position =  i;
					 return position;
				 }
			 }
			 catch (Exception ex)
			 {
				 System.out.println(ex.toString());
			 }
			 
			 
		 }
		 return position;
		 
	 }
	 
	  private  void initializeTests(String strUrl) 
	  {
	    	ChromeOptions options = new ChromeOptions();
	    	options.addArguments("--start-maximized");
	        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	        
        	try
	        {
        	   this.driver =  new ChromeDriver();
        	this.driver.get(strUrl);
	        	
	        } catch (Exception e) { 
	        	throw e;
	        } 
	        
	        if (this.driver == null) {
	        	System.out.println("null driver");
	        }
	  }   
	  
	  @Test
	  public void AdoptAPuppieTest() {
		  initializeTests("http://puppies.herokuapp.com/");
		  
		  
	        this.listOfPuppiesPosts = getListOfPuppiePosts(this.driver);
	        int positionOfPuppieToAdopt = getPositionOfDogByName(this.driver, "Hanna");
	        
	        WebElement webElement = driver.findElement(By.xpath(String.format(getPuppyPostViewAtPositionByString, positionOfPuppieToAdopt)));
	        webElement.click();
	  }

	  
	  
//	  "http://puppies.herokuapp.com/"
	
}
