package pl.agagra.google.tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Created with IntelliJ IDEA.
 * User: Wojtas
 * Date: 17.12.13
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
public class TestBase {

    WebDriver driver;


    @BeforeClass
    public void beforeAllTests() {
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterAllTests() {
        System.out.println("Tests done!");
        driver.quit();
    }


}
