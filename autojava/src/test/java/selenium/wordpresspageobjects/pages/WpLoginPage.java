package selenium.wordpresspageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WpLoginPage extends WpPage {

    private static final String WP_ADMIN_URL = "https://automatyzacja.benedykt.net/wp-admin/";

    private static final By LOGIN_FORM = By.id("loginform");
    private static final By USER_BOX = By.id("user_login");
    private static final By PASSWORD_BOX = By.id("user_pass");
    private static final By SUBMIT_BUTTON = By.id("wp-submit");

    public WpLoginPage(WebDriver driver) {
        super(driver);
        openLoginPage();
    }

    private void openLoginPage() {
        driver.get(WP_ADMIN_URL);
        driver.findElement(LOGIN_FORM);
    }

    public WpAdminPage login(String user, String password) {
        driver.findElement(USER_BOX).sendKeys(user);
        driver.findElement(PASSWORD_BOX).sendKeys(password);
        driver.findElement(SUBMIT_BUTTON).click();

        driver.findElement(WpAdminPage.WP_ADMIN_BAR);
        return new WpAdminPage(driver);
    }
}
