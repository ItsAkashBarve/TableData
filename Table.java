package TableData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Table {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		Properties properties = new Properties();
		FileInputStream inputstream = new FileInputStream("Data.properties");
		properties.load(inputstream);
		driver.get(properties.getProperty("URL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		System.out.println("Page title is: " + driver.getTitle());

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 300)");

		String Firstname = properties.getProperty("firstname");
		String LastName = properties.getProperty("lastname");
		String Email = properties.getProperty("email");
		String Mobile = properties.getProperty("mobile");
		String Address = properties.getProperty("address");

		WebElement First = driver.findElement(By.id("firstName"));
		First.sendKeys(Firstname);

		WebElement Last = driver.findElement(By.id("lastName"));
		Last.sendKeys(LastName);

		WebElement mail = driver.findElement(By.id("userEmail"));
		mail.sendKeys(Email);

		Thread.sleep(2000);

		driver.findElement(By.xpath("(//label[@class=\"custom-control-label\"])[1]")).click();

		Thread.sleep(3000);

		WebElement Mob = driver.findElement(By.id("userNumber"));
		Mob.sendKeys(Mobile);
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0, 400)");

		driver.findElement(By.xpath("(//label[@class=\"custom-control-label\"])[4]")).click();

		WebElement Adres = driver.findElement(By.id("currentAddress"));
		Adres.sendKeys(Address);

		js.executeScript("window.scrollBy(0, 200)");

		driver.findElement(By.id("submit")).click();

		Thread.sleep(3000);

		List<WebElement> allHeaders = driver.findElements(
				By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']/thead"));
		System.out.println(allHeaders.size());

		for (WebElement ele : allHeaders) {
			String value = ele.getText();
			System.out.println(value);
		}

		
		
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Create a unique name for the screenshot
        String timestamp = new SimpleDateFormat("_yyyy-MM-dd_HH-mm-ss").format(new Date());
        String screenshotName = "Table data" + timestamp + ".png";
        File destination = new File("C:\\Eclipse workspace\\AutomationExercise2\\" + screenshotName);

        // Save the screenshot
        FileUtils.copyFile(screenshot, destination);
        System.out.println("Screenshot saved as: " + screenshotName);


		
		
		
		
		
		// WebElement table = driver.findElement(By.xpath("//table[@class='table
		// table-dark table-striped table-bordered table-hover']/tbody/tr")); // Adjust
		// the selector

		List<WebElement> alldata = driver.findElements(
				By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']/tbody/tr"));
		System.out.println(alldata.size());

		if (alldata.size() >= 1) {

			WebElement Row = alldata.get(4);

			List<WebElement> cells = driver.findElements(By
					.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']/tbody/tr/td"));
			System.out.println(cells.size());

			if (cells.size() >= 1) {
				// Get the third cell (index 2)
				String cellText = cells.get(9).getText();
				System.out.println("The data is: " + cellText);

			} else {
				System.out.println("Row does not contain enough columns.");
			}
		} else {
			System.out.println("The table does not contain enough rows.");
		}

		
		
		
		/*
		List<WebElement> alldata = driver.findElements(
				By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']/tbody/tr"));
		System.out.println(alldata.size());

		
		 for(WebElement ele:alldata) {
		 String value = ele.getText();
		 System.out.println(value);
		 }
      
      */
		
		driver.quit();
	}
	

}
