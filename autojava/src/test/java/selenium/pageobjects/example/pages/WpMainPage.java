package selenium.pageobjects.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WpMainPage extends WpPage {

    private static final String WP_HOME_URL = "https://automatyzacja.benedykt.net/";

    // Locators for home page
    private static final By WP_MAIN_PAGE = By.cssSelector("body.home");

    // Locators for list of notes
    private static final By NOTE_LINK = By.cssSelector(".entry-title > a");

    public WpMainPage(WebDriver driver) {
        super(driver);

        driver.get(WP_HOME_URL);

        // This will throw timeout error if within 2 seconds main page is not loaded
        // If error (exception) is thrown, the test execution stops with an error.
        // It allows to prevent from going further if requested page did not load or wrong page loaded.
        waitForElementPresent(WP_MAIN_PAGE, 2);
    }

    public static WpMainPage open(WebDriver driver) {
        return new WpMainPage(driver);
    }

    public WpNotePage openLatestNote() {
        driver.findElement(NOTE_LINK).click();

        return new WpNotePage(driver);
    }
}
