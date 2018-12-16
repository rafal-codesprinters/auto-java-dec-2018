package selenium.pageobjects.pagefactory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Stream;

public class WpNotePage extends WpPage {

    // Locators for note page
    private static final By WP_NOTE_PAGE = By.cssSelector("body.single-post");

    // Elements created by Page Factory used to read entry title and content
    private @FindBy (className = "entry-title") WebElement noteTitle;
    private @FindBy (className = "entry-content") WebElement noteContent;

    // Elements created by Page Factory used to post comments
    private @FindBy (id = "comment") WebElement commentBox;
    private @FindBy (id = "author") WebElement authorBox;
    private @FindBy (id = "email") WebElement emailBox;
    private @FindBy (id = "submit") WebElement submitButton;

    // List of all comments created by Page Factory
    private @FindAll ({@FindBy (css = ".comment-list > .comment")}) List<WebElement> allComments;

    // Locators for comments list
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
        commentBox.sendKeys(comment);
        authorBox.sendKeys(author);
        emailBox.sendKeys(email);

        scrollElementIntoView(submitButton);
        submitButton.click();

        driver.findElement(WP_NOTE_PAGE);
        return new WpNotePage(driver);
    }

    public boolean commentExists(String comment, String author) {
        Stream<WebElement> comments = allComments
                .stream()
                .filter(c -> c.findElement(COMMENT_AUTHOR).getText().equals(author))
                .filter(c -> c.findElement(COMMENT_CONTENT).getText().equals(comment));

        return comments.count() == 1;
    }

    public String getTitle() {
        return noteTitle.getText();
    }

    public String getContent() {
        return noteContent.getText();
    }
}
