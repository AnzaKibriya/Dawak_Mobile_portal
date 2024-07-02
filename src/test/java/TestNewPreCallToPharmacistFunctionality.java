import API_Calls.*;
import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class TestNewPreCallToPharmacistFunctionality extends BaseClass {
String mobileUserAccessToken;
    @BeforeClass
    public void createANewPrescription() {
        test = extent.createTest("Testing Call to pharmacist button functionality");
        accessToken = LoginApiCall.makeLoginApiCall();
        prescriptionOrderID = generateRandomNumericString();
        System.out.println(prescriptionOrderID);
//        PrescriptionApiCall.makePrescriptionApiCall(accessToken, prescriptionOrderID);
        NewPatientApiCall.makeCreatePatientApiCall(accessToken, prescriptionOrderID);
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
        test = extent.createTest("Open Prescription and Verify ID");
        Pages.DawakAppLandingPage().openActivePrescription();
        Pages.DawakAppPrescriptionPage().verifyPrescriptionID();
    }

    @Test(priority = 6)
    public void talktopharmacist() throws FileNotFoundException {
        test = extent.createTest("Click on talk to pharmacist and verify");
        Pages.DawakAppLandingPage().talkToPharmaicst();
    }
    @Test(priority = 7)
    public void removePatientFromApp() {
        test = extent.createTest("Deleting the Newly Added Patient");
        mobileUserAccessToken = MobileUserLoginApiCall.makeMobileLoginApiCall("LoginMobile");
        GetPatientApiCall.getPatientApiCall(mobileUserAccessToken);
        int i =GetPatientApiCall.getPatientID();
        System.out.println("Patient id is"+i);
        DeletePatientApiCall.deletePatientApiCall(mobileUserAccessToken);
    }
}
