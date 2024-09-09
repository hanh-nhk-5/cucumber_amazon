package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends BasePage{
    WebDriver driver;
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(this.driver,this);
    }


}
