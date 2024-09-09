package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends CommonPage {
    WebDriver driver;
    public SignInPage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(id="ap_email")
    WebElement emailTextBox;

    @FindBy(css = "input#continue")
    WebElement continueButton;

    @FindBy(id="ap_password")
    WebElement passwordTextBox;

    final String signInID= "signInSubmit";
    @FindBy(id=signInID)
    WebElement signInButton;

    public void signIn(String email, String password) throws InterruptedException {
        emailTextBox.sendKeys(email);
        continueButton.click();
        waitForElementLocated(By.id(signInID));
        passwordTextBox.sendKeys(password);
        signInButton.click();
    }


}
