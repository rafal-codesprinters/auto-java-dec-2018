package selenium.wordpresspageobjects.pages;

import org.openqa.selenium.WebDriver;

public class WpMainPage {

    private final WebDriver driver;

    public WpMainPage(WebDriver driver) {
        this.driver = driver;
        // Todo: here you open wordpress page
    }

    public WpNotePage openLatestNote() {
        // Todo: here you find and open latest note (first note)
        return new WpNotePage(driver);
    }
}
