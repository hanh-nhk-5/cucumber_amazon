package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObjects.MasterPage;
import pageObjects.SignInPage;
import utils.TestBaseContext;

public class AuthenticationStepDefinitions {
    TestBaseContext testBaseContext;
    public AuthenticationStepDefinitions(TestBaseContext testBaseContext){
        this.testBaseContext= testBaseContext;
    }

    @Given("I already signed in")
    public void i_already_signed_in() throws InterruptedException {
        MasterPage masterPage= testBaseContext.pageObjectManager.getMasterPage();
        if(!masterPage.isSignedIn()){
            SignInPage signInPage= masterPage.openSignInPage();
            signIn(signInPage);
        }
    }

    @When("I sign in")
    public void signIn() throws InterruptedException {
//        MasterPage masterPage= testBaseContext.pageObjectManager.getMasterPage();
//        SignInPage signInPage= masterPage.openSignInPage();
        SignInPage signInPage= testBaseContext.pageObjectManager.getSignInPage();
        signIn(signInPage);
    }

    private void signIn(SignInPage signInPage) throws InterruptedException {
        signInPage.signIn(System.getProperty("USERNAME"), System.getProperty("PASSWORD"));
        Thread.sleep(10000);//to have enough time to input capcha manually, just in case
    }

    @Then("I am logged in successfully")
    public void i_am_logged_in_successfully() {
        MasterPage masterPage= testBaseContext.pageObjectManager.getMasterPage();
        Assert.assertTrue(masterPage.isSignedIn());
    }

    @When("I navigate to Cart page")
    public void i_navigate_to_Cart_page(){
        MasterPage masterPage= this.testBaseContext.pageObjectManager.getMasterPage();
        masterPage.navigateToCart();
    }

    @When("I sign out")
    public void i_sign_out() {
        MasterPage masterPage= testBaseContext.pageObjectManager.getMasterPage();
        masterPage.signOut();
    }

    @Given("I have not logged in yet")
    public void i_have_not_logged_in_yet() {
        MasterPage masterPage= testBaseContext.pageObjectManager.getMasterPage();
        if(masterPage.isSignedIn()){
            masterPage.signOut();
        }
    }
}
