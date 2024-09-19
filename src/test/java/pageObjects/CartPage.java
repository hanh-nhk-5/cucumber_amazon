package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CartPage extends MasterPage {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(this.driver, this);

        //wait until the page loaded
        waitForElementAppeared(cartTitle, 10);
    }

    @FindBy(xpath= "//div[contains(@class, 'a-row sc-cart-header')]//h2") //= "Shopping Cart" OR "Your Amazon Cart is empty."
    WebElement cartTitle;

    String xpathEmptyCartTitle= "//div[@class='a-row sc-your-amazon-cart-is-empty']";

    final String xpathDeleteButton= "//input[@data-action='delete']";
    @FindBy(xpath = xpathDeleteButton)
    List<WebElement> deleteButtons;

    @FindBy(id= "sc-subtotal-label-activecart")
    WebElement totalItemsLabel;

    @FindBy(xpath= "//div[@data-name='Active Items']/div[@data-csa-c-type='item']")
    List<WebElement> addedItemBoxes;

    String xpathItemTitle= ".//li[contains(@class, 'sc-item-product-title-cont')]//span[@class='a-truncate-full a-offscreen']";
    String xpathQuantityDropdown= ".//select[@name='quantity']";
    String xpathQuantitySpan= ".//span[@class='a-dropdown-prompt']";

    public void clearCart() throws InterruptedException {
        if(!deleteButtons.isEmpty()) {
            int i= deleteButtons.size()-1;
            while(i>=0){
                Thread.sleep(1000);
                Actions actions= new Actions(driver);
                actions.moveToElement(deleteButtons.get(i)).click().perform();
                waitForElementDisappeared(deleteButtons.get(i), 10);
                i--;
            }


        }
    }

    private WebElement findElement(String xPath){
        return driver.findElement(By.xpath(xPath));
    }

    public boolean isCartEmpty() throws InterruptedException {
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

    public void updateQuantity(String itemTitle, int amount) {
        System.out.println("itemTitle= "+ itemTitle);
        Optional<WebElement> optional= addedItemBoxes.stream()
                .filter(i -> Objects.requireNonNull(i.findElement(By.xpath(xpathItemTitle)).getAttribute("innerText")).trim().toLowerCase().endsWith(itemTitle.toLowerCase()))
                .findAny();
        if(optional.isPresent()){
            WebElement itemBox= optional.get();
            System.out.println("inside if= " + itemBox.findElement(By.xpath(xpathItemTitle)).getAttribute("innerText"));
            Actions actions= new Actions(driver);

            WebElement quantityDropdown= itemBox.findElement(By.xpath(xpathQuantityDropdown));
            actions.moveToElement(quantityDropdown).click().perform();
            Select select= new Select(quantityDropdown);
            select.selectByValue(Integer.toString(amount));
        }
    }

    public boolean checkCartState(Map<String, Integer> addedItemsAndAmount){
        if(addedItemBoxes.size() != addedItemsAndAmount.size())
            return false;
        System.out.println("checkCartState");

        Map<String, Integer> clonedAddedItemsAndAmount = new HashMap<>(addedItemsAndAmount);
        String title;
        int amount;
        for (WebElement box: addedItemBoxes){
            System.out.println("innerText= " + box.findElement(By.xpath(xpathItemTitle)).getAttribute("innerText"));

            title= Objects.requireNonNull(box.findElement(By.xpath(xpathItemTitle)).getAttribute("innerText")).trim();
            amount= Integer.parseInt(Objects.requireNonNull(box.findElement(By.xpath(xpathQuantitySpan)).getAttribute("innerText")));

            for (String cachedTitle : clonedAddedItemsAndAmount.keySet()) {
                if (title.toLowerCase().trim().endsWith(cachedTitle.toLowerCase())) {
                    if (clonedAddedItemsAndAmount.get(cachedTitle) != amount)
                        return false;

                    clonedAddedItemsAndAmount.remove(cachedTitle);
                    break;
                }
            }
        }
        return true;
    }
}
