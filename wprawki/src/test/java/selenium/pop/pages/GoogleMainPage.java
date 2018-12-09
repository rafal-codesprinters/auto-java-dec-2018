package selenium.pop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleMainPage extends GooglePage {

	protected static final String MAIN_PAGE_URL = "http://www.google.com";
	private static final By SEARCH_BOX_LOC = By.name("q");
	private static final By SEARCH_RESULTS_LOC = By.cssSelector("div#search");

	public GoogleMainPage(WebDriver driver) {
		super(driver);
		driver.get(MAIN_PAGE_URL);
	}

	public GoogleResultsPage search(String textToFind) {
		WebElement searchBox = driver.findElement(SEARCH_BOX_LOC);
		searchBox.sendKeys(textToFind);
		searchBox.submit();

		waitUntilElementIsPresent(SEARCH_RESULTS_LOC, 5);

		return new GoogleResultsPage(driver);
	}

}
