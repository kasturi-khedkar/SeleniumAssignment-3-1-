import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PopUpWindowHandle {

	WebDriver driver;
	String url="http://popuptest.com/goodpopups.html";

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

		driver.findElement(By.linkText("Good PopUp #1")).click();

		Set<String>handle=driver.getWindowHandles();	//					// Two Window IDs will be available now

		Iterator<String>s=handle.iterator();								// as set does not store obj in form of indexes, use iterator

		String parentWin=s.next();											// stores Parent Window Value
		String childWin=s.next();											// stores Child Window Value

		driver.switchTo().window(childWin);									// Switch from Parent to Child Window

		System.out.println("Child Window Title : "+driver.getTitle());
		driver.close();														// close will close only child window

		driver.switchTo().window(parentWin);								// Switching to parent window
		System.out.println("Parent Window Title : "+driver.getTitle());			// print title to check if the control is back to parent window

	}

}
