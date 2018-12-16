package selenium.wordpresspageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WpMainPage extends WpPage {

    private static final String WP_URL = "https://automatyzacja.benedykt.net/";

    private static final By SITE_TITLE = By.cssSelector(".site-header-main > .site-branding > .site-title");
    private static final By NOTE_LINK = By.cssSelector(".entry-title > a");

    public WpMainPage(WebDriver driver) {
        super(driver);
        openMainPage();
    }

    public WpNotePage openLatestNote() {
        driver.findElement(NOTE_LINK).click();

        driver.findElement(WpNotePage.SINGE_NOTE_CONTAINER);
        return new WpNotePage(driver);
    }

    private void openMainPage() {
        driver.get(WP_URL);
        driver.findElement(SITE_TITLE);
    }

}
