package Pages;

import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static Helper.BaseClass.mobileWait;
import static Helper.BaseClass.test;

public class DawakAppLandingPage {
    AndroidDriver androidDriver;
    By activePrescriptionWidget = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"ae.purehealth.dawak.qa:id/card_v\").instance(1)");
    By patientBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/managePatient");
    By cancelPrescriptionWidget = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"ae.purehealth.dawak.qa:id/card_v\").instance(3)");
    public DawakAppLandingPage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }


    public void openActivePrescription() throws InterruptedException {
        Pages.MobileCommon().waitForAPIResponseToMirrorInAPP();
        mobileWait.until(ExpectedConditions.elementToBeClickable(activePrescriptionWidget)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        Pages.MobileCommon().waitForElementsInteractions();
        test.log(Status.PASS, "open active prescription successfully");

    }
    public void navigateToPatientPage() {
        mobileWait.until(ExpectedConditions.elementToBeClickable(patientBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        test.log(Status.PASS, "Navigated to patient page");

    }

    public void openCancelPrescription() throws InterruptedException {
        Pages.MobileCommon().waitForAPIResponseToMirrorInAPP();
        mobileWait.until(ExpectedConditions.elementToBeClickable(cancelPrescriptionWidget)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        Pages.MobileCommon().waitForElementsInteractions();
        test.log(Status.PASS, "cancel prescription opened successfully");

    }
}
