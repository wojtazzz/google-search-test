package pl.agagra.google.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public Page(WebDriver driver){
        this.driver = driver;
    }


    protected boolean elementPresent(By locator) {

         return driver.findElements(locator).size()!=0;

         }
}
