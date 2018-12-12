package selenium.basic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class WordpressTests {

    private WebDriver driver;

    @BeforeEach
    public void startBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void addCommentToFirstNote() {
        String comment = UUID.randomUUID().toString();
        String author = UUID.randomUUID().toString();
        String email = UUID.randomUUID() + "@test.com";

        driver.get("https://automatyzacja.benedykt.net");

        driver.findElement(By.cssSelector("article.post"))
                .findElement(By.className("entry-title"))
                .findElement(By.tagName("a"))
                .click();

        Assertions.assertEquals(1, driver.findElements(By.cssSelector("body.single-post")).size(),
                "Single note page is displayed");

        driver.findElement(By.id("comment")).sendKeys(comment);

        driver.findElement(By.id("author")).sendKeys(author);

        driver.findElement(By.id("email")).sendKeys(email);

        WebElement submit = driver.findElement(By.id("submit"));
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        jsExec.executeScript("arguments[0].scrollIntoView(true);", submit);
        submit.click();

        Stream<WebElement> comments = driver.findElements(By.cssSelector(".comment-list > .comment"))
                .stream()
                .filter(c -> c.findElement(By.cssSelector(".comment-author > b")).getText().equals(author))
                .filter(c -> c.findElement(By.cssSelector(".comment-content > p")).getText().equals(comment));

        Assertions.assertEquals(1, comments.count(), "Exactly one matching comment is published");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }
}
