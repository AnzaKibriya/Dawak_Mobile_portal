import API_Calls.LoginApiCall;
import API_Calls.NewPatientApiCall;
import Helper.BaseClass;
import Pages.Pages;
import com.aventstack.extentreports.Status;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class TestCancelPrescription extends BaseClass {
    @BeforeClass
    public void createANewPrescription() {
        test = extent.createTest("Testing Cancel Prescription");
        accessToken = LoginApiCall.makeLoginApiCall();
        prescriptionOrderID = generateRandomNumericString();
        System.out.println(prescriptionOrderID);
//        PrescriptionApiCall.makePrescriptionApiCall(accessToken, prescriptionOrderID);
        NewPatientApiCall.makeCreatePatientApiCall(accessToken, prescriptionOrderID);
    }

//    @Test(priority = 1)
//    public void loginApp() {
//        test = extent.createTest("Login to Dawak App");
//        Pages.AndroidAppLogin().handleSplashScreens();
//        Pages.AndroidAppLogin().loginToDawakApp();
//    }

    @Test(priority = 2)
    public void navigateToPatientPage() {
        test.log(Status.INFO, "Navigation to Add Family Form");
        Pages.DawakAppLandingPage().navigateToPatientPage();
        Pages.DawakAppPatientModule().clickOnAddFamilyBtn();
    }

    @Test(priority = 3)
    public void addPatientToDawakApp() {
        test.log(Status.INFO, "Adding A New Patient");
        Pages.DawakAppPatientModule().addNewPatient();
        Pages.DawakAppPatientModule().verifyOTP();
    }

    @Test(priority = 4)
    public void verifyPatientDetails() throws FileNotFoundException {
        test.log(Status.INFO,"Verifying New Patient Details");
        Pages.DawakAppPatientModule().verifyPatientDetailsAndProceed();
        Pages.DawakAppPatientModule().navigateBackToDashboard();
    }

    @Test(priority = 5)
    public void verifyPrescription() throws InterruptedException {
        test.log(Status.INFO,"Verify prescription ID");
        Pages.DawakAppLandingPage().openActivePrescription();
        Pages.DawakAppPrescriptionPage().verifyPrescriptionID();
    }

    @Test(priority = 6)
    public void cancelPrescription() throws InterruptedException {
        test.log(Status.INFO,"Cancel the prescription");
        Pages.DawakAppPrescriptionPage().clickOnGoToPharmacy();
        Pages.DawakAppPrescriptionPage().setCancelPrescriptionReason();
    }

    @Test(priority = 7)
    public void verifyPrescriptionCancelled() throws InterruptedException {
        test.log(Status.INFO,"Verify that the prescription is cancelled");
        Pages.DawakAppLandingPage().openCancelPrescription();
        Pages.DawakAppPrescriptionPage().verifyPrescriptionID();

    }

    @Test(priority = 8)
    public void removePatientFromApp() {
        test.log(Status.INFO,"Deleting the Newly Added Patient");
        Pages.MobileCommon().navigateBack();
        Pages.DawakAppLandingPage().navigateToPatientPage();
        Pages.DawakAppPatientModule().deletePatient();
    }
}
