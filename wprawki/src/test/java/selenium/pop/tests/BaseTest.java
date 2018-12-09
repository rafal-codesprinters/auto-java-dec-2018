package selenium.pop.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BaseTest {

	protected WebDriver driver;

	@BeforeEach
	public void openBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@AfterEach
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}

}
