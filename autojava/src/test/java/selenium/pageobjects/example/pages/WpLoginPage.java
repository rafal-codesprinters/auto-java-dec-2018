package selenium.pageobjects.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WpLoginPage extends WpPage {

    private static final String WP_ADMIN_URL = "https://automatyzacja.benedykt.net/wp-admin/";

    // Locators for login page
    private static final By LOGIN_PAGE = By.cssSelector("body.login");

    // Locators for login form
    private static final By USER_BOX = By.id("user_login");
    private static final By PASSWORD_BOX = By.id("user_pass");
    private static final By SUBMIT_BUTTON = By.id("wp-submit");

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
        WebElement userBox = driver.findElement(USER_BOX);
        userBox.click();
        userBox.sendKeys(user);

        WebElement passwordBox = driver.findElement(PASSWORD_BOX);
        passwordBox.click();
        passwordBox.sendKeys(password);

        driver.findElement(SUBMIT_BUTTON).click();

        return new WpAdminPage(driver);
    }

    public boolean isUserLoggedOut() {
        return driver.findElements(LOGIN_PAGE).size() > 0;
    }
}
