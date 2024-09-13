package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class CartPage extends MasterPage {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath= "//div[contains(@class, 'a-row sc-cart-header')]//h2")
    WebElement cartTitle;

    @FindBy(xpath = "//input[@data-action='delete']")
    List<WebElement> deleteButtons;

    public void clearCart(){
        if(!deleteButtons.isEmpty()) {
            System.out.println("Delete total count: " + deleteButtons.size());
            for(int i= deleteButtons.size()-1; i >=0; i--){
                Actions actions= new Actions(driver);
                actions.moveToElement(deleteButtons.get(i)).click().perform();
                waitForElementDisappeared(deleteButtons.get(i));
                System.out.println("delete index= " + i);
            }
        }
    }

    public boolean isCartEmtpy() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Cart title: " + cartTitle.getText());
        return cartTitle.getText().trim().equalsIgnoreCase("Your Amazon Cart is empty.");
    }
}
