package FunctionTests;

import BaseSwapa.BaseClass;
import BaseSwapa.BrowserFactory;
import Utilties.ScreenshotUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import PageObjects.DisabilityCalculatorPageObjects;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestResult;


public class DisabilityCalculatorTest extends BrowserFactory {
    BaseClass login;
    DisabilityCalculatorPageObjects disabilityCalculatorPage;

    @Test
    public void validateDisabilityCalculator() throws InterruptedException, IOException {
        test = extent.createTest("2024 Disability Calculator", "Validate Disability 2024 Calculator");
        try {
            this.login = new BaseClass(driver);
            login.ClickMainLogin();
            login.setUsername(properties.getDataFromPropertyFile("username"));
            login.setPassword(properties.getDataFromPropertyFile("password"));
            login.ClickLoginbutton();
            login.clickResources();

            // Initialize DisabilityCalculatorPageObjects and perform operations
            this.disabilityCalculatorPage = new DisabilityCalculatorPageObjects(driver);
            disabilityCalculatorPage.selectDisabilityCalculator();
            disabilityCalculatorPage.enterDisabilityCalculatorValues();
            //disabilityCalculatorPage.printDisabilityTableHeader();
            disabilityCalculatorPage.validateDisabilityTableHeader();
            disabilityCalculatorPage.validateRetirementYear();
        }
        catch (Exception e) {
            e.printStackTrace();
            test.fail("Test failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void getResult(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
            test.addScreenCaptureFromPath(screenshotPath); // Add screenshot to the report
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test Case Passed", ExtentColor.GREEN));
        } else {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }


}
