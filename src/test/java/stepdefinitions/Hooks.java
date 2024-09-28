package stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Utils.ExcelReader;
import Utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    private WebDriver driver;
    private ScreenshotUtil screenshotUtil;
    private ExcelReader excelUtil;

    @Before
    public void setUp() {
        // Set Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        // Initialize ChromeDriver
        driver = new ChromeDriver(options);

        // Initialize ScreenshotUtil
        screenshotUtil = new ScreenshotUtil(driver);
        
        excelUtil = new ExcelReader("src/test/resource/test-data/orangehrmautomation.xlsx");
    }

    // This will execute after each step
    @AfterStep
    public void afterEachStep(Scenario scenario) {
        screenshotUtil.takeScreenshotAndAttachToScenario(scenario);
    }

    // This will run after the entire scenario
    @After(order = 0)
    public void tearDown() {
        // Close the browser session
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
    
    public ExcelReader getReader() {
        return excelUtil;
    }
}
