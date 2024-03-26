import Helper.BaseClass;
import Pages.Pages;
import model.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestAddPatientInDawakApp extends BaseClass {
    String cpAccessToken;
    @BeforeClass
    public void createANewPrescription() {
        accessToken = LoginApiCall.makeLoginApiCall();
        prescriptionOrderID = generateRandomNumericString();
        System.out.println(prescriptionOrderID);
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
        Pages.DawakAppLandingPage().navigateToPatientPage();
        Pages.DawakAppPatientModule().clickOnAddFamilyBtn();
    }

    @Test(priority = 3)
    public void addPatientToDawakApp() {
        Pages.DawakAppPatientModule().addNewPatient();
        Pages.DawakAppPatientModule().verifyOTP();
    }

    @Test(priority = 4)
    public void verifyPatientDetails() throws FileNotFoundException {
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
    public void sendPrescriptionForDelivery() {
        test = extent.createTest("Deliver Medicine Functionality");
        Pages.DawakAppPrescriptionPage().deliverMedicine();
    }

    @Test(priority = 7)
    public void webPart() {
        LoginCpApiCall.makeCpLoginApiCall();
        CPCreateOtpApiCall.createOtpApiCall();
        cpAccessToken = CPPutOTPApiCall.OTPApiCall();
        GetTaskApiCall.getTaskApiCall(cpAccessToken,prescriptionOrderID);
        CPClaimTaskApiCall.getTaskClaimApiCall(cpAccessToken);
        SendForInsuranceApiCall.getSendForInsuranceApiCall(cpAccessToken);
    }

}
