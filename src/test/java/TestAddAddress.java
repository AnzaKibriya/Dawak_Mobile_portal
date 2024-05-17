import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.Test;

import java.awt.*;

public class TestAddAddress  extends BaseClass {


    @Test(priority = 1)
    public void loginApp() {
        test = extent.createTest("Login to Dawak App");
        Pages.AndroidAppLogin().handleSplashScreens();
        Pages.AndroidAppLogin().loginToDawakApp();

    }
    @Test(priority = 2)
    public void addAddress() throws AWTException, InterruptedException {
        test = extent.createTest("Add address");
        Pages.DawakAddressAddition().addAddress();
    }
}
