import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.Test;

import java.awt.*;

public class TestDasboardWidgetPages extends BaseClass {


    @Test(priority = 1)
    public void loginApp() {
        test = extent.createTest("Login to Dawak App");
        Pages.AndroidAppLogin().handleSplashScreens();
        Pages.AndroidAppLogin().loginToDawakApp();

    }
    @Test(priority = 2)
    public void verifyTotalPatientNavigationScreen() throws AWTException, InterruptedException {
        test = extent.createTest("Verify the Patient Navigation Screen");
        Pages.DawakAppLandingPage().totalPatientswidget();
        Pages.MobileCommon().backToDashboardArrowButton();
    }
    @Test(priority = 3)
    public void verifyActivePrescriptionNavigationScreen() throws AWTException, InterruptedException {
        test = extent.createTest("Verify the Patient Navigation Screen");
        Pages.DawakAppLandingPage().openActivePrescription();
        Pages.MobileCommon().backToDashboardArrowButton();
    }
    @Test(priority = 3)
    public void verifyCancelledPrescriptionNavigationScreen() throws AWTException, InterruptedException {
        test = extent.createTest("Verify the Patient Navigation Screen");
        Pages.DawakAppLandingPage().openCancelPrescription();
        Pages.MobileCommon().backToDashboardArrowButton();
    }
    @Test(priority = 4)
    public void verifyCompletedPrescriptionNavigationScreen() throws AWTException, InterruptedException {
        test = extent.createTest("Verify the Patient Navigation Screen");
        Pages.DawakAppLandingPage().openCompletedPrescription();
        Pages.MobileCommon().backToDashboardArrowButton();
    }
}
