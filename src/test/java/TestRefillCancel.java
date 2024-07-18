import API_Calls.*;
import Helper.BaseClass;
import Pages.Pages;
import com.aventstack.extentreports.Status;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class TestRefillCancel extends BaseClass {
    String mobileUserAccessToken;
    @BeforeClass
    public void createANewPrescription() {
        test = extent.createTest("Refill - Testing Cancel Prescription");
        accessToken = LoginApiCall.makeLoginApiCall();
        prescriptionOrderID = generateRandomNumericString();
        System.out.println(prescriptionOrderID);
        test.log(Status.INFO, "We are working on "+ prescriptionOrderID);
//        PrescriptionApiCall.makePrescriptionApiCall(accessToken, prescriptionOrderID);
        RefillsApiCall.makeRefillsApiCall(accessToken, prescriptionOrderID);
    }


   @Test(priority = 1)
    public void navigateToPatientPage() throws InterruptedException {
        test.log(Status.INFO,"Navigation to Add Family Form");
        Pages.MobileCommon().launchApp();
        Pages.DawakAppLandingPage().navigateToPatientPage();
        Pages.DawakAppPatientModule().clickOnAddFamilyBtn();
    }

    @Test(priority = 2)
    public void addPatientToDawakApp() {
        test.log(Status.INFO,"Adding A New Patient");
        Pages.DawakAppPatientModule().addNewPatient();
       Pages.DawakAppPatientModule().verifyOTP();
    }

    @Test(priority = 3)
    public void verifyPatientDetails() throws FileNotFoundException {
        test.log(Status.INFO,"Verifying New Patient Details");
        Pages.DawakAppPatientModule().verifyPatientDetailsAndProceed();
        Pages.DawakAppPatientModule().navigateBackToDashboard();
    }

    @Test(priority = 4)
    public void verifyPrescription() throws InterruptedException {
        test.log(Status.INFO,"Verify prescription ID");
        Pages.DawakAppLandingPage().openActivePrescription();
        Pages.DawakAppPrescriptionPage().verifyPrescriptionID();
    }

    @Test(priority = 5)
    public void cancelPrescription() throws InterruptedException {
        test.log(Status.INFO,"Cancel the prescription");
        Pages.DawakAppPrescriptionPage().clickOnGoToPharmacy();
        Pages.DawakAppPrescriptionPage().setCancelPrescriptionReason();
    }

    @Test(priority = 6)
    public void verifyPrescriptionCancelled() throws InterruptedException {
        test.log(Status.INFO,"Verify that the prescription is cancelled");
        Pages.DawakAppLandingPage().openCancelPrescription();
        Pages.DawakAppPrescriptionPage().verifyPrescriptionID();

    }

    @Test(priority = 7)
    public void removePatientFromApp() {
        test.log(Status.INFO,"Deleting the Newly Added Patient");
        Pages.MobileCommon().navigateBack();
        Pages.DawakAppLandingPage().navigateToPatientPage();
        Pages.DawakAppPatientModule().deletePatient();
    }
    @Test(priority = 8)
    public void closeDawakApp(){
        Pages.MobileCommon().closeApp();
    }
}
