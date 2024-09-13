package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultPage extends MasterPage {
    WebDriver driver;
    public SearchResultPage(WebDriver driver) {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(this.driver,this);
    }

    @FindBy(id ="//div[contains(@cel_widget_id, 'MAIN-SEARCH_RESULTS')]")
    List<WebElement> resultBoxes;

    @FindBy(xpath="//button[contains(@class, 'a-button-text') and text()='Add to cart']")
    List<WebElement> addToCardButtons;

    @FindBy(xpath="//div[@class='atfc-spinner']")
    WebElement spinner;

    public void addToCart(int count) throws InterruptedException {
        if(count<=0)
            return;

        count= Math.min(addToCardButtons.size(), count);
        for(int i=0; i< count; i++){
            addToCardButtons.get(i).click();
            waitForElementClickable(addToCardButtons.get(i));
        }
    }
}
