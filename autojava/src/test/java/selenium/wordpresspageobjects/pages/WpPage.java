package selenium.wordpresspageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WpPage {

    protected final WebDriver driver;

    public WpPage(WebDriver driver) {
        this.driver = driver;
    }

    protected void scrollElementIntoView(WebElement element) {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        jsExec.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void hoverOverElement(WebElement element) {



        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();




    }

    protected void waitForElementClickable(By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitForElementClickable(WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
