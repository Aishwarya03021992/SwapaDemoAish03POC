package FunctionTests;


import BaseSwapa.BaseClass;
import BaseSwapa.BrowserFactory;
import org.testng.annotations.Test;

import java.io.IOException;

public class PillotPollingTest extends BrowserFactory {

    BaseClass login;

    @Test
    public void pillotPollingForm() throws IOException {
        this.login = new BaseClass(driver);
        login.ClickMainLogin();
        login.setUsername(properties.getDataFromPropertyFile("username"));
        login.setPassword(properties.getDataFromPropertyFile("password"));
        login.ClickLoginbutton();
        login.clickResources();
        login.clickSRCForms();
        login.clickPilotPollingForm();
    }

}
