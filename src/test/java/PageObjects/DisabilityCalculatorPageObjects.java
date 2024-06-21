package PageObjects;

import DriverActions.ClickAction;
import DriverActions.MouseHoverActions;
import DriverActions.SelectAction;
import DriverActions.WaitActions;
import Utilties.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class DisabilityCalculatorPageObjects {

    WebDriver driver;
    SoftAssert softAssert;
    PropertiesReader property;
    WaitActions wait;
    ClickAction clicks;
    SelectAction selectAction;  // Declare an instance of SelectAction
    MouseHoverActions hoverActions;  // Declare an instance of MouseHoverActions

    public DisabilityCalculatorPageObjects(WebDriver driver) {
        this.driver = driver;
        this.softAssert = new SoftAssert();
        this.property = new PropertiesReader(driver);
        this.wait = new WaitActions(this.driver);
        this.clicks = new ClickAction(this.driver);
        this.selectAction = new SelectAction(this.driver);  // Initialize the SelectAction instance
        this.hoverActions = new MouseHoverActions(this.driver);  // Initialize the MouseHoverActions instance

    }

    /**
     * Page Objects of 2024 Disability Calculator
     **/
    private By calculators = By.xpath("//div[@class='dropdown-menu show']//a[contains(.,'Calculators')]");
    private By disabilityCalculators = By.xpath("//div[@class='dropdown-menu show']//a[@class='dropdown-item'][normalize-space()='2024 Disability Calculator']");
    private By seniority = By.name("seniority");
    private By payRate = By.name("payrate");
    private By STDRate = By.name("stdplan");
    private By LTDRate = By.name("ltdplan");
    private By taxRate = By.name("taxrate");
    private By sickBank = By.name("sickbank");
    private By birthDate = By.xpath("//input[@placeholder='01/01/0001']");
    private By hireDate = By.xpath("//input[@placeholder='04/21/2024']");
    private By lolTaxability = By.name("loltaxability");
    private By sickUse = By.name("sickuse");
    private By calculateButton = By.xpath("//button[normalize-space()='Calculate']");
    private By disabilityCalculatorHeading = By.xpath("//h1[contains(text(),'2024 Disability Calculator')]");

    /**
     * Page Objects of 2024 Disability Calculator Table
     **/
    private By disabilityCalculatorTable = By.xpath("//table[@class='swapatable text-center']");
    private By lastColumnHeader = By.xpath("(//tr//th[normalize-space()='Taxable Sick + Taxable LOL + STD + LTD A'])[1]");
    private By retirementYear = By.xpath("(//td[normalize-space()='07-2026'])[1]");
    private By resources = By.xpath("//span[text() = 'Resources']");

    /**
     * Functions of 2024 Disability Calculator Module
     **/
    public void selectDisabilityCalculator() throws InterruptedException {
        WebElement menuHoverLink = driver.findElement(resources);
        hoverActions.moveToElement(menuHoverLink);
        clicks.clickAction(driver.findElement(calculators));
        //this.wait.explicitWaitForElementToBeLocated(disabilityCalculators);
        Thread.sleep(2000);
        clicks.clickAction(driver.findElement(disabilityCalculators));
    }

    public void enterDisabilityCalculatorValues() throws InterruptedException, IOException {
        selectAction.selectByVisibleText(driver.findElement(seniority), "FO");
        selectAction.selectByIndex(driver.findElement(payRate), 1);
        selectAction.selectByIndex(driver.findElement(STDRate), 0);
        selectAction.selectByIndex(driver.findElement(LTDRate), 1);
        selectAction.selectByIndex(driver.findElement(taxRate), 1);
        selectAction.selectByIndex(driver.findElement(lolTaxability), 1);
        selectAction.selectByIndex(driver.findElement(sickUse), 1);
        driver.findElement(sickBank).sendKeys(property.getDataFromPropertyFile("sickBank"));
        driver.findElement(birthDate).clear();
        driver.findElement(birthDate).sendKeys(property.getDataFromPropertyFile("birthdate"));
        clicks.clickAction(driver.findElement(disabilityCalculatorHeading));
        driver.findElement(hireDate).clear();
        driver.findElement(hireDate).sendKeys(property.getDataFromPropertyFile("hireDate"));
        clicks.clickAction(driver.findElement(disabilityCalculatorHeading));
        clicks.clickAction(driver.findElement(calculateButton));
        // Get the title of the Calculator page
        String calculatorPageTitle = driver.getTitle();
        // Assert that the Calculator page title matches the expected title from the properties file
        Assert.assertEquals(calculatorPageTitle, this.property.getDataFromPropertyFile("ExpectedCalculatorPageTitle"), "Calculator Page Tile Mismatch");

    }

    public void printDisabilityTableHeader() throws InterruptedException {
        WebElement Table = driver.findElement(disabilityCalculatorTable);
        List<WebElement> rowsList = Table.findElements(By.tagName("tr"));
        List<WebElement> columnsList = null;
        for (WebElement row : rowsList) {
            columnsList = row.findElements(By.tagName("th"));
            for (WebElement column : columnsList) {
                System.out.println("Column Header : " + column.getText() + ", ");
            }
        }
    }

    public void validateDisabilityTableHeader() throws InterruptedException {
        WebElement Table = driver.findElement(disabilityCalculatorTable);
        List<WebElement> columnsLists = Table.findElements(By.tagName("th"));
        WebElement lastHeading = columnsLists.get(columnsLists.size() - 1);
        String heading = lastHeading.getText();
        System.out.println("Column Header : " + columnsLists.get(columnsLists.size() - 1).getText() + ", ");
        Assert.assertTrue(heading.contains("STD"), "Text 'STD' is not present");
        Assert.assertTrue(heading.contains("LTD B"), "Text 'LTD B' is not present");
        Assert.assertTrue(heading.contains("TAXABLE"), "Text 'TAXABLE' is not present");
    }

    public void validateRetirementYear() {
        String birthdate = "08/09/1961";
        int retirementAge = 65;

        // Correct date formatter for parsing birthdate
        DateTimeFormatter birthdateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        try {
            // Parse the birthdate
            LocalDate birthDate = LocalDate.parse(birthdate, birthdateFormatter);
            // Calculate the retirement date by adding retirementAge years
            LocalDate calculatedRetirementDate = birthDate.plusYears(retirementAge);
            System.out.println("Given Retirement Date: " + calculatedRetirementDate);

            // Get the year and month of the calculated retirement date
            int localYear = calculatedRetirementDate.getYear();
            System.out.println("Calculated Retirement Year: " + localYear);

            int localMonth = calculatedRetirementDate.getMonthValue();
            int localMonth01 = localMonth - 1;
            System.out.println("Calculated Retirement Month: " + localMonth01);

            // Fetch the application retirement month/year from the web element
            WebElement rYear = driver.findElement(retirementYear);
            String monthYear = rYear.getText(); // Assuming the text is in MM-yyyy format
            System.out.println("Application Retirement Month/Year: " + monthYear);

            // Correct date formatter for parsing the month/year
            DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MM-yyyy");

            // Parse the monthYear string directly as YearMonth
            YearMonth applicationRetirementYearMonth = YearMonth.parse(monthYear, monthYearFormatter);

            int applicationRetirementYear = applicationRetirementYearMonth.getYear();
            System.out.println("Application Retirement Year: " + applicationRetirementYear);

            int applicationRetirementMonth = applicationRetirementYearMonth.getMonthValue();
            System.out.println("Application Retirement Month: " + applicationRetirementMonth);

            Assert.assertEquals(localYear, applicationRetirementYear, "Calculated Retirement Year Matching with Application");
            Assert.assertEquals(localMonth01, applicationRetirementMonth, "Calculated Retirement Month Matching with Application");

        } catch (DateTimeParseException e) {
            System.err.println("Error parsing the date: " + e.getMessage());
        }
    }
}
