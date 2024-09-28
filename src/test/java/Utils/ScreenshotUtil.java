package Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.Scenario;



public class ScreenshotUtil {
    private WebDriver driver;

    public ScreenshotUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void takeScreenshotAndAttachToScenario(Scenario scenario) {
        try {
            // Take screenshot
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            // Attach it to the Cucumber scenario
            scenario.attach(screenshot, "image/png", "Screenshot");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}