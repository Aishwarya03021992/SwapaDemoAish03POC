package BaseSwapa;

import DriverActions.ClickAction;
import DriverActions.WaitActions;
import Utilties.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;

public class BaseClass {
    WebDriver driver;
    SoftAssert softAssert;
    PropertiesReader property;
    WaitActions wait;
    ClickAction clicks;

    /**
     * Constructor to initialize WebDriver and utilities.
     *
     * @param driver The WebDriver instance to interact with the browser.
     */
    public BaseClass(WebDriver driver) {
        this.driver = driver;
        this.softAssert = new SoftAssert();
        this.property = new PropertiesReader(driver);
        this.wait = new WaitActions(this.driver);
        this.clicks = new ClickAction(this.driver);
    }

    // Locators for elements on the Login page
    private By mainLogin = By.linkText("Log in");
    private By UserNamefield = By.id("signInName");
    private By Passwordfield = By.id("password");
    private By Logininbutton = By.id("next");

    // Locator for the 'Resources' link
    private By resources = By.xpath("//span[text() = 'Resources']");
    private By SrcForms = By.xpath("(//a[contains(text(),'SRC')])[1]");
    private By PilotPollingForm = By.xpath("(//a[contains(text(),'Pilot')])[3]");

    /**
     * Page Objects of Pillot Polling Module
     **/
    private By quest01 = By.xpath("(//label[@class='FormChoice__Container'][normalize-space()='Long Pairing Length (4 days)'])[1]");
    private By quest02 = By.xpath("(//label[@class='FormChoice__Container'][normalize-space()='Long Pairing Length (4 days)'])[2]");
    private By quest03 = By.xpath("(//label[@class='FormChoice__Container'][normalize-space()='Long Pairing Length (4 days)'])[3]");
    private By quest07 = By.xpath("(//label[@class='FormChoice__Container'][normalize-space()='Long Pairing Length (4 days)'])[4]");
    private By quest04 = By.xpath("(//label[@class='FormChoice__Container'][normalize-space()='AM'])");
    private By quest05 = By.xpath("(//label[@class='FormChoice__Container'][normalize-space()='1 day'])");
    private By quest06 = By.xpath("(//label[@class='FormChoice__Container'][normalize-space()='1 leg'])");
    private By quest08 = By.xpath("(//label[@class='FormChoice__Container'][normalize-space()='Yes'])");
    private By quest09 = By.xpath("(//label[@class='FormChoice__Container'][normalize-space()='A 3 day preceded or followed by a 1 day trip'])");
    private By quest10 = By.xpath("(//label[@class='FormChoice__Container'][normalize-space()='3 on 3 off'])");
    private By quest11 = By.xpath("(//label[@class='FormChoice__Container'][normalize-space()='No, I almost always drive'])");
    private By submitForm = By.xpath("(//button[@name='submit'][normalize-space()='Submit'])");

    // Functions For Login
    public void ClickMainLogin() {

        this.wait.explicitWaitForElementToBeLocated(mainLogin);
        driver.findElement(mainLogin).click();
    }

    public void setUsername(String username) {

        this.wait.explicitWaitForElementToBeLocated(UserNamefield);
        driver.findElement(UserNamefield).sendKeys(username);
    }

    public void setPassword(String password) {

        this.wait.explicitWaitForElementToBeLocated(Passwordfield);
        driver.findElement(Passwordfield).sendKeys(password);
    }

    public void ClickLoginbutton() throws IOException {

        this.wait.explicitWaitForElementToBeLocated(Logininbutton);
        // Click on the login button
        driver.findElement(Logininbutton).click();

        // Get the title of the home page
        String ActualHomePageTitle = driver.getTitle();

        // Assert that the actual home page title matches the expected title from the properties file
        this.softAssert.assertEquals(ActualHomePageTitle, this.property.getDataFromPropertyFile("ExpectedHomePageTitle"), "Home Page Tile Mismatch");

        // Perform all assertions
        this.softAssert.assertAll();
    }

    // Functions for Resource Link
    public void clickResources() {
        // Wait for the 'Resources' link to be located
        this.wait.explicitWaitForElementToBeLocated(resources);
        // Click on the 'Resources' link
        driver.findElement(resources).click();
    }

    public void clickSRCForms() {

        // Wait for the 'SRC Forms' link to be located
        this.wait.explicitWaitForElementToBeLocated(SrcForms);
        // Click on the 'SRC Forms' link
        driver.findElement(SrcForms).click();
    }

    public void clickPilotPollingForm() throws IOException {
        // Wait for the 'PilotPollingForm' link to be located
        this.wait.explicitWaitForElementToBeLocated(PilotPollingForm);
        // Click on the 'Pilot Polling Form' link
        driver.findElement(PilotPollingForm).click();
        // Verify the page title
        String ActualPilotPollingFormPageTitle = driver.getTitle();
        this.softAssert.assertEquals(ActualPilotPollingFormPageTitle, this.property.getDataFromPropertyFile("ExceptedPilotPollingFormPageTitle"), "Home Page Tile Mismatch");
        this.softAssert.assertAll();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        clicks.clickAction(driver.findElement(quest01));
        clicks.clickAction(driver.findElement(quest02));
        clicks.clickAction(driver.findElement(quest03));
        clicks.clickAction(driver.findElement(quest04));
        clicks.clickAction(driver.findElement(quest05));
        clicks.clickAction(driver.findElement(quest06));
        clicks.clickAction(driver.findElement(quest07));
        clicks.clickAction(driver.findElement(quest08));
        clicks.clickAction(driver.findElement(quest09));
        clicks.clickAction(driver.findElement(quest10));
        clicks.clickAction(driver.findElement(quest11));
        clicks.clickAction(driver.findElement(submitForm));

    }

}
