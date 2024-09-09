package utils;

import pageObjects.NotRobotPage;
import pageObjects.PageObjectManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBaseContext {
    public TestBase testBase;
    public PageObjectManager pageObjectManager;
    public TestBaseContext() throws IOException {
        FileInputStream fis= new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/global.properties");
        Properties props= new Properties();
        props.load(fis);

        System.setProperty("BROWSER", props.getProperty("BROWSER"));
        if(props.getProperty("ENVIRONMENT").trim().equalsIgnoreCase("production")){
            System.setProperty("URL", props.getProperty("PROD_URL"));
            System.setProperty("USERNAME", props.getProperty("PROD_USERNAME"));
            System.setProperty("PASSWORD", props.getProperty("PROD_PASSWORD"));
        }
        else{}

        testBase= new TestBase();
        pageObjectManager= new PageObjectManager(testBase.getWebDriver());
    }
}
