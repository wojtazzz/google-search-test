package pl.agagra.google.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Wojtas
 * Date: 17.12.13
 * Time: 22:21
 * To change this template use File | Settings | File Templates.
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

    protected void hoverAndClick(String clickCssLocator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        StringBuilder command = new StringBuilder();
        command.append("var x = $(\'" + clickCssLocator + "\');");
        // command.append("x.hover();");
        command.append("x.click();");
        js.executeScript(command.toString());
    }

    public boolean waitForJQueryProcessing(int timeOutInSeconds) {
        boolean jQcondition = false;
        try {
            new WebDriverWait(driver, timeOutInSeconds) {
            }.until(new ExpectedCondition<Boolean>() {

                @Override
                public Boolean apply(WebDriver driverObject) {
                    return (Boolean) ((JavascriptExecutor) driverObject).executeScript("return jQuery.active == 0");
                }
            });
            jQcondition = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
            return jQcondition;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jQcondition;
    }

}
