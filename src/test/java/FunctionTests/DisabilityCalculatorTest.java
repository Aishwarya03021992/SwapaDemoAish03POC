package FunctionTests;

import Utilties.*;
import org.testng.annotations.Test;
import PageObjects.DisabilityCalculatorPageObjects;
import java.io.IOException;


public class DisabilityCalculatorTest extends BrowserFactory {
    BaseTest login;
    DisabilityCalculatorPageObjects disabilityCalculatorPage;

    @Test
    public void validateDisabilityCalculator() throws InterruptedException, IOException {
        this.login = new BaseTest(driver);
        login.ClickMainLogin();
        login.setUsername(properties.getDataFromPropertyFile("username"));
        login.setPassword(properties.getDataFromPropertyFile("password"));
        login.ClickLoginbutton();
        login.clickResources();

        // Initialize DisabilityCalculatorPageObjects and perform operations
        this.disabilityCalculatorPage = new DisabilityCalculatorPageObjects(driver);
        disabilityCalculatorPage.selectDisabilityCalculator();
        disabilityCalculatorPage.enterDisabilityCalculatorValues();
        disabilityCalculatorPage.printDisabilityTableHeader();
        disabilityCalculatorPage.validateDisabilityTableHeader();
        disabilityCalculatorPage.validateRetirementYear();


    }
}
