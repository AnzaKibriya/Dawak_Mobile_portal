import Helper.BaseClass;
import Pages.Pages;
import API_Calls.LoginApiCall;
import API_Calls.PrescriptionApiCall;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestPrescriptionDelivery extends BaseClass {
//   @BeforeClass
//    public void createANewPrescription(){
//        accessToken = LoginApiCall.makeLoginApiCall();
//        prescriptionOrderID = generateRandomNumericString();
//        System.out.println(prescriptionOrderID);
//        PrescriptionApiCall.makePrescriptionApiCall(accessToken, prescriptionOrderID);
//    }
    @Test(priority = 1)
    public void loginApp() {
        test = extent.createTest("Login to Dawak App");
        Pages.AndroidAppLogin().handleSplashScreens();
        Pages.AndroidAppLogin().loginToDawakApp();
    }

    @Test(priority = 2)
    public void verifyPrescription() throws InterruptedException {
        test = extent.createTest("Open Prescription and Verify ID");
        Pages.DawakAppLandingPage().openActivePrescription();
//        Pages.DawakAppPrescriptionPage().verifyPrescriptionID();
    }

    @Test(priority = 3)
    public void sendPrescriptionForDelivery() throws InterruptedException {
        test = extent.createTest("Deliver Medicine Functionality");
        Pages.DawakAppPrescriptionPage().deliverMedicine();
    }
}
