package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ElementUtil {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public ElementUtil(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Default wait time
    }

    // Find element
    public WebElement getElement(By locator) {
        try {
            return driver.findElement(locator);
        } catch (NoSuchElementException e) {
            System.err.println("Element not found: " + locator);
            throw e;
        }
    }

    // Find multiple elements
    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    // Click on element
    public void clickElement(By locator) {
        try {
            waitForElementToBeClickable(locator).click();
        } catch (Exception e) {
            System.err.println("Unable to click on element: " + locator);
            throw e;
        }
    }

    // Send keys to element
    public void sendKeys(By locator, String text) {
        try {
            WebElement element = waitForElementToBeVisible(locator);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            System.err.println("Unable to send keys to element: " + locator);
            throw e;
        }
    }

    // Get text from element
    public String getText(By locator) {
        return waitForElementToBeVisible(locator).getText();
    }

    // Select dropdown by visible text
    public void selectDropdownByVisibleText(By locator, String visibleText) {
        WebElement dropdown = getElement(locator);
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }

    // Select dropdown by value
    public void selectDropdownByValue(By locator, String value) {
        WebElement dropdown = getElement(locator);
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    // Select dropdown by index
    public void selectDropdownByIndex(By locator, int index) {
        WebElement dropdown = getElement(locator);
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }

    // Wait for element to be visible
    public WebElement waitForElementToBeVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            System.err.println("Element not visible: " + locator);
            throw e;
        }
    }

    // Wait for element to be clickable
    public WebElement waitForElementToBeClickable(By locator) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            System.err.println("Element not clickable: " + locator);
            throw e;
        }
    }

    // Check if element is displayed
    public boolean isElementDisplayed(By locator) {
        try {
            return getElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Check if element is enabled
    public boolean isElementEnabled(By locator) {
        try {
            return getElement(locator).isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Scroll to element using JavaScript
    public void scrollToElement(By locator) {
        WebElement element = getElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Perform mouse hover over element
    public void hoverOverElement(By locator) {
        WebElement element = getElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    // Drag and drop
    public void dragAndDrop(By sourceLocator, By targetLocator) {
        WebElement sourceElement = getElement(sourceLocator);
        WebElement targetElement = getElement(targetLocator);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(sourceElement, targetElement).perform();
    }

    // Check if element exists
    public boolean doesElementExist(By locator) {
        return !driver.findElements(locator).isEmpty();
    }
    
    // Get attribute from element
    public String getAttribute(By locator, String attribute) {
        return getElement(locator).getAttribute(attribute);
    }
    
    // Wait until page is fully loaded
    public void waitForPageLoad() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        wait.until(driver -> js.executeScript("return document.readyState").equals("complete"));
    }
}
