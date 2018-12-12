package selenium.pagebjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

public class GoogleResultPage {
    private final WebDriver grpDriver;
    private static final By LOC_RESULT = By.cssSelector("#search .rc .r");

    public GoogleResultPage(WebDriver driver) {
        grpDriver = driver;
    }

    public boolean containsResults(String resultUrl) {
        Stream<WebElement> results = getResultByUrl(resultUrl);
        return results.count() > 0;
    }

    public boolean containsResultsWithTitle(String pageUrl, String pageTitle) {
        Stream<WebElement> results = getResultByUrl(pageUrl)
                .filter(r -> r.findElement(By.tagName("h3")).getText().equals(pageTitle));

        return results.count() > 0;
    }

    private Stream<WebElement> getResultByUrl(String resultUrl) {
        return grpDriver.findElements(LOC_RESULT)
                .stream()
                .filter(r -> r.findElement(By.tagName("a")).getAttribute("href").equals(resultUrl));
    }
}
