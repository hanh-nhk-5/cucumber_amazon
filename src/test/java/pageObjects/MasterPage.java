package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MasterPage extends CommonPage{
    WebDriver driver;
    public MasterPage(WebDriver driver){
        super(driver);
        this.driver= driver;
    }

    final String navLinkAccountId= "nav-link-accountList";
    @FindBy(id = navLinkAccountId)
    WebElement accountListDropdown;

    @FindBy(id= "twotabsearchtextbox")
    WebElement searchBox;

    @FindBy(id="nav-cart")
    WebElement cartNavigator;

    @FindBy(id="nav-link-accountList-nav-line-1")
    WebElement helloAccount;

    public SignInPage openSignInPage(){
        waitForElementLocated(By.id(navLinkAccountId));
        accountListDropdown.click();
        return new SignInPage(driver);
    }

    public void search(String text){
        searchBox.clear();
        searchBox.sendKeys(text);
        searchBox.sendKeys(Keys.ENTER);
    }

    public void openCart(){
        waitForElementClickable(cartNavigator);
        cartNavigator.click();
    }

    public boolean isSignedIn(){
        return !helloAccount.getText().trim().equalsIgnoreCase("Hello, sign in");
    }
}
