package selenium.pageobjects.pagefactory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void startBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void quitBrowser() {
        driver.quit();
    }

    protected String generateRandomEmail() {
        return generateRandomText() + "@testdomain.com";
    }

    protected String generateRandomText() {
        return UUID.randomUUID().toString();
    }
}
