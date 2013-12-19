package pl.agagra.google.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Wojtas
 * Date: 17.12.13
 * Time: 22:31
 * To change this template use File | Settings | File Templates.
 */
public class GoogleResultPage extends Page {

    private By searchFieldLocator = By.cssSelector("#gbqfq");
    private By searchResultTitleLocator = By.cssSelector(".rc .r");
    private By searchResultUrlLocator = By.cssSelector(".vurls");
    private By searchResultDescriptionLocator = By.cssSelector(".st");
    private By notFoundSloganLocator = By.cssSelector(".med>p");
    private By tooLongSloganLocator = By.cssSelector(".f.std.uc.card-section.ucm");
    private By googleLogoButtonLocator = By.xpath(".//*[@id='gbq1']/div/a/span");
    private By suggestionRowsLocator = By.cssSelector(".gssb_a.gbqfsf");
    private By statsTextLocator = By.cssSelector("#resultStats");
    private String query;

    public GoogleResultPage(WebDriver driver, String query) {
        super(driver);
        this.query = query;
    }

    public boolean isLoaded() {
        return driver.getCurrentUrl().matches("(http||https)://" + URL + "/#q=" + query);
    }

    public void confirmSearchWithEnter() {
        driver.findElement(searchFieldLocator).sendKeys(Keys.ENTER);
    }

    public boolean searchResultsAreValid() {
        //Assumption: Search results are valid if at least 5  urls popup
        waitForElementVisible(searchResultTitleLocator, 5);

        return (driver.findElements(searchResultUrlLocator).size() >= 5);

    }

    public boolean noResultsFound() {
        return driver.findElements(searchResultTitleLocator).size() == 0;
    }


    public boolean notFoundSloganIsDiplayed() {
        Pattern pattern = Pattern.compile("Podana fraza - " + query + " - nie została odnaleziona.");
        Matcher matcher = pattern.matcher(getNotFoundSlogan());
        return matcher.find();

    }


    public boolean urlFoundAtFirstPosition(String url) {
        waitForElementVisible(searchResultUrlLocator, 5);
        return driver.findElements(searchResultUrlLocator).get(0).getText().contains(url);
    }


    private String getNotFoundSlogan() {
        waitForElementVisible(notFoundSloganLocator, 5);
        List<WebElement> paragraphs = driver.findElements(notFoundSloganLocator);
        StringBuffer slogan = new StringBuffer();
        slogan.append(paragraphs.get(0).getText());
        slogan.append(paragraphs.get(1).getText());
        return slogan.toString();
    }

    public boolean tooLongQuerySloganIsDisplayed() {
        String slogan = driver.findElement(tooLongSloganLocator).getText();
        return slogan.contains(query) && slogan.contains("jest za długie. Spróbuj użyć krótszego.");
    }

    public GoogleSearchPage goBackToSearchPage() {
        driver.findElement(googleLogoButtonLocator).click();
        return new GoogleSearchPage(driver);
    }

    public boolean fourSuggestionsAreOffered() {
        return driver.findElements(suggestionRowsLocator).size() == 4;
    }

    public boolean suggestionDropdownIsVisible() {
        return elementPresent(suggestionRowsLocator);
    }

    public void selectFirstSuggestion() {
        waitForElementVisible(suggestionRowsLocator,8);
        driver.findElements(suggestionRowsLocator).get(0).click();
        driver.findElement(searchFieldLocator).sendKeys(Keys.ENTER);

    }

    public boolean phraseNotInSearchResultsDescription(String phrase) {
        List<WebElement> paragraphElements = driver.findElements(searchResultDescriptionLocator);
        for (WebElement element : paragraphElements) {
            if (element.getText().contains(phrase)) {
                return false;
            }
        }
        return true;
    }

}
