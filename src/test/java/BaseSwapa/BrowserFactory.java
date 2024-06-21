package BaseSwapa;

import DriverActions.WaitActions;
import DriverActions.WebDriverActions;
import Utilties.PropertiesReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

import java.io.IOException;

public class BrowserFactory {
    protected WebDriver driver;
    public WebDriverActions webDriverUtils = new WebDriverActions();
    public WaitActions wait;
    PropertiesReader property;
    public PropertiesReader properties = new PropertiesReader(driver);

    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/EssentialDataModule.html");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("SWAPA Automation POC Report");
        sparkReporter.config().setReportName("SWAPA Automation POC Test Report");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "Aishwarya Kamaraj");
    }

    @BeforeClass
    public void OpenBrowser() throws IOException {
        String browserName = this.properties.getDataFromPropertyFile("BrowserName");
        if (browserName.equals("chrome")) {
            this.driver = new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            this.driver = new FirefoxDriver();
        } else if (browserName.equals("edge")) {
            this.driver = new EdgeDriver();
        } else if (browserName.equals("safari")) {
            this.driver = new SafariDriver();
        }

        this.webDriverUtils.maximize(this.driver);
        this.wait = new WaitActions(this.driver);
        this.wait.implicitWaitCommand();
        driver.get(properties.getDataFromPropertyFile("url"));

    }

    @AfterClass
    public void CloseBrowser() {
        this.webDriverUtils.minimize(this.driver);
        driver.quit();
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }


}
