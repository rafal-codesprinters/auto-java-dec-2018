package selenium.pageobjects.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

public class WpNotePage extends WpPage {

    // Locators for note page
    private static final By WP_NOTE_PAGE = By.cssSelector("body.single-post");
    private static final By TITLE = By.className("entry-title");
    private static final By CONTENT = By.className("entry-content");

    // Locators for form allowing to post comments
    private static final By COMMENT_BOX = By.id("comment");
    private static final By AUTHOR_BOX = By.id("author");
    private static final By EMAIL_BOX = By.id("email");
    private static final By SUBMIT_BUTTON = By.id("submit");

    // Locators for comments list
    private static final By COMMENT = By.cssSelector(".comment-list > .comment");
    private static final By COMMENT_AUTHOR = By.cssSelector(".comment-author > b");
    private static final By COMMENT_CONTENT = By.cssSelector(".comment-content > p");
    

    public WpNotePage(WebDriver driver) {
        super(driver);

        // This will throw timeout error if within 2 seconds note page is not loaded
        // If error (exception) is thrown, the test execution stops with an error.
        // It allows to prevent from going further if requested page did not load or wrong page loaded.
        waitForElementPresent(WP_NOTE_PAGE, 2);
    }

    public static WpNotePage open(String noteUrl, WebDriver driver) {
        driver.get(noteUrl);
        return new WpNotePage(driver);
    }

    public WpNotePage addComment(String comment, String author, String email) {
        driver.findElement(COMMENT_BOX).sendKeys(comment);
        driver.findElement(AUTHOR_BOX).sendKeys(author);
        driver.findElement(EMAIL_BOX).sendKeys(email);

        WebElement submit = driver.findElement(SUBMIT_BUTTON);
        scrollElementIntoView(submit);
        submit.click();

        driver.findElement(WP_NOTE_PAGE);
        return new WpNotePage(driver);
    }

    public boolean commentExists(String comment, String author) {
        Stream<WebElement> comments = driver.findElements(COMMENT)
                .stream()
                .filter(c -> c.findElement(COMMENT_AUTHOR).getText().equals(author))
                .filter(c -> c.findElement(COMMENT_CONTENT).getText().equals(comment));

        return comments.count() == 1;
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public String getContent() {
        return driver.findElement(CONTENT).getText();
    }
}
