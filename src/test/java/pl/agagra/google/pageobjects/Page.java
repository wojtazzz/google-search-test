package pl.agagra.google.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * User: Wojtas
 * Date: 17.12.13
 * Time: 22:21
 * <p/>
 * Base class for all page objects.
 * Contains generalized page functionalities.
 */
public class Page {

    protected final WebDriver driver;
    public static final String URL = "www.google.pl";

    Logger LOG = Logger.getLogger(Page.class);

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
            LOG.error("Something went wrong when waiting for visibility of element!");
            e.printStackTrace();
        }
    }

    protected void waitForElementPresent(final By by, int timeOutInSeconds) {
        new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

}
