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

    public LandingPage getLandingPage(){
        return new LandingPage(driver);
    }

    public SearchResultPage getSearchResultPage(){
        return new SearchResultPage(driver);
    }

    public CartPage getCartPage(){
        CartPage cartPage= new CartPage(driver);
        cartPage.waitForElementAppeared(cartPage.cartTitle);
        System.out.println("cart title: " + cartPage.cartTitle.getText());
        return cartPage;
    }


}
