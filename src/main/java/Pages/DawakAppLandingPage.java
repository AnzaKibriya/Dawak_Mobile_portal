package Pages;

import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


import static Helper.BaseClass.*;

public class DawakAppLandingPage {
    AndroidDriver androidDriver;
    By activePrescriptionWidget = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + packageName + ":id/card_v\").instance(1)");
    By patientBtn = AppiumBy.id(packageName + ":id/managePatient");
    By cancelPrescriptionWidget = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + packageName + ":id/card_v\").instance(3)");

    By manageFamilyText = AppiumBy.id(packageName + ":id/textView7");
    By completePrescriptionWidget = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + packageName + ":id/card_v\").instance(2)");
    By totalPatientsWidget = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + packageName + ":id/card_v\").instance(0)");
    By talkToPharmacistButton = AppiumBy.id(packageName + ":id/secondary_button");
    By phoneNumberFromDial = AppiumBy.id("com.google.android.dialer:id/digits");


    public DawakAppLandingPage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }


    public void openActivePrescription() throws InterruptedException {
        Pages.MobileCommon().waitForAPIResponseToMirrorInAPP();
        mobileWait.until(ExpectedConditions.elementToBeClickable(activePrescriptionWidget)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        Pages.MobileCommon().waitForElementsInteractions();
        Assert.assertEquals(mobileWait.until(ExpectedConditions.visibilityOfElementLocated(manageFamilyText)).getText(), "Active Prescriptions");
        test.log(Status.PASS, "Active prescription opened successfully");

    }

    public void totalPatientsWidget() {
        mobileWait.until(ExpectedConditions.elementToBeClickable(totalPatientsWidget)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        test.log(Status.PASS, "Successfully navigated to the total patients widget");
        Assert.assertEquals(mobileWait.until(ExpectedConditions.visibilityOfElementLocated(manageFamilyText)).getText(), "Manage Family");
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
        Assert.assertEquals(mobileWait.until(ExpectedConditions.visibilityOfElementLocated(manageFamilyText)).getText(), "Cancelled Prescriptions");
        test.log(Status.PASS, "Successfully navigated to the  cancelled prescription widget");
    }

    public void openCompletedPrescription() throws InterruptedException {
        Pages.MobileCommon().waitForAPIResponseToMirrorInAPP();
        mobileWait.until(ExpectedConditions.elementToBeClickable(completePrescriptionWidget)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        Pages.MobileCommon().waitForElementsInteractions();
        Assert.assertEquals(mobileWait.until(ExpectedConditions.visibilityOfElementLocated(manageFamilyText)).getText(), "Completed Prescriptions");
        test.log(Status.PASS, "Successfully navigated to the completed prescription widget");
    }

    public void talkToPharmacist() {
        androidDriver.findElement(By.id(packageName + ":id/secondary_button")).click();
        String expectedPharmacistNumber = "028150450";
        Assert.assertEquals(mobileWait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberFromDial)).getText(), expectedPharmacistNumber);
        test.log(Status.PASS, "Successfully navigated back to dashboard landing page" + expectedPharmacistNumber);
    }
}
