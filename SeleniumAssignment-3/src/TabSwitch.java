import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TabSwitch {

	WebDriver driver;
	String url="http://openclinic.sourceforge.net/openclinic/home/index.php";

	@Before
	public void setUp() throws Exception {

		System.setProperty("webdriver.chrome.driver", "F:\\selenium\\Selenium New Jar Files\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		Thread.sleep(3000);
	}

	@After
	public void tearDown() throws Exception {

		driver.quit();
	}

	@Test
	public void test() {

		String url2=null;
		String tab=Keys.chord(Keys.CONTROL,Keys.RETURN);					
		driver.findElement(By.linkText("Medical Records")).sendKeys(tab);

		Set<String>window=driver.getWindowHandles();
		for(String x:window) {
			driver.switchTo().window(x);
			url2=driver.getCurrentUrl();
		}
			Assert.assertTrue(!(url.equals(url2)));							// Assertion used for Validation
			driver.findElement(By.linkText("Search Patient")).click();

			WebElement value=driver.findElement(By.id("search_type"));

			Select sc=new Select(value);

			sc.selectByVisibleText("First Name");

			driver.findElement(By.id("search_patient")).click();
	}
}
