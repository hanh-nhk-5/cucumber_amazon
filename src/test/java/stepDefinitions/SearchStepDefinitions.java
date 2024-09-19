package stepDefinitions;

import io.cucumber.java.en.Given;
import pageObjects.MasterPage;
import pageObjects.SearchResultPage;
import utils.TestBaseContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SearchStepDefinitions {
    TestBaseContext testBaseContext;
    public SearchStepDefinitions(TestBaseContext testBaseContext){
        this.testBaseContext= testBaseContext;
    }

    @Given("I add some items into the Cart")
    public void addItemsInTheCart(List<String> searchedTexts){
        addItemsInTheCart(searchedTexts, 2);
    }

    public void addItemsInTheCart(List<String> searchedTexts, int numberOfItemsToBeAdded){
        for (String text: searchedTexts){
            searchThenAddItemIntoCart(text, numberOfItemsToBeAdded);
        }
    }

    @Given("^I add some items (.+) into the Cart$")
    public void addItemsInTheCart(String searchedStrings) {
        List<String> texts= Arrays.stream(searchedStrings.split(",")).collect(Collectors.toList());
        addItemsInTheCart(texts, 1);
    }

    private void searchThenAddItemIntoCart(String searchedText, int numberOfToBeAddedItems){
        MasterPage masterPage= testBaseContext.pageObjectManager.getMasterPage();
        masterPage.search(searchedText);
        SearchResultPage searchResultPage= testBaseContext.pageObjectManager.getSearchResultPage();
        ArrayList<String> itemTitles = searchResultPage.addSomeFirstItemsIntoCart(numberOfToBeAddedItems);
        for (String title: itemTitles){
            if(testBaseContext.itemTitlesAndAmountInCart.containsKey(title))
                testBaseContext.itemTitlesAndAmountInCart.put(title, testBaseContext.itemTitlesAndAmountInCart.get(title) + 1);
            else
                testBaseContext.itemTitlesAndAmountInCart.put(title, 1);
        }
    }
}
