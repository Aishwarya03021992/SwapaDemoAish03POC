package Utilties;

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
            this.driver= new SafariDriver();
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


}
