package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    WebDriver driver;
    public PageObjectManager(WebDriver driver){
        this.driver= driver;
    }

    public NotRobotPage getNotRobot(){
        return new NotRobotPage(driver);
    }

    public MasterPage getMasterPage(){
        return new MasterPage(driver);
    }

    public SignInPage getSignInPage(){
        return new SignInPage(driver);
    }

    public LandingPage getLandingPage(){
        return new LandingPage(driver);
    }

    public SearchResultPage getSearchResultPage(){
        return new SearchResultPage(driver);
    }

    public CartPage getCartPage(){
        return new CartPage(driver);
    }


}
