package selenium.wordpresspageobjects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void startBrowser() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void quitBrowser() {
        driver.quit();
    }
}
