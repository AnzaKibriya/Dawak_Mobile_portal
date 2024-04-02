import API_Calls.*;
import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class TestAddPatientInDawakApp extends BaseClass {
    String cpAccessToken, dpAccessToken;
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
    public void verifyPatientDetails() throws  FileNotFoundException {
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
    public void webCentralPharma() {
        WebLoginApiCall.makeWebLoginApiCall("LoginCP");
        WebCreateOtpApiCall.createOtpApiCall("CPCreateOTP");
        cpAccessToken = WebPutOTPApiCall.OTPApiCall("CPPutOTP");
        GetCPTaskApiCall.getTaskApiCall(cpAccessToken,prescriptionOrderID);
        CPClaimTaskApiCall.getTaskClaimApiCall(cpAccessToken);
        SendForInsuranceApiCall.getSendForInsuranceApiCall(cpAccessToken);
        List<Integer> medicationRequestIDs = InProgressInsuranceTaskDetailsApiCall.makeInProgressInsuranceTaskDetailsApiCall(cpAccessToken);
        assert medicationRequestIDs != null;
        MedicationCoPayApiCall.getMedicationCoPayApiCall(cpAccessToken,medicationRequestIDs.get(1), "MetforminaAddCopay");
        MedicationCoPayApiCall.getMedicationCoPayApiCall(cpAccessToken, medicationRequestIDs.get(0), "MetforminaAddCopay");
        ConfirmInsuranceApiCall.getConfirmInsuranceApiCall(cpAccessToken);
    }
    @Test(priority = 8)
    public void paymentCashOnDelivery() throws InterruptedException {
        test = extent.createTest("Payment Functionality");
        Pages.DawakAppLandingPage().openActivePrescription();
        Pages.DawakAppPrescriptionPage().clickOnProceedBtn();
        Pages.DawakAppPaymentModule().selectTimeSlotForDelivery();
        Pages.DawakAppPaymentModule().placeOrderSuccessfully();
    }

    @Test(priority = 9)
   public void webDispensingPortal(){
        WebLoginApiCall.makeWebLoginApiCall("LoginDP");
        WebCreateOtpApiCall.createOtpApiCall("DPCreateOTP");
        dpAccessToken = WebPutOTPApiCall.OTPApiCall("DPPutOTP");
        GetDPTaskApiCall.getTaskApiCall(dpAccessToken, prescriptionOrderID);
        DPClaimTaskApiCall.getTaskClaimApiCall(dpAccessToken);
        DispensingStartedApiCall.getDispensingStartedApiCall(dpAccessToken);
        ReadyForDeliveryApiCall.getReadyForDeliveryApiCall(dpAccessToken);
        String shipaOrderNum = GetShipaIdApiCall.makeShipaIdApiCall(dpAccessToken);
        ShipaEventApiCall.makeShipaEventApiCall(dpAccessToken, shipaOrderNum, "initiated");
        ShipaEventApiCall.makeShipaEventApiCall(dpAccessToken, shipaOrderNum, "Completed");

    }
}
