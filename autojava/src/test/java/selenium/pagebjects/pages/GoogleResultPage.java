package selenium.pagebjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

public class GoogleResultPage {
    private final WebDriver grpDriver;
    public static final By RESULT_LOC = By.cssSelector("#search .rc .r");

    public GoogleResultPage(WebDriver driver) {
        grpDriver = driver;
    }

    public boolean contains(String resutlUrl) {

        Stream<WebElement> results = getResultByUrl(resutlUrl);

        if (results.count() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean containsResultWithTitle(String pageUrl, String pageTitle) {
        Stream<WebElement> results = getResultByUrl(pageUrl)
                .filter(r -> r.findElement(By.tagName("h3")).getText().equals(pageTitle));

        if (results.count() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private Stream<WebElement> getResultByUrl(String resutlUrl) {
        return grpDriver.findElements(RESULT_LOC)
                .stream()
                .filter(r -> r.findElement(By.tagName("a")).getAttribute("href").equals(resutlUrl));
    }
}
