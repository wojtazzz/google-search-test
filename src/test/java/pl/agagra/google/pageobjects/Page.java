package pl.agagra.google.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * User: Wojtas
 * Date: 17.12.13
 * Time: 22:21
 *
 * Base class for all page objects.
 * Contains generalized page functionalities.
 */
public class Page {

    protected final WebDriver driver;
    public static final String URL = "www.google.pl";

    public Page(WebDriver driver) {
        this.driver = driver;
    }


    protected boolean elementPresent(By locator) {
        return driver.findElements(locator).size() != 0;
    }

    protected boolean elementIsVisible(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    protected void waitForElementVisible(final By by, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void waitForElementPresent(final By by, int timeOutInSeconds) {
        WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

}
