package selenium.wordpresspageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class WpAdminPage extends WpPage {

    public static final By WP_ADMIN_BAR = By.id("wpadminbar");
    private static final By MENU_ITEM_POSTS = By.id("menu-posts");
    private static final By SUBMENU_ITEM = By.cssSelector(".wp-submenu li a"); // .wp-submenu li a[href*=nowy-wpis]
    private static final By NEW_NOTE_TITLE = By.id("title");
    private static final By HTML_EDITOR_TAB = By.id("content-html");
    private static final By NEW_NOTE_CONTENT = By.id("content");
    private static final By PERMALINK_EDIT_BUTTON = By.cssSelector("edit-slug");
    private static final By PUBLISH_BUTTON = By.id("publish");
    private static final By PERMALINK = By.id("sample-permalink");

    public WpAdminPage(WebDriver driver) {
        super(driver);
    }

    public String createNote(String title, String content) {
        WebElement menuNotes = driver.findElement(MENU_ITEM_POSTS);
        hoverOverElement(menuNotes);

        menuNotes.findElements(SUBMENU_ITEM)
                .stream()
                .filter(sm -> sm.getAttribute("textContent").equals("Dodaj nowy"))
                .collect(Collectors.toList())
                .get(0)
                .click();
        
        driver.findElement(NEW_NOTE_TITLE).sendKeys(title);
        driver.findElement(HTML_EDITOR_TAB).click();
        driver.findElement(NEW_NOTE_CONTENT).sendKeys(content);
        
        waitForElementClickable(PERMALINK_EDIT_BUTTON, 5);
        driver.findElement(PUBLISH_BUTTON).click();
        waitForElementClickable(PUBLISH_BUTTON, 5);

        return driver.findElement(PERMALINK)
                .findElement(By.tagName("a"))
                .getAttribute("href");
    }

    public WpLoginPage logout() {
        return null;
    }
}
