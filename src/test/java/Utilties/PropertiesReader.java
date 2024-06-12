package Utilties;

import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    WebDriver driver;

    public PropertiesReader(WebDriver driver) {
        this.driver = driver;
    }

    public String getDataFromPropertyFile(String key) throws IOException {

        Properties file = new Properties();

        FileInputStream Path = new FileInputStream("/Users/presidio/Documents/SWAPA POC/Automation/AutomationPOC/src/main/resources/Properties");

        file.load(Path);

        String KeyValue = file.getProperty(key);

        return KeyValue;
    }
}
