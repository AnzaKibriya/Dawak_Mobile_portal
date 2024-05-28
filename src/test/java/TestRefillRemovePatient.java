import API_Calls.LoginApiCall;
import API_Calls.RefillsApiCall;
import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class TestRefillRemovePatient extends BaseClass {
    @BeforeClass
    public void createANewPrescription() {
        test = extent.createTest("Refill-Testing Remove Patient from the Patient Details Screen");
        accessToken = LoginApiCall.makeLoginApiCall();
        prescriptionOrderID = generateRandomNumericString();
        System.out.println(prescriptionOrderID);
//        PrescriptionApiCall.makePrescriptionApiCall(accessToken, prescriptionOrderID);
         RefillsApiCall.makeRefillsApiCall(accessToken, prescriptionOrderID);
    }

    @Test(priority = 1)
    public void loginApp() {
        test = extent.createTest("Login to Dawak App");
        Pages.AndroidAppLogin().handleSplashScreens();
        Pages.AndroidAppLogin().loginToDawakApp();
    }

    @Test(priority = 2)
    public void navigateToPatientPage() {
        test = extent.createTest("Navigation to Add Family Form");
        Pages.DawakAppLandingPage().navigateToPatientPage();
        Pages.DawakAppPatientModule().clickOnAddFamilyBtn();
    }

    @Test(priority = 3)
    public void addPatientToDawakApp() {
        test = extent.createTest("Adding A New Patient");
        Pages.DawakAppPatientModule().addNewPatient();
        Pages.DawakAppPatientModule().verifyOTP();
    }

    @Test(priority = 4)
    public void verifyPatientDetails() throws FileNotFoundException {
        test = extent.createTest("Verifying New Patient Details");
        Pages.DawakAppPatientModule().verifyPatientDetailsAndProceed();
        Pages.DawakAppPatientModule().navigateBackToDashboard();
    }

    @Test(priority = 5)
    public void verifyPrescription() throws InterruptedException {
        test = extent.createTest("Verify prescription ID");
        Pages.DawakAppLandingPage().openActivePrescription();
        Pages.DawakAppPrescriptionPage().verifyPrescriptionID();
    }

    @Test(priority = 6)
   public void removePatientInDetailsScreen() {
        test = extent.createTest("Remove Patient");
        Pages.DawakPatientDetailsPage().clickOnRemovePatientButton();

   }
}
