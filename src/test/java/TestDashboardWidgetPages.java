import Helper.BaseClass;
import Pages.Pages;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;

import java.awt.*;

public class TestDashboardWidgetPages extends BaseClass {


    @Test(priority = 1)
    public void verifyTotalPatientNavigationScreen() throws InterruptedException {
        test= extent.createTest("Verifying Dashboard Widgets");
        Pages.MobileCommon().launchApp();
        test.log(Status.INFO,"Verify the Patient Navigation Screen");
        Pages.DawakAppLandingPage().totalPatientsWidget();
        Pages.MobileCommon().backToDashboardArrowButton();
    }
    @Test(priority = 2)
    public void verifyActivePrescriptionNavigationScreen() throws InterruptedException {
        test.log(Status.INFO,"Verify the Patient Navigation Screen");
        Pages.DawakAppLandingPage().openActivePrescription();
        Pages.MobileCommon().backToDashboardArrowButton();
    }
    @Test(priority = 3)
    public void verifyCancelledPrescriptionNavigationScreen() throws InterruptedException {
        test.log(Status.INFO,"Verify the Patient Navigation Screen");
        Pages.DawakAppLandingPage().openCancelPrescription();
        Pages.MobileCommon().backToDashboardArrowButton();
    }
    @Test(priority = 4)
    public void verifyCompletedPrescriptionNavigationScreen() throws AWTException, InterruptedException {
        test.log(Status.INFO,"Verify the Patient Navigation Screen");
        Pages.DawakAppLandingPage().openCompletedPrescription();
        Pages.MobileCommon().backToDashboardArrowButton();
    }
    @Test(priority = 5)
    public void closeDawakApp(){
        Pages.MobileCommon().closeApp();
    }
}
