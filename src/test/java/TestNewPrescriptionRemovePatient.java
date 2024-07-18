import API_Calls.LoginApiCall;
import API_Calls.NewPatientApiCall;
import API_Calls.RefillsApiCall;
import Helper.BaseClass;
import Pages.Pages;
import com.aventstack.extentreports.Status;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class TestNewPrescriptionRemovePatient extends BaseClass {
    @BeforeClass
    public void createANewPrescription() {
        test = extent.createTest("New Prescription -Testing Remove Patient from the Patient Details Screen");
        accessToken = LoginApiCall.makeLoginApiCall();
        prescriptionOrderID = generateRandomNumericString();
        System.out.println(prescriptionOrderID);
        test.log(Status.INFO, "We are working on "+ prescriptionOrderID);
//        PrescriptionApiCall.makePrescriptionApiCall(accessToken, prescriptionOrderID);
        NewPatientApiCall.makeCreatePatientApiCall(accessToken, prescriptionOrderID);
    }

    @Test(priority = 1)
    public void navigateToPatientPage() throws InterruptedException {
        test.log (Status.INFO,"Navigation to Add Family Form");
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
        test.log(Status.INFO, "Verifying New Patient Details");
        Pages.DawakAppPatientModule().verifyPatientDetailsAndProceed();
        Pages.DawakAppPatientModule().navigateBackToDashboard();
    }

    @Test(priority = 4)
    public void verifyPrescription() throws InterruptedException {
        test.log(Status.INFO, "Verify prescription ID");
        Pages.DawakAppLandingPage().openActivePrescription();
        Pages.DawakAppPrescriptionPage().verifyPrescriptionID();
    }

    @Test(priority = 5)
   public void removePatientInDetailsScreen() {
        test.log(Status.INFO,"Remove Patient");
        Pages.DawakPatientDetailsPage().clickOnRemovePatientButton();
        Pages.MobileCommon().backToDashboardArrowButton();
   }
    @Test(priority = 6)
    public void closeDawakApp(){
        Pages.MobileCommon().closeApp();
    }
}
