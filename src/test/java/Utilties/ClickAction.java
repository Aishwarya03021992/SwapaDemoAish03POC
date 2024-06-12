package Utilties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ClickAction {
    WebDriver driver;
    Actions actions;

    public ClickAction(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }
    public void clickAction(WebElement element) {
        actions = new Actions(driver);
        actions.click(element).perform();
    }
}
