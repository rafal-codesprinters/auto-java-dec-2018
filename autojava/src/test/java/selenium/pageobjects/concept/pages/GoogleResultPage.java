package selenium.pageobjects.concept.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

public class GoogleResultPage {
    private final WebDriver driver;
    private static final By LOC_RESULT = By.cssSelector("#search .rc");
    private static final By LOC_RESULT_TITLE = By.cssSelector(".r h3");

    public GoogleResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean containsResults(String resultUrl) {
        Stream<WebElement> results = getResultsByUrl(resultUrl);
        return results.count() > 0;
    }

    public boolean containsResultsWithTitle(String pageUrl, String pageTitle) {
        Stream<WebElement> results = getResultsByUrl(pageUrl)
                .filter(r -> r.findElement(LOC_RESULT_TITLE).getText().equals(pageTitle));

        return results.count() > 0;
    }

    private Stream<WebElement> getResultsByUrl(String resultUrl) {
        return driver.findElements(LOC_RESULT)
                .stream()
                .filter(r -> r.findElement(By.tagName("a")).getAttribute("href").equals(resultUrl));
    }
}
