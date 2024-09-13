package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestBase {
    private WebDriver driver;
    public WebDriver getWebDriver() throws IOException {
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
