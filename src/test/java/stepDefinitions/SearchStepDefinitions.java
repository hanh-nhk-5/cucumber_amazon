package stepDefinitions;

import io.cucumber.java.en.Given;
import pageObjects.LandingPage;
import pageObjects.SearchResultPage;
import utils.TestBaseContext;

import java.util.List;

public class SearchStepDefinitions {
    TestBaseContext testBaseContext;
    public SearchStepDefinitions(TestBaseContext testBaseContext){
        this.testBaseContext= testBaseContext;
    }

    @Given("I add some items in the Cart")
    public void i_add_some_items_in_the_cart(List<String> items){
        for (String item: items){
            addAnItem(item);
        }
    }

    private void addAnItem(String item){
        LandingPage landingPage= testBaseContext.pageObjectManager.getLandingPage();
        landingPage.search(item);
        SearchResultPage searchResultPage= testBaseContext.pageObjectManager.getSearchResultPage();
        searchResultPage.addToCart(2);
    }
}
