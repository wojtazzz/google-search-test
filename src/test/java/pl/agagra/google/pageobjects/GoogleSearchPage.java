package pl.agagra.google.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * User: Wojtas
 * Date: 17.12.13
 * Time: 21:59
 *
 * Page Object respesenting main google page with search field
 */
public class GoogleSearchPage extends Page {

    private By googleLogoLocator = By.id("hplogo");
    private By searchButtonLocator = By.id("gbqfba");
    private By searchFieldLocator = By.id("#gbqfq");
    private By cookieInfoSloganLocator = By.xpath(".//*[@id='epb-notice']");
    private By cookieAcceptButtonLocator = By.cssSelector("#epb-ok");

    public GoogleSearchPage(WebDriver driver) {
        super(driver);
    }

    public GoogleResultPage inputPhrase(String query) {
        WebElement searchField = driver.findElement(searchFieldLocator);
        searchField.sendKeys(query);
        return new GoogleResultPage(driver, query);
    }

    public boolean isLoaded() {
        waitForElementVisible(googleLogoLocator, 5);
        System.out.println(driver.getCurrentUrl());
        return driver.getCurrentUrl().matches("(http||https)://" + URL + "\\/?");
    }

    public boolean logoIsPresent() {
        return elementPresent(googleLogoLocator);
    }

    public boolean cookieInfoIsDisplayed() {
        return elementIsVisible(cookieInfoSloganLocator);
    }

    public GoogleSearchPage acceptCookies() {
        waitForElementVisible(cookieAcceptButtonLocator, 5);
        driver.findElement(cookieAcceptButtonLocator).click();
        return this;
    }

    public GoogleSearchPage pressSearchButton() {
        driver.findElement(searchButtonLocator).click();
        return this;
    }
}


