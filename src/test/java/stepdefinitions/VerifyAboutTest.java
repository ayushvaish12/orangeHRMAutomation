package stepdefinitions;

import static org.testng.Assert.assertEquals;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utils.ElementUtil;
import Utils.ExcelReader;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VerifyAboutTest {

	private final Hooks hooks;
	private WebDriver driver;
	private ElementUtil elementUtil;
	private ExcelReader excelUtil;

	private String sheet;

	/************* Locators **************/

	By username = By.xpath("//input[@placeholder='Username']");
	By password = By.xpath("//input[@placeholder='Password']");
	By login = By.xpath("//button[@type='submit']");
	By dashboard = By.xpath("//h6[text()='Dashboard']");
	By profiledd = By.xpath("//span[@class='oxd-userdropdown-tab']");
	By about = By.xpath("//ul[@class='oxd-dropdown-menu']//li[1]");
	By support = By.xpath("//ul[@class='oxd-dropdown-menu']//li[2]");
	

	public VerifyAboutTest(Hooks hooks) {
		this.hooks = hooks;
		this.driver = hooks.getDriver();
		this.elementUtil = new ElementUtil(driver);
		this.excelUtil = hooks.getReader();
	}

	@Given("I am on the OrangeHRM login page from given sheetname {string}")
	public void i_am_on_the_orange_hrm_login_page_from_given_sheetname(String sheetName) throws InterruptedException {

		this.sheet = sheetName;

		if (excelUtil.isSheetExist(sheetName)) {
			driver.get(excelUtil.getCellData(sheetName, "url", 2));
		} else {
			
			ExtentCucumberAdapter.addTestStepLog("Sheet \"" + sheetName + "\" does not exist in the Excel file.");
			//Assert.fail("Test failed: Sheet \"" + sheetName + "\" not found in Excel.");
		}
		Thread.sleep(2000);
	}

	@When("I enter valid credentials for OrangeHRM")
	public void i_enter_valid_credentials_for_orange_hrm() throws InterruptedException {

		elementUtil.waitForElementToBeVisible(username);
		elementUtil.sendKeys(username, excelUtil.getCellData(sheet, "username", 2));
		elementUtil.sendKeys(password, excelUtil.getCellData(sheet, "password", 2));
		Thread.sleep(2000);
	}

	@When("I click the login button")
	public void i_click_the_login_button() throws InterruptedException {
		elementUtil.clickElement(login);
	}

	@Then("I should be logged into the OrangeHRM dashboard from {string}")
	public void i_should_be_logged_into_the_orange_hrm_dashboard_from(String sheet) throws InterruptedException {

		elementUtil.waitForElementToBeVisible(dashboard);
		if(elementUtil.isElementDisplayed(dashboard)) {
			ExtentCucumberAdapter.addTestStepLog("Landing on Dashboard successful");
			elementUtil.waitForElementToBeClickable(profiledd);
			elementUtil.clickElement(profiledd);
			this.sheet = sheet;
		}
		else {
			ExtentCucumberAdapter.addTestStepLog("Dashboard not visible - failed");
			Assert.fail("Dashboard not visible - failed");
		}
		
		Thread.sleep(2000);
	}

	@When("I navigate to the about section")
	public void i_navigate_to_the_about_section() throws InterruptedException {

		elementUtil.clickElement(about);
		Thread.sleep(2000);
	}

	@Then("I should see the about section details")
	public void i_should_see_the_about_section_details() {
		
		String module = excelUtil.getCellData(sheet, "module", 2);
		String aboutvalue = excelUtil.getCellData(sheet, "value", 2);
		By aboutdetails = By.xpath("//p[text()='"+aboutvalue+"']");
		if(elementUtil.isElementDisplayed(aboutdetails)) {
			ExtentCucumberAdapter.addTestStepLog(module + " section details verified");
		}
		else {
			ExtentCucumberAdapter.addTestStepLog("Profile section details failed");
			Assert.fail("Profile section details failed");
		}
	}
	
	@When("I navigate to the support section")
	public void i_navigate_to_the_support_section() throws InterruptedException {

		elementUtil.clickElement(support);
		Thread.sleep(2000);
	}

	@Then("I should see the support section details")
	public void i_should_see_the_support_section_details() {
		
		String module = excelUtil.getCellData(sheet, "module", 3);
		String supportvalue = excelUtil.getCellData(sheet, "value", 3);
		By supportdetails = By.xpath("//a[text()='"+supportvalue+"']");
		if(elementUtil.isElementDisplayed(supportdetails)) {
			ExtentCucumberAdapter.addTestStepLog(module + " section details verified");
		}
		else {
			ExtentCucumberAdapter.addTestStepLog("Profile section details failed");
			Assert.fail("Profile section details failed");
		}
	}
}
