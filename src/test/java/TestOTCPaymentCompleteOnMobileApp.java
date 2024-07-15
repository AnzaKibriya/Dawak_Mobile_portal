import API_Calls.*;
import Helper.BaseClass;
import Pages.Pages;
import com.aventstack.extentreports.Status;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import static Helper.BaseClass.*;

public class TestOTCPaymentCompleteOnMobileApp extends BaseClass {
    String callCenterAccessToken, dpAccessToken;
    @BeforeClass
    public void createANewPrescription() throws MalformedURLException {
        test = extent.createTest("Testing Cash On Delivery Medicine Scenario");
        accessToken = LoginApiCall.makeLoginApiCall();
        prescriptionOrderID = generateRandomNumericString();
        System.out.println(prescriptionOrderID);
//        PrescriptionApiCall.makePrescriptionApiCall(accessToken, prescriptionOrderID);
        NewPatientApiCall.makeCreatePatientApiCall(accessToken, prescriptionOrderID);
    }

    @Test(priority = 1)
    public void navigateToPatientPage() {
        test.log(Status.INFO,"Navigation to Add Family Form");
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
    @Test(priority =4)
    public void verifyPrescription() throws InterruptedException {
        test.log(Status.INFO,"Open Prescription and Verify ID");
        Pages.DawakAppLandingPage().openActivePrescription();
        Pages.DawakAppPrescriptionPage().verifyPrescriptionID();
    }

    @Test(priority = 5)
    public void sendPrescriptionForDelivery() throws InterruptedException {
        test.log(Status.INFO,"Deliver Medicine Functionality");
        Pages.DawakAppPrescriptionPage().deliverMedicine();
    }

    @Test(priority = 6)
    public void addingProductFromCallCenter(){
        test.log(Status.INFO, "Adding a Product from Call Center");
        WebLoginApiCall.makeWebLoginApiCall("LoginCallCenter");
        WebCreateOtpApiCall.createOtpApiCall("CallCenterCreateOTP");
        callCenterAccessToken = WebPutOTPApiCall.OTPApiCall("CallCenterPutOTP");
        GetCallCenterTaskApiCall.getCallCenterTaskApiCall(callCenterAccessToken, prescriptionOrderID);
    }


}
