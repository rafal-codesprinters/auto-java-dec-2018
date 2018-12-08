package seleniumTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {

	protected WebDriver driver;

	@BeforeEach
	public void startDriver(){
		driver = new ChromeDriver();
	}

	@AfterEach
	public void closeDriver(){
		driver.quit();
	}


}
