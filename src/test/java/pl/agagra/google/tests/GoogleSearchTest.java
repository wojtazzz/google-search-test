package pl.agagra.google.tests;


import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pl.agagra.google.pageobjects.GoogleResultPage;
import pl.agagra.google.pageobjects.GoogleSearchPage;
import pl.agagra.google.pageobjects.Page;
import pl.agagra.google.utils.FileUtils;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class GoogleSearchTest extends TestBase {


    public static final int NUMBER_OF_POSITIVE_SEARCHES = 6;
    GoogleSearchPage googleSearchPage;
    GoogleResultPage googleResultPage;

    //    @BeforeMethod
//    public void setUp() {
//
//
//    }
    @BeforeMethod
    public void beforeAllTests() {
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void afterAllTests() {
        System.out.println("Test done!");
        driver.quit();
    }

    @Test
    public void shouldBePossibleToLoadSearchPage() {
        //given
        googleSearchPage = new GoogleSearchPage(driver);
        //when
        driver.get(Page.URL);
        //then
        assertTrue(googleSearchPage.isLoaded());
        assertTrue(googleSearchPage.logoIsPresent());

    }

    @Test(dataProvider = "randomNouns")
    public void shouldBePossibleToSearchForEnglishNouns(String noun) {
        //given
        googleSearchPage = new GoogleSearchPage(driver);
        driver.get(Page.URL);
        //when
        googleResultPage = googleSearchPage.searchForPhrase(noun);
        //then
        assertTrue(googleResultPage.atLeastTenResultsAreFound());

    }

    @Test
    public void exclusionOperatorShouldWork() {
        //given
        googleSearchPage = new GoogleSearchPage(driver);
        driver.get(Page.URL);
        //when
        googleResultPage = googleSearchPage.searchForPhrase("-seamless");
        //then
        assertTrue(googleResultPage.noResultsFound());
        assertTrue(googleResultPage.phraseNotFoundSloganIsDiplayed("-seamless"));
    }

    @Test
    public void searchForUrlShouldGiveThePageOnFirstPosition(){}

    @Test
    public void nonIndexedPhrasesShouldNotGiveAnyResults(){}

    @Test
    public void tooLongPhrasesShouldGiveWarnings(){}

    @Test
    public void cookieWarningShouldNotReapprearAfterPageReload(){}

    @Test
    public void emptySearchShouldDoNothing(){}

    @Test
    public void shouldBePossibleToSelectSuggestions(){}

    @Test
    public void shouldSuggestCorrectSearchForMisspelledPhrases(){}

    @Test
    public void shouldBeInformedAboutNumberOfPagesFound(){}

    @Test
    public void pagingShouldWork(){}







    @DataProvider(name = "randomNouns")
    public static Object[][] randomNouns() {

        List<String> list = FileUtils.getRandomListOfNouns(NUMBER_OF_POSITIVE_SEARCHES);
        Object[][] providedData = new Object[list.size()][1];
        for (int i = 0; i < list.size(); i++) {
            providedData[i][0] = list.get(i);
        }
        return providedData;
    }


}
