package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePage extends CommonPage{
    WebDriver driver;
    public BasePage(WebDriver driver){
        super(driver);
        this.driver= driver;
    }

    final String navLinkAccountId= "nav-link-accountList";
    @FindBy(id = navLinkAccountId)
    WebElement accountListEle;

    public SignInPage openSignInPage(){
        waitForElementLocated(By.id(navLinkAccountId));
        accountListEle.click();
        return new SignInPage(driver);
    }
}
