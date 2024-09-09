package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class NotRobotPage extends CommonPage {
    WebDriver driver;
    public NotRobotPage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(this.driver,this);
    }

    @FindBy(xpath = "//button[@type='submit']")
    WebElement continueButton;

    public void confirm(){
        continueButton.click();
    }
}
