package selenium.basics;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoogleTests {

	private WebDriver driver;

	@BeforeEach
	public void openGooglePage() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://google.com");
	}

	@Test
	public void canFindCodeSprinters() {

		WebElement queryBox = driver.findElement(By.name("q"));
		queryBox.sendKeys("Code Sprinters");
		queryBox.submit();

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#search")));

		List<WebElement> results = driver.findElements(By.className("rc"))
				.stream()
				.filter(result -> result.findElement(By.cssSelector(".r > a")).getAttribute("href").equals("http://agileszkolenia.pl/"))
				.collect(Collectors.toList());

		assertEquals(1, results.size(), "Only one result for agileszkolenia.pl");
		assertEquals("agileszkolenia.pl/", results.get(0).findElement(By.tagName("cite")).getText(), "Proper URL is displayed for Code Sprinters");

	}

	@AfterEach
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}

}
