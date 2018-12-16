package selenium.pageobjects.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.stream.Collectors;

public class WpAdminPage extends WpPage {

    // Locators for admin page
    private static final By WP_ADMIN_PAGE = By.cssSelector("body.wp-admin");

    // Locators for new post menu and submenus
    private static final By NEW_POST_EDITOR = By.cssSelector(".post-new-php");
    private static final By MENU_ITEM_POSTS = By.id("menu-posts");
    private static final By SUBMENU = By.cssSelector(".wp-submenu");
    private static final By SUBMENU_ITEM = By.cssSelector("li a");

    // Locators for note editor
    private static final By NOTE_TITLE = By.id("title");
    private static final By HTML_EDITOR_TAB = By.id("content-html");
    private static final By NOTE_CONTENT = By.id("content");
    private static final By PERMALINK_EDIT_BUTTON = By.cssSelector(".edit-slug");
    private static final By PUBLISH_BUTTON = By.id("publish");
    private static final By PERMALINK = By.id("sample-permalink");

    // Locators for user admin menu
    private static final By USER_ADMIN_LINK = By.id("wp-admin-bar-my-account");
    private static final By USER_ACTIONS_MENU = By.id("wp-admin-bar-user-actions");
    private static final By LOGOUT_LINK = By.id("wp-admin-bar-logout");

    private static final String ADD_NEW_MENU_TITLE = "Dodaj nowy";

    public WpAdminPage(WebDriver driver) {
        super(driver);

        // This will throw timeout error if within 2 seconds admin page is not loaded
        // If error (exception) is thrown, the test execution stops with an error.
        // It allows to prevent from going further if requested page did not load or wrong page loaded.
        waitForElementPresent(WP_ADMIN_PAGE, 2);
    }

    public WpAdminPage openNewNoteEditor() {
        WebElement menuNotes = driver.findElement(MENU_ITEM_POSTS);
        hoverOverElement(menuNotes);
        waitForElementVisible(menuNotes.findElement(SUBMENU), 2);
        menuNotes.findElements(SUBMENU_ITEM)
                .stream()
                .filter(sm -> sm.getText().equals(ADD_NEW_MENU_TITLE))
                .collect(Collectors.toList())
                .get(0)
                .click();

        waitForElementVisible(NEW_POST_EDITOR, 2);
        return new WpAdminPage(driver);
    }

    public WpAdminPage createNote(String title, String content) {
        WebElement newNoteTitle = driver.findElement(NOTE_TITLE);
        newNoteTitle.click();
        newNoteTitle.sendKeys(title);

        driver.findElement(HTML_EDITOR_TAB).click();

        WebElement newNoteContent = driver.findElement(NOTE_CONTENT);
        newNoteContent.click();
        newNoteContent.sendKeys(content);

        waitForElementClickable(PERMALINK_EDIT_BUTTON, 2);
        return new WpAdminPage(driver);
    }

    public WpLoginPage logout() {
        WebElement userAdminLink = driver.findElement(USER_ADMIN_LINK);
        hoverOverElement(userAdminLink);
        waitForElementVisible(USER_ACTIONS_MENU, 2);
        driver.findElement(LOGOUT_LINK).click();
        return new WpLoginPage(driver);
    }

    public String publishNote() {
        driver.findElement(PUBLISH_BUTTON).click();
        waitForElementClickable(PUBLISH_BUTTON, 5);

        return driver.findElement(PERMALINK)
                .findElement(By.tagName("a"))
                .getAttribute("href");
    }
}
