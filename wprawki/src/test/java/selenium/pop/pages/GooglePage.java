package selenium.pop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class GooglePage {
	protected final WebDriver driver;

	public GooglePage(WebDriver driver) {
		this.driver = driver;
	}

	public void refreshPage() {
		driver.navigate().refresh();
	}

	protected void waitUntilElementIsPresent(By elementLocator, long seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
	}
}
