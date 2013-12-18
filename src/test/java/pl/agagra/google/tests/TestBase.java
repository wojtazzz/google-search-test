package pl.agagra.google.tests;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

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
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }


}
