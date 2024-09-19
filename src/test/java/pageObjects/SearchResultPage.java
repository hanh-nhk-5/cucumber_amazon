package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends MasterPage {
    WebDriver driver;
    public SearchResultPage(WebDriver driver) {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(this.driver,this);
    }

    @FindBy(xpath="//button[contains(@class, 'a-button-text') and text()='Add to cart']")
    List<WebElement> addToCardButtons;

    @FindBy(xpath= "//button[contains(@class, 'a-button-text') and text()='Add to cart']/ancestor::div[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]//div[@data-cy='title-recipe']/h2/a/span")
    List<WebElement> searchedItemTitles;

    String xpathBranchName= "./parent::*/parent::*/preceding-sibling::div[@class='a-row a-color-secondary']/h2/span";

    public ArrayList<String> addSomeFirstItemsIntoCart(int numberOfItems){
        if(numberOfItems<=0)
            return null;

        String title;
        ArrayList<String> titles= new ArrayList<>();
        numberOfItems= Math.min(addToCardButtons.size(), numberOfItems);
        for(int i=0; i< numberOfItems; i++){
            addToCardButtons.get(i).click();
            waitForElementClickable(addToCardButtons.get(i), 5);

            title= searchedItemTitles.get(i).getText();
            List<WebElement> branchNameElements= searchedItemTitles.get(i).findElements(By.xpath(xpathBranchName));
            if(!branchNameElements.isEmpty())
                title= branchNameElements.get(0).getText() + " " + title;
            titles.add(title);
        }
        return titles;
    }
}
