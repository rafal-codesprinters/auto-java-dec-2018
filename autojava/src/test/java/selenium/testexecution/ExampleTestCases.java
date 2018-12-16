package selenium.testexecution;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ExampleTestCases {

    protected WebDriver driver;

    @BeforeEach
    public void startBrowser() throws InvalidBrowserConfiguration, MalformedURLException {
        if (System.getProperty("grid").equals("true")) {
            String host = System.getProperty("host");
            String port = System.getProperty("port");
            URL url = new URL("http://" + host + ":" + port + "/wd/hub");

            switch (System.getProperty("browser")) {
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    driver = new RemoteWebDriver(url, firefoxOptions);
                    break;

                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    driver = new RemoteWebDriver(url, chromeOptions);
                    break;

                default:
                    throw new InvalidBrowserConfiguration();
            }
        } else {
            switch (System.getProperty("browser")) {
                case "firefox":
                    driver = new FirefoxDriver();
                    break;

                case "chrome":
                    driver = new ChromeDriver();
                    break;

                default:
                    throw new InvalidBrowserConfiguration();
            }

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void canOpenGooglePage() {
        driver.get("http://www.google.com/");
        Assertions.assertEquals("Google", driver.getTitle());
    }

    @Test
    public void canOpenScrumOrgPage() {
        driver.get("https://www.scrum.org/");
        Assertions.assertEquals("Homepage | Scrum.org", driver.getTitle());
    }

    @Test
    public void canOpenRafalsPage() {
        driver.get("http://markowicz.pro/");
        Assertions.assertEquals("Rafał Markowicz – Kolejny piękny dzień", driver.getTitle());
    }

    @AfterEach
    public void quitBrowser() {
        driver.quit();
    }

    private class InvalidBrowserConfiguration extends Throwable {
    }
}
