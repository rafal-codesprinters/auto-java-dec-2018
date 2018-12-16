package selenium.pageobjects.pagefactory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class WpAdminPage extends WpPage {

    // Locators for admin page
    private static final By WP_ADMIN_PAGE = By.cssSelector("body.wp-admin");
    private static final By SUBMENU_ITEM = By.cssSelector("li a");

    // Elements created by Page Factory for new post menu and submenus
    private @FindBy (css = ".post-new-php") WebElement newPostEditor;
    private @FindBy (id = "menu-posts") WebElement menuNotes;
    private @FindBy (css = ".wp-submenu") WebElement submenu;

    // Elements created by Page Factory for note editor
    private @FindBy (id = "title") WebElement newNoteTitle;
    private @FindBy (id = "content-html") WebElement htmlEditorTab;
    private @FindBy (id = "content") WebElement newNoteContent;
    private @FindBy (css = ".edit-slug") WebElement permalinkEditButton;
    private @FindBy (id = "publish") WebElement publishButton;
    private @FindBy (id = "sample-permalink") WebElement permalink;

    // Elements created by Page Factory for user admin menu
    private @FindBy (id = "wp-admin-bar-my-account") WebElement userAdminLink;
    private @FindBy (id = "wp-admin-bar-user-actions") WebElement userActionsMenu;
    private @FindBy (id = "wp-admin-bar-logout") WebElement logoutLink;

    private static final String ADD_NEW_MENU_TITLE = "Dodaj nowy";

    public WpAdminPage(WebDriver driver) {
        super(driver);

        // This will throw timeout error if within 2 seconds admin page is not loaded
        // If error (exception) is thrown, the test execution stops with an error.
        // It allows to prevent from going further if requested page did not load or wrong page loaded.
        waitForElementPresent(WP_ADMIN_PAGE, 2);
    }

    public WpAdminPage openNewNoteEditor() {
        hoverOverElement(menuNotes);
        waitForElementVisible(submenu, 2);
        menuNotes.findElements(SUBMENU_ITEM)
                .stream()
                .filter(sm -> sm.getText().equals(ADD_NEW_MENU_TITLE))
                .collect(Collectors.toList())
                .get(0)
                .click();

        waitForElementVisible(newPostEditor, 2);
        return new WpAdminPage(driver);
    }

    public WpAdminPage createNote(String title, String content) {
        newNoteTitle.click();
        newNoteTitle.sendKeys(title);

        htmlEditorTab.click();

        newNoteContent.click();
        newNoteContent.sendKeys(content);

        waitForElementClickable(permalinkEditButton, 2);
        return new WpAdminPage(driver);
    }

    public String publishNote() {
        publishButton.click();
        waitForElementClickable(publishButton, 5);

        return permalink.findElement(By.tagName("a")).getAttribute("href");
    }

    public WpLoginPage logout() {
        hoverOverElement(userAdminLink);
        waitForElementVisible(userActionsMenu, 2);
        logoutLink.click();
        return new WpLoginPage(driver);
    }
}
