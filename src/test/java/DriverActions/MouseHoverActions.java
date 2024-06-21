package DriverActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MouseHoverActions {
    WebDriver driver;
    Actions actions;

    public MouseHoverActions(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public void moveToElement(WebElement element) {
        actions.moveToElement(element).perform();
    }
}
