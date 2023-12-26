package homeWork17;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static homeWork17.Credentials.PASSWORD;
import static homeWork17.Credentials.USERNAME;
import static homeWork17.SauceDemoLocators.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;


public class SauceDemoTest extends BaseTest {

    @Test
    public void testSauceDemo() {

        // login
        wait.until(visibilityOfElementLocated(USERNAME_FIELD)).sendKeys(USERNAME);
        wait.until(visibilityOfElementLocated(PASSWORD_FIELD)).sendKeys(PASSWORD);
        wait.until(elementToBeClickable(LOGIN_BUTTON)).click();
        assertNotNull(wait.until(visibilityOfElementLocated(SWAG_LABS_LOGO)), "Not logged in!");

        // click LinkedIn link
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        wait.until(visibilityOfElementLocated(LINKEDIN_LINK)).click();

        // handling new tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        assertTrue(driver.getTitle().contains(LINKEDIN_TITLE), "Opened tab is wrong!");
        driver.close();
        driver.switchTo().window(tabs.get(0));

        // logout
        wait.until(elementToBeClickable(MENU_BUTTON)).click();
        wait.until(elementToBeClickable(LOGOUT_LINK)).click();
    }
}
