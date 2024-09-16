package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CartPage extends MasterPage {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath= "//div[contains(@class, 'a-row sc-cart-header')]//h2")
    WebElement cartTitle;

    String xpathEmptyCartTitle= "//div[@class='a-row sc-your-amazon-cart-is-empty']";

    @FindBy(xpath = "//input[@data-action='delete']")
    List<WebElement> deleteButtons;

    @FindBy(id= "sc-subtotal-label-activecart")
    WebElement totalItemsLabel;

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
        List<WebElement> emptyCartTitles= this.driver.findElements(By.xpath(xpathEmptyCartTitle));
        if(!emptyCartTitles.isEmpty()){
            System.out.println("if(!emptyCartTitles.isEmpty()) - " + emptyCartTitles.get(0).getText());
            return true;
        }

        if (cartTitle.getText().trim().equalsIgnoreCase("Your Amazon Cart is empty.")) {
            System.out.println("title= Your Amazon Cart is empty.");
            return true;
        }

        waitForElementAppeared(totalItemsLabel, 10);
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(totalItemsLabel.getText());

        int total= 0;
        while (matcher.find()) {
            total=  Integer.parseInt(matcher.group());
        }

        return total== 0;
    }
}
