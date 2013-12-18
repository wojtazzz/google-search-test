package pl.agagra.google.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Wojtas
 * Date: 17.12.13
 * Time: 22:21
 * To change this template use File | Settings | File Templates.
 */
public class Page {

    protected final WebDriver driver;
    public static final String URL = "https://www.google.pl/";

    public Page(WebDriver driver) {
        this.driver = driver;
    }


    protected boolean elementPresent(By locator) {

        return driver.findElements(locator).size() != 0;

    }

    public final boolean isTextPatternPresent(String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(driver.getPageSource());
        return matcher.find();
    }

    public void waitForElementVisible(final By by, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
