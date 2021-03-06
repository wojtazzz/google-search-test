package pl.agagra.google.tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pl.agagra.google.pageobjects.GoogleResultPage;
import pl.agagra.google.pageobjects.GoogleSearchPage;
import pl.agagra.google.pageobjects.Page;
import pl.agagra.google.utils.FileUtils;
import pl.agagra.google.utils.TestCaseListener;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Listeners({TestCaseListener.class})
public class GoogleSearchTest {

    public static final int NUMBER_OF_POSITIVE_SEARCHES = 6;

    Logger LOG = Logger.getLogger(GoogleSearchTest.class);
    GoogleSearchPage googleSearchPage;
    GoogleResultPage googleResultPage;
    WebDriver driver;

    @BeforeMethod
    public void beforeEveryTest() {
        LOG.info("Setting up FireFox driver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        googleSearchPage = new GoogleSearchPage(driver);
        driver.get("http://" + Page.URL);

    }

    @AfterMethod
    public void afterEveryTest() {
        driver.quit();
    }

    @Test
    public void shouldBePossibleToLoadSearchPage() {
        assertTrue(googleSearchPage.isLoaded());
        assertTrue(googleSearchPage.logoIsPresent());
    }

    @Test(dataProvider = "randomNouns")
    public void shouldBePossibleToSearchForEnglishNouns(String noun) {
        googleResultPage = googleSearchPage.inputPhrase(noun);
        googleResultPage.confirmSearchWithEnter();
        assertTrue(googleResultPage.searchResultsAreValid());
        assertTrue(googleResultPage.isLoaded());

    }

    @Test
    public void exclusionOperatorShouldWork() {
        String query = "-seamless";
        googleResultPage = googleSearchPage.inputPhrase(query);
        googleResultPage.confirmSearchWithEnter();
        assertTrue(googleResultPage.noResultsFound());
        assertTrue(googleResultPage.notFoundSloganIsDisplayed());
    }

    @Test
    public void searchForUrlShouldGiveThePageOnFirstPosition() {
        String query = "seamless.se";
        googleResultPage = googleSearchPage.inputPhrase(query);
        googleResultPage.confirmSearchWithEnter();
        assertTrue(googleResultPage.urlFoundAtFirstPosition(query));
    }

    @Test
    public void nonIndexedPhrasesShouldNotGiveAnyResults() {
        googleResultPage = googleSearchPage.inputPhrase("()");
        googleResultPage.confirmSearchWithEnter();

    }

    @Test
    public void tooLongPhrasesShouldGiveWarnings() {
        String longString = generateLongString();
        googleResultPage = googleSearchPage.inputPhrase(longString);
        googleResultPage.confirmSearchWithEnter();
        assertTrue(googleResultPage.tooLongQuerySloganIsDisplayed());
    }

    @Test
    public void cookieWarningShouldNotReapprearAfterPageReload() {
        assertTrue(googleSearchPage.cookieInfoIsDisplayed());
         googleSearchPage.acceptCookies();
        googleResultPage = googleSearchPage.inputPhrase("Any Phrase");
        googleResultPage.confirmSearchWithEnter();
        googleSearchPage = googleResultPage.goBackToSearchPage();
        assertFalse(googleSearchPage.cookieInfoIsDisplayed());
    }

    @Test
    public void emptySearchShouldDoNothing() {
        googleSearchPage.inputPhrase("     ");
        googleSearchPage.pressSearchButton();
        assertTrue(googleSearchPage.isLoaded());
    }


    @Test
    public void shouldBePossibleToSelectSuggestionsFromDropdown() {
        googleResultPage = googleSearchPage.inputPhrase("simless");
        assertTrue(googleResultPage.fourSuggestionsAreOffered());
        googleResultPage.selectFirstSuggestionFromDropdown();
        assertFalse(googleResultPage.suggestionDropdownIsVisible());
        assertTrue(googleResultPage.phraseNotInSearchResultsDescription("simless"));
    }

    @Test
    public void shouldSuggestCorrectSearchForMisspelledPhrases() {
        googleResultPage = googleSearchPage.inputPhrase("ogurek");
        googleResultPage.confirmSearchWithEnter();
        assertTrue(googleResultPage.didYouMeanSloganIsDisplayed());
        String suggestion = googleResultPage.getSuggestedWord();
        googleResultPage.clickSuggestedWord();
        assertTrue(googleResultPage.urlWasRewritten(suggestion));
    }


    @DataProvider(name = "randomNouns")
    public static Object[][] randomNouns() {

        List<String> list = FileUtils.getRandomListOfNouns(NUMBER_OF_POSITIVE_SEARCHES);
        Object[][] providedData = new Object[list.size()][1];
        for (int i = 0; i < list.size(); i++) {
            providedData[i][0] = list.get(i);
        }
        return providedData;
    }

    private String generateLongString() {
        int stringLength = 200;
        StringBuffer longString = new StringBuffer(stringLength);
        for (int i = 0; i < stringLength; i++) {
            longString.append("X");
        }
        return longString.toString();
    }
}
