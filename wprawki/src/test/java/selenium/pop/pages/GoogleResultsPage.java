package selenium.pop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoogleResultsPage extends GooglePage{
	private static final By RESULT_LOC = By.className("rc");
	private static final By RESULT_URL = By.cssSelector(".r > a");
	private static final By RESULT_DISPLAY_URL_LOC = By.tagName("cite");

	public GoogleResultsPage(WebDriver driver) {
		super(driver);
	}

	public boolean oneResultFound(String url) {
		List<WebElement> results = findResultsByUrl(url);
		if (results.size() == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validUrlDisplayed(String url, String displayedUrl) {
		Stream<WebElement> results = findResultsByUrl(url)
				.stream()
				.filter(res -> res.findElement(RESULT_DISPLAY_URL_LOC).getText().equals(displayedUrl));
		if (results.count() == 1) {
			return true;
		} else {
			return false;
		}
	}

	private List<WebElement> findResultsByUrl(String url) {
		return driver.findElements(RESULT_LOC)
				.stream()
				.filter(res -> res.findElement(RESULT_URL).getAttribute("href").equals(url))
				.collect(Collectors.toList());
	}
}
