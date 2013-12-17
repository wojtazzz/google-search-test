package pl.agagra.google.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created with IntelliJ IDEA.
 * User: Wojtas
 * Date: 17.12.13
 * Time: 21:59
 * To change this template use File | Settings | File Templates.
 */
public class GoogleSearchPage extends Page {

    private By googleLogoLocator = By.cssSelector("#hplogo");
    private By searchButtonLocator = By.cssSelector("#gbqfba");
    private By searchFieldLocator = By.cssSelector("#gs_htif0");
    private By luckyShotButtonLocator = By.cssSelector("#gbqfbb");


    public GoogleSearchPage(WebDriver driver) {
        super(driver);
    }


    public GoogleResultPage searchForPhrase(String query) {
        WebElement searchField = driver.findElement(searchFieldLocator);
        searchField.clear();
        searchField.sendKeys(query);
        return new GoogleResultPage(driver, query);
    }

    public boolean isLoaded() {
        System.out.println(driver.getCurrentUrl());
        return driver.getCurrentUrl().equals(URL);
    }

    public boolean logoIsPresent() {
        return elementPresent(googleLogoLocator);
    }

}
