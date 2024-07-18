import API_Calls.*;
import Helper.BaseClass;
import Pages.Pages;
import com.aventstack.extentreports.Status;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.List;


public class TestRefillCancelOrderAfterInsuranceApproval extends BaseClass {
    String cpAccessToken;

    @BeforeClass
    public void createANewPrescription() {
        test = extent.createTest("Refill - Testing Cancel Order After Insurance Approval");
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
        test.log(Status.INFO,"Sending For Insurance Approval from CP Portal through API");
        WebLoginApiCall.makeWebLoginApiCall("LoginCP");
        WebCreateOtpApiCall.createOtpApiCall("CPCreateOTP");
        cpAccessToken = WebPutOTPApiCall.OTPApiCall("CPPutOTP");
        GetCPTaskApiCall.getTaskApiCall(cpAccessToken, prescriptionOrderID);
        CPClaimTaskApiCall.getTaskClaimApiCall(cpAccessToken);
        SendForInsuranceApiCall.getSendForInsuranceApiCall(cpAccessToken);
        List<Integer> medicationRequestIDs = InProgressInsuranceTaskDetailsApiCall.makeInProgressInsuranceTaskDetailsApiCall(cpAccessToken);
        assert medicationRequestIDs != null;
        MedicationCoPayApiCall.getMedicationCoPayApiCall(cpAccessToken, medicationRequestIDs.get(0), "MontelukastAddCopay");
        ConfirmInsuranceApiCall.getConfirmInsuranceApiCall(cpAccessToken);
    }

    @Test(priority = 7)
    public void cancelPrescription() throws InterruptedException {
        test.log(Status.INFO,"Cancel the prescription");
        Pages.DawakAppLandingPage().openActivePrescription();
        Pages.DawakAppPrescriptionPage().cancelOrder();
        Pages.DawakAppPrescriptionPage().setCancelPrescriptionReason();
    }

    @Test(priority = 9)
    public void verifyPrescriptionCancelled() throws InterruptedException {
        test.log(Status.INFO,"Verify that the prescription is cancelled");
        Pages.DawakAppLandingPage().openCancelPrescription();
        Pages.DawakAppPrescriptionPage().verifyPrescriptionID();

    }

    @Test(priority = 10)
    public void removePatientFromApp() {
        test.log(Status.INFO,"Deleting the Newly Added Patient");
        Pages.MobileCommon().navigateBack();
        Pages.DawakAppLandingPage().navigateToPatientPage();
        Pages.DawakAppPatientModule().deletePatient();
        Pages.MobileCommon().backToDashboardArrowButton();
    }
    @Test(priority = 11)
    public void closeDawakApp(){
        Pages.MobileCommon().closeApp();
    }
}
