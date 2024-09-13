package stepDefinitions;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pageObjects.NotRobotPage;
import utils.TestBaseContext;

import java.io.File;
import java.io.IOException;

public class Hooks {
    TestBaseContext testBaseContext;
    public Hooks(TestBaseContext testBaseContext){
        this.testBaseContext= testBaseContext;
    }

    @Before
    public void scenarioPreparation() throws InterruptedException {
        overcomeNotRobot();
    }

    private void overcomeNotRobot() throws InterruptedException {
        NotRobotPage notRobotPage= testBaseContext.pageObjectManager.getNotRobot();
        Thread.sleep(9000);
        notRobotPage.confirm();
    }

    @AfterStep
    public void afterStep(Scenario scenario) throws IOException {
        if(scenario.isFailed()){
            File file= ((TakesScreenshot)testBaseContext.testBase.getWebDriver()).getScreenshotAs(OutputType.FILE);
            byte[] bytes= FileUtils.readFileToByteArray(file);
            scenario.attach(bytes, "image/png", "image");
        }
    }
}
