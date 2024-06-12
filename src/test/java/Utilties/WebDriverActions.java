package Utilties;

import org.openqa.selenium.WebDriver;

public class WebDriverActions {
    public WebDriverActions() {
    }
    public void maximize(WebDriver driver) {
        driver.manage().window().maximize();
    }
    public void minimize(WebDriver driver) {
        driver.manage().window().minimize();
    }
}
