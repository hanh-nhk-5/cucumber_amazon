package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObjects.CartPage;
import pageObjects.MasterPage;
import utils.TestBaseContext;
import java.util.Map;

public class CartStepDefinitions {
    TestBaseContext testBaseContext;
    public CartStepDefinitions(TestBaseContext testBaseContext){
        this.testBaseContext= testBaseContext;
    }

    @When("I delete all items on Cart")
    public void deleteAllItems() throws InterruptedException {
        clearCart();
    }

    @Then("The cart will be empty")
    public void checkCartBeEmpty(){
        CartPage cardPage= testBaseContext.pageObjectManager.getCartPage();
        Assert.assertTrue(cardPage.isCartEmpty(), "Expected: Cart is empty. Actual: Cart is not empty");
    }

    @Given("The cart is empty")
    public void ensureCartBeEmpty() throws InterruptedException {
        clearCart();
    }

    private void clearCart() throws InterruptedException {
        MasterPage masterPage= testBaseContext.pageObjectManager.getMasterPage();
        masterPage.navigateToCart();
        CartPage cardPage= testBaseContext.pageObjectManager.getCartPage();
        cardPage.clearCart();
    }

    @Given("^On the Cart page, I modify amount of the added items in random order (.+)$")
    public void modifyAmountOfItemsInOrder(String amountStrings){
        MasterPage masterPage= testBaseContext.pageObjectManager.getMasterPage();
        masterPage.navigateToCart();

        CartPage cartPage= testBaseContext.pageObjectManager.getCartPage();

        String [] amounts= amountStrings.split(",");
        int index= 0;
        int amount;
        for (Map.Entry<String, Integer> itemEntry : testBaseContext.itemTitlesAndAmountInCart.entrySet()) {
            amount=  Integer.parseInt(amounts[index].trim());
            cartPage.updateQuantity(itemEntry.getKey(), amount);
            itemEntry.setValue(amount);
            index ++;
        }
    }

    @Then("I should see all items in the Cart as before sign out")
    public void checkCartTheSameAsBeforeSignOut() {
        MasterPage masterPage= testBaseContext.pageObjectManager.getMasterPage();
        masterPage.navigateToCart();
        CartPage cartPage= testBaseContext.pageObjectManager.getCartPage();
        Assert.assertTrue(cartPage.checkCartState(testBaseContext.itemTitlesAndAmountInCart), "Cart is not the same as before sign out" );
    }

}
