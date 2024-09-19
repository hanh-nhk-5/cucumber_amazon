package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MasterPage extends CommonPage{
    WebDriver driver;
    public MasterPage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(this.driver,this);
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

    @FindBy(id="nav-link-accountList")
    WebElement accountAndListElement;

    @FindBy(id="nav-logo-sprites")
    WebElement landingPageNavigator;

    @FindBy(id="nav-item-signout")
    WebElement signOutButton;

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

    public void navigateToCart(){
        waitForElementClickable(cartNavigator, 5);
        cartNavigator.click();
    }

    public boolean isSignedIn(){
        return !helloAccount.getText().trim().equalsIgnoreCase("Hello, sign in");
    }

    public void signOut(){
        Actions actions= new Actions(driver);
        actions.moveToElement(accountAndListElement)
                .moveToElement(signOutButton).click().perform();
    }
}
