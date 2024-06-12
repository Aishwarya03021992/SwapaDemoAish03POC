package FunctionTests;


import Utilties.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class PillotPollingTest extends BrowserFactory {

    BaseTest login;

    @Test
    public void pillotPollingForm() throws IOException {
        this.login = new BaseTest(driver);
        login.ClickMainLogin();
        login.setUsername(properties.getDataFromPropertyFile("username"));
        login.setPassword(properties.getDataFromPropertyFile("password"));
        login.ClickLoginbutton();
        login.clickResources();
        login.clickSRCForms();
        login.clickPilotPollingForm();
    }

}
