package cucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features"
        , glue = "stepDefinitions"
        , monochrome = true
        , plugin= {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
//        , dryRun = true
)
public class TestNGRunner extends AbstractTestNGCucumberTests {


}
