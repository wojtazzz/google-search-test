package pl.agagra.google.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created with IntelliJ IDEA.
 * User: Wojtas
 * Date: 17.12.13
 * Time: 22:31
 * To change this template use File | Settings | File Templates.
 */
public class GoogleResultPage extends Page {

    private By searchResultTitleLocator = By.cssSelector(".rc .r");
    private By searchResultUrlLocator = By.cssSelector(".vurls");
    private By searchResultDescriptionLocator = By.cssSelector(".st");


    private By statsTextLocator = By.cssSelector("#resultStats");
    private String query;

    public GoogleResultPage(WebDriver driver, String query) {
        super(driver);
        this.query = query;
    }

    public boolean isLoaded() {
        return driver.getCurrentUrl().equals(URL + "/#q" + query);
    }

    public boolean atLeastTenResultsAreFound() {
        waitForElementVisible(searchResultTitleLocator, 5);
        return driver.findElements(searchResultTitleLocator).size() >= 10;
    }

    public boolean noResultsFound() {
        return driver.findElements(searchResultTitleLocator).size() == 0;
    }

    public boolean phraseNotFoundSloganIsDiplayed(String phrase) {
        return isTextPatternPresent("Podana fraza");
    }


}
