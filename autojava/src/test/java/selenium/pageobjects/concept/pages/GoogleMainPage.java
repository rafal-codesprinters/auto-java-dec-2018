package selenium.pageobjects.concept.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleMainPage {

    private final WebDriver driver;
    private static final String GOOGLE_PAGE_URL = "http://www.google.com";
    private static final By LOC_SEARCH_BOX = By.name("q");

    public GoogleMainPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get(GOOGLE_PAGE_URL);
    }

    public GoogleResultPage search(String searchQuery) {
        WebElement searchBox = driver.findElement(LOC_SEARCH_BOX);
        searchBox.sendKeys(searchQuery);
        searchBox.submit();

        return new GoogleResultPage(driver);
    }
}
