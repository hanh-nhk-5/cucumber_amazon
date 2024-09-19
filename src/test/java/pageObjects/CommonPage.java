package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonPage {
    WebDriver driver;
    public CommonPage(WebDriver driver){
        this.driver= driver;
    }

    public void waitForElementLocated(By by){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(driver -> driver.findElement(by));
    }


    public void waitForElementAppeared(WebElement element, int secs){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(secs));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementDisappeared(WebElement element, int secs){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(secs));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForElementClickable(WebElement element, int secs){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(secs));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
