package Assessment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class AutomationAssessment1 {

	public static void main(String[] args) throws InterruptedException {
		/*
		 * Task Details:
		 * Navigate to the FitPeo Homepage:
		 * Open the web browser and navigate to FitPeo Homepage. "https://www.fitpeo.com/"
		 * Navigate to the Revenue Calculator Page:
		 * From the homepage, navigate to the Revenue Calculator Page.  "https://fitpeo.com/revenue-calculator"
		 * Scroll Down to the Slider section:
		 * Scroll down the page until the revenue calculator slider is visible.

		 */
		
	//1. Opening a new Chrome Browser
		WebDriver driver = new ChromeDriver();
		
	//2. Adding an implicit wait time so that there all the elements are given sufficient time to get located.
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	//3.Maximizing the browser window to full screen
		
		driver.manage().window().maximize();
		
	//4.Navigating to the Fitpeo HomePage:
		
		driver.get("https://www.fitpeo.com/");
		
	//5.Navigating to the Revenue calculator page by locating the element using relative x-path
		
		driver.findElement(By.xpath("//div[contains(text(),'Revenue Calculator')]")).click();
		
	//6.Scrolling through the page by using 
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		WebElement sliderHeading = driver.findElement(By.xpath("//div[@class=\"MuiBox-root css-79elbk\"]/h4"));
		
		js.executeScript("arguments[0].scrollIntoView();",sliderHeading);
	
		Thread.sleep(1000);
		
	//7.Adjusting the slider to set the value to 820
		
		Actions act = new Actions(driver); //Creating an Action class Object and storing it into a variable. This Action class contains methods to handle sliders.
		
		WebElement slider = driver.findElement(By.xpath("//input[@aria-orientation='horizontal']"));
		
		System.out.println(slider.getLocation()); // getting the current coordinates of the slider in order to move it - (803, 654)
		
		//since the slider has only has a movement in the x-axis, we will fix the y-axis and change the x-axis to get it to desired value
		
	
		act.dragAndDropBy(slider, 94, 0).perform(); // the value may vary +5 since it is by default as per my understanding.
		
		System.out.println(slider.getLocation());
		
	//8. Setting the value of the silder text to 560  - Not working
		
		Thread.sleep(2000);
		
		WebElement sliderTextBox = driver.findElement(By.xpath("//input[@id=\":r0:\"]"));
		
		System.out.println(sliderTextBox.getAttribute("value"));
		
		sliderTextBox.clear();
		
		Thread.sleep(1000);
		
		sliderTextBox.click();
		
		Thread.sleep(1000);
		
		sliderTextBox.sendKeys("560");
		
	
	//8. Select CPT Codes:
		//Scroll down further and select the checkboxes for CPT-99091, CPT-99453, CPT-99454, and CPT-99474.
		
		
		WebElement CPTHeading = driver.findElement(By.xpath("//p[normalize-space()='CPT-99091']"));
		js.executeScript("arguments[0].scrollIntoView();",CPTHeading);
		//Used a looping statement to check only the desired checkBoxes
		for(int checkBox = 1; checkBox<=8; checkBox++)
		{
			if(checkBox == 4 || checkBox == 5 || checkBox == 6 || checkBox == 7)
			{
				continue;
			}
			
			driver.findElement(By.xpath("//div[@class='MuiBox-root css-1p19z09']/div["+checkBox+"]/label/span/input")).click();
	
		}
	
	WebElement TotalRecReimb = driver.findElement(By.xpath("//div[@class=\"MuiBox-root css-m1khva\"]/p[2]"));
	
	System.out.println("Total Recurring Reimbursement Value = " +TotalRecReimb.getText()); // This will print out the Total Recurring Reimbursement value
	
	
	//9. Validation -  Validating the Total Recurring Reimbursement Value for all patient per month (Considering 820 patients)
	
		WebElement TotalPatients = driver.findElement(By.xpath("//div[@class='MuiBox-root css-19xu03j'][2]/p[2]"));
		
		if(TotalRecReimb.getText().equals("$110700"))
		{
			System.out.println("Yes exactly 820 patients have been seleected!");
		}
		
		else 
		{
			System.out.println("Sorry, I guess there is a deviation in the total number of patients selected. You have selected " +TotalPatients.getText()+ " patients" );
		}
		
		
	//10. Scroll up back to the revenue screen:
		
		
		js.executeScript("arguments[0].scrollIntoView();",sliderHeading);
		
	}

}
