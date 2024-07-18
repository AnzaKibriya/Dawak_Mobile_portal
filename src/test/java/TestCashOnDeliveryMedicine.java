import API_Calls.*;
import Helper.BaseClass;
import Pages.Pages;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static Helper.AndroidDriverCapabilities.getAPKOptions;

public class TestCashOnDeliveryMedicine extends BaseClass {
    String cpAccessToken, dpAccessToken;

    @BeforeClass
    public void createANewPrescription() throws MalformedURLException {
        test = extent.createTest("Testing Cash On Delivery Medicine Scenario");
        accessToken = LoginApiCall.makeLoginApiCall();
        prescriptionOrderID = generateRandomNumericString();
        System.out.println(prescriptionOrderID);
        test.log(Status.INFO, "We are working on "+ prescriptionOrderID);
//        PrescriptionApiCall.makePrescriptionApiCall(accessToken, prescriptionOrderID);
        NewPatientApiCall.makeCreatePatientApiCall(accessToken, prescriptionOrderID);
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
        test.log(Status.INFO, "Verifying New Patient Details");
        Pages.DawakAppPatientModule().verifyPatientDetailsAndProceed();
        Pages.DawakAppPatientModule().navigateBackToDashboard();
    }

    @Test(priority = 4)
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
    public void webCentralPharma() {
        test.log(Status.INFO, "Sending For Insurance Approval from CP Portal through API");
        WebLoginApiCall.makeWebLoginApiCall("LoginCP");
        WebCreateOtpApiCall.createOtpApiCall("CPCreateOTP");
        cpAccessToken = WebPutOTPApiCall.OTPApiCall("CPPutOTP");
        GetCPTaskApiCall.getTaskApiCall(cpAccessToken, prescriptionOrderID);
        CPClaimTaskApiCall.getTaskClaimApiCall(cpAccessToken);
        SendForInsuranceApiCall.getSendForInsuranceApiCall(cpAccessToken);
        List<Integer> medicationRequestIDs = InProgressInsuranceTaskDetailsApiCall.makeInProgressInsuranceTaskDetailsApiCall(cpAccessToken);
        assert medicationRequestIDs != null;
        MedicationCoPayApiCall.getMedicationCoPayApiCall(cpAccessToken, medicationRequestIDs.get(1), "MetforminaAddCopay");
        MedicationCoPayApiCall.getMedicationCoPayApiCall(cpAccessToken, medicationRequestIDs.get(0), "MontelukastAddCopay");
        ConfirmInsuranceApiCall.getConfirmInsuranceApiCall(cpAccessToken);
    }

    @Test(priority = 7)
    public void paymentCashOnDelivery() throws InterruptedException {
        test.log(Status.INFO, "Cash on Delivery Payment Scenario");

        Pages.DawakAppLandingPage().openActivePrescription();
        Pages.DawakAppPrescriptionPage().clickOnProceedBtn();
        Pages.DawakAppPaymentModule().selectTimeSlotForDelivery();
        Pages.DawakAppPaymentModule().placeOrderSuccessfully();
    }

    @Test(priority = 8)
    public void webDispensingPortal() {
        test.log(Status.INFO, "Dispensing Medication from Dp Portal through API");
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

    @Test(priority = 9)
    public void removePatientFromApp() {
        test.log(Status.INFO,"Deleting the Newly Added Patient");
        Pages.DawakAppLandingPage().navigateToPatientPage();
        Pages.DawakAppPatientModule().deletePatient();
        Pages.MobileCommon().backToDashboardArrowButton();
    }
    @Test(priority = 10)
    public void closeDawakApp(){
        Pages.MobileCommon().closeApp();
    }

}
