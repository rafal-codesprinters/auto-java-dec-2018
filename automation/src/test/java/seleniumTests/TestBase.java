package seleniumTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.util.concurrent.TimeUnit.*;

public class TestBase {

	protected WebDriver driver;

	@BeforeEach
	public void startDriver(){
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, SECONDS);

	}

	@AfterEach
	public void closeDriver(){
		driver.quit();
	}


}
