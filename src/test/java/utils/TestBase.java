package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import java.io.IOException;

public class TestBase {
    private WebDriver driver;
    public WebDriver getWebDriver() {
        if(driver == null){
            if(System.getProperty("BROWSER").trim().equalsIgnoreCase("firefox"))
                driver= WebDriverManager.firefoxdriver().create();
            else
                driver= WebDriverManager.chromedriver().create();

            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.get(System.getProperty("URL"));
        }
        return driver;
    }
}
