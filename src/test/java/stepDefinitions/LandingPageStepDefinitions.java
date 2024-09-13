package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObjects.*;
import utils.TestBaseContext;

import java.util.List;

public class LandingPageStepDefinitions {
    TestBaseContext testBaseContext;
    public LandingPageStepDefinitions(TestBaseContext testBaseContext){
        this.testBaseContext= testBaseContext;
    }

    @Given("I am on the landing page")
    public void i_am_on_the_landing_page() {

    }
    @When("I sign in")
    public void i_sign_in() throws InterruptedException {
        LandingPage landingPage= testBaseContext.pageObjectManager.getLandingPage();
        SignInPage signInPage= landingPage.openSignInPage();
        signInPage.signIn(System.getProperty("USERNAME"), System.getProperty("PASSWORD"));
        Thread.sleep(10000);//to have enough time to input capcha manually, just in case
    }

    @Then("I am logged in successfully")
    public void i_am_logged_in_successfully() {
        LandingPage landingPage= testBaseContext.pageObjectManager.getLandingPage();
        Assert.assertTrue(landingPage.isSignedIn());
    }

    @Given("On the landing page I add some items in the Cart")
    public void i_add_some_items_in_the_cart(List<String> items) throws InterruptedException {
        for (String item: items){
            addAnItem(item);
        }
    }

    private void addAnItem(String item) throws InterruptedException {
        LandingPage landingPage= testBaseContext.pageObjectManager.getLandingPage();
        landingPage.search(item);
        SearchResultPage searchResultPage= testBaseContext.pageObjectManager.getSearchResultPage();
        searchResultPage.addToCart(2);
    }

    @When("I navigate to Cart page")
    public void i_navigate_to_Cart_page() throws InterruptedException {
        LandingPage landingPage= testBaseContext.pageObjectManager.getLandingPage();
        landingPage.openCart();
    }

}
