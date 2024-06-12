package Utilties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitActions {
    WebDriver driver;
    WebDriverWait wait;

    public WaitActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void implicitWaitCommand() {
        // Set the implicit wait timeout to 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void explicitWaitForPageTitle(String pageTitle) {
        // Wait until the page title contains the specified text
        wait.until(ExpectedConditions.titleContains(pageTitle));
    }
    public void explicitWaitForElementToBeSelected(WebElement element) {
        // Wait until the element is selected
        wait.until(ExpectedConditions.elementToBeSelected(element));
    }

    public void explicitWaitForElementToBeClickable(WebElement element) {
        // Wait until the element is clickable
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void explicitWaitForElementToBeLocated(By locator) {
        // Wait until the element located by the given locator is present
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

}
