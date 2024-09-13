package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObjects.CartPage;
import utils.TestBaseContext;

public class CartStepDefinitions {
    TestBaseContext testBaseContext;
    public CartStepDefinitions(TestBaseContext testBaseContext){
        this.testBaseContext= testBaseContext;
    }

    @When("I delete all items")
    public void i_I_delete_all_items() throws InterruptedException {
        CartPage cardPage= testBaseContext.pageObjectManager.getCartPage();
        cardPage.clearCart();
    }

    @Then("The cart will be empty")
    public void the_cart_will_be_empty() throws InterruptedException {
        CartPage cardPage= testBaseContext.pageObjectManager.getCartPage();
        Assert.assertTrue(cardPage.isCartEmtpy(), "Expected: Cart is empty. Actual: Cart is not empty");
    }

}
