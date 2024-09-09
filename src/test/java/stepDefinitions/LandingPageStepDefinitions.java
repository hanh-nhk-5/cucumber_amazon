package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.TestBase;
import utils.TestBaseContext;

public class LandingPageStepDefinitions {
    TestBaseContext testBaseContext;
    public LandingPageStepDefinitions(TestBaseContext testBaseContext){
        this.testBaseContext= testBaseContext;
    }

    @Given("user is in the landing page")
    public void user_is_in_the_landing_page() throws InterruptedException {
    }
    @When("user type text in the search box")
    public void user_type_text_in_the_search_box() {

    }
    @When("user hit enter")
    public void user_hit_enter() {

    }
    @Then("the search result page will be displayed")
    public void the_search_result_page_will_be_displayed() {

    }
}
