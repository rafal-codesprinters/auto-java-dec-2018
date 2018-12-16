package selenium.wordpresspageobjects.pages;

import jdk.jfr.ContentType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

public class WpNotePage extends WpPage {

    // Locators for note page
    public static final By SINGE_NOTE_CONTAINER = By.cssSelector("body.single-post");
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
    }

    public WpNotePage(WebDriver driver, String noteUrl) {
        this(driver);
        openNote(noteUrl);
    }

    public WpNotePage addComment(String comment, String author, String email) {
        driver.findElement(COMMENT_BOX).sendKeys(comment);
        driver.findElement(AUTHOR_BOX).sendKeys(author);
        driver.findElement(EMAIL_BOX).sendKeys(email);

        WebElement submit = driver.findElement(SUBMIT_BUTTON);
        scrollElementIntoView(submit);
        submit.click();

        driver.findElement(SINGE_NOTE_CONTAINER);
        return new WpNotePage(driver);
    }

    public boolean commentExists(String comment, String author) {
        Stream<WebElement> comments = driver.findElements(COMMENT)
                .stream()
                .filter(c -> c.findElement(COMMENT_AUTHOR).getText().equals(author))
                .filter(c -> c.findElement(COMMENT_CONTENT).getText().equals(comment));

        return comments.count() == 1;
    }

    private void openNote(String noteUrl) {
        driver.get(noteUrl);
        driver.findElement(SINGE_NOTE_CONTAINER);
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public String getContent() {
        return driver.findElement(CONTENT).getText();
    }
}
