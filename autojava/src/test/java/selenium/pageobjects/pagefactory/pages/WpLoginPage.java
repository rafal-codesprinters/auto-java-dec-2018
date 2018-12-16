package selenium.pageobjects.pagefactory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WpLoginPage extends WpPage {

    private static final String WP_ADMIN_URL = "https://automatyzacja.benedykt.net/wp-admin/";

    // Locators for login page
    private static final By LOGIN_PAGE = By.cssSelector("body.login");

    // Elements created by Page Factory for login form
    private @FindBy (id = "user_login") WebElement userBox;
    private @FindBy (id = "user_pass") WebElement passwordBox;
    private @FindBy (id = "wp-submit") WebElement submitButton;

    public WpLoginPage(WebDriver driver) {
        super(driver);

        driver.get(WP_ADMIN_URL);

        // This will throw timeout error if within 2 seconds login page is not loaded
        // If error (exception) is thrown, the test execution stops with an error.
        // It allows to prevent from going further if requested page did not load or wrong page loaded.
        waitForElementPresent(LOGIN_PAGE, 2);
    }

    public static WpLoginPage open(WebDriver driver) {
        return new WpLoginPage(driver);
    }

    public WpAdminPage login(String user, String password) {
        userBox.click();
        userBox.sendKeys(user);

        passwordBox.click();
        passwordBox.sendKeys(password);

        submitButton.click();

        return new WpAdminPage(driver);
    }

    public boolean isUserLoggedOut() {
        return driver.findElements(LOGIN_PAGE).size() > 0;
    }
}
