package pl.agagra.google.tests;


import org.testng.annotations.Test;
import pl.agagra.google.pageobjects.GoogleResultPage;
import pl.agagra.google.pageobjects.GoogleSearchPage;
import pl.agagra.google.pageobjects.Page;

import static org.testng.Assert.assertTrue;

public class GoogleSearchTest extends TestBase {


    GoogleSearchPage googleSearchPage;
    GoogleResultPage googleResultPage;

//    @BeforeMethod
//    public void setUp() {
//
//
//    }


    @Test
    public void shouldBePossibleToSearch() {
        googleSearchPage = new GoogleSearchPage(driver);
        driver.get(Page.URL);
        assertTrue(googleSearchPage.isLoaded());
        assertTrue(googleSearchPage.logoIsPresent());

    }
}
