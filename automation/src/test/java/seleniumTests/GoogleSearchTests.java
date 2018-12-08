package seleniumTests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoogleSearchTests extends TestBase {

	@Test
	public void testGoogleFindsCodeSprintersJupiter() {
		driver.get("http://www.google.com");

		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Code Sprinters");
		searchBox.submit();

		List<WebElement> searchResults = driver.findElements(By.className("rc"));
		List<WebElement> codeSprintersResults = searchResults.stream()
				.filter(result -> result.findElement(By.cssSelector(".r > a")).getAttribute("href").equals("http://agileszkolenia.pl/"))
				.collect(Collectors.toList());

		assertAll("One result found with valid content",
				() -> assertEquals(1, codeSprintersResults.size(), "Only one result is found"),
				() -> assertEquals("agileszkolenia.pl/", codeSprintersResults.get(0).findElement(By.tagName("cite")).getText(), "Visible link is set to agileszkolenia.pl/")
		);
	}

	@Test
	public void testGoogleScrumOrgJupiter() {
		driver.get("http://www.google.com");

		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Scrum org");
		searchBox.submit();

		List<WebElement> searchResults = driver.findElements(By.className("rc"));
		List<WebElement> scrumOrgResults = searchResults.stream()
				.filter(result -> result.findElement(By.cssSelector(".r > a")).getAttribute("href").equals("https://www.scrum.org/"))
				.collect(Collectors.toList());

		assertAll("One result found with valid content",
				() -> assertEquals(1, scrumOrgResults.size(), "Only one result is found"),
				() -> assertEquals("https://www.scrum.org/", scrumOrgResults.get(0).findElement(By.tagName("cite")).getText(), "Visible link is set to https://www.scrum.org/")
		);
	}
}
