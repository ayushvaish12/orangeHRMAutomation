package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.picocontainer.PicoFactory;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resource/features", // Path to your feature files
        glue = {"stepdefinitions"}, // Package names for step definitions and hooks
        plugin = {"pretty", "html:target/cucumber-reports.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, // Reporting options
        monochrome = true, // Set to true for a more readable console output
        objectFactory = PicoFactory.class
)
public class TestRunner {
}