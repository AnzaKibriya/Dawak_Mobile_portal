package Pages;

import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.SupportsContextSwitching;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

import static Helper.BaseClass.mobileWait;
import static Helper.BaseClass.test;


public class DawakAppLandingPage {
    AndroidDriver androidDriver;
    By activePrescriptionWidget = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"ae.purehealth.dawak.qa:id/card_v\").instance(1)");
    By patientBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/managePatient");
    By cancelPrescriptionWidget = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"ae.purehealth.dawak.qa:id/card_v\").instance(3)");
    By uploadPrescriptionBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/uploadPrescription_tv");
    By uploadPrescriptionLink = AppiumBy.id("ae.purehealth.dawak.qa:id/uploadPrescription_iv");
    By selectBtnNative = AppiumBy.id("com.google.android.documentsui:id/action_menu_select");
    By pdfAttached = AppiumBy.id("ae.purehealth.dawak.qa:id/pdf_viewer");
    By patientNameField = AppiumBy.id("ae.purehealth.dawak.qa:id/patient_tv");
    By insuranceField = AppiumBy.id("ae.purehealth.dawak.qa:id/insurance_tv");
    By patientNameRadioBtn = AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"ae.purehealth.dawak.qa:id/rvPatient\"]/android.view.ViewGroup");
    By insuranceNameRadioBtn = AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"ae.purehealth.dawak.qa:id/rvPatient\"]/android.view.ViewGroup[1]");
    By insuranceListConfirmBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/confirm_btn");
    By uploadBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/upload_btn");
    String successMessage = "ae.purehealth.dawak.qa:id/congrats_label_tv";
    By exitAction = AppiumBy.id("ae.purehealth.dawak.qa:id/success_logo_v");
    String pageScroll = "ae.purehealth.dawak.qa:id/root_view";

    public DawakAppLandingPage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }


    public void openActivePrescription() throws InterruptedException {
        Pages.MobileCommon().waitForAPIResponseToMirrorInAPP();
        mobileWait.until(ExpectedConditions.elementToBeClickable(activePrescriptionWidget)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        Pages.MobileCommon().waitForElementsInteractions();
        test.log(Status.PASS, "Active prescription opened successfully");

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
        test.log(Status.PASS, "open cancel prescription successfully");
    }

    public void openUploadPrescriptionPage() throws IOException, InterruptedException {
        mobileWait.until(ExpectedConditions.elementToBeClickable(uploadPrescriptionBtn)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(patientNameField)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        mobileWait.until(ExpectedConditions.elementToBeClickable(patientNameRadioBtn)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(insuranceField)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        mobileWait.until(ExpectedConditions.elementToBeClickable(insuranceNameRadioBtn)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(insuranceListConfirmBtn)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(uploadPrescriptionLink)).click();
        Set<String> contextNames = ((SupportsContextSwitching)androidDriver).getContextHandles();
        for (String strContextName : contextNames) {
            if (strContextName.contains("NATIVE_APP")) {
                androidDriver.context("NATIVE_APP");
                break;
            }
        }
        String pdfPath = Path.of(System.getProperty("user.dir"), "/src/main/resources/dummy.pdf").toString();
        androidDriver.pushFile("/sdcard/download/test.pdf", new File(pdfPath));

        //Click on files
        By eleFile = By.xpath("//*[@text='File']");
        mobileWait.until(ExpectedConditions.visibilityOfElementLocated(eleFile));
        androidDriver.findElement(eleFile).click();

        //select pdf file from downloads (location of pdf file)
        By eleDoc = By.id("com.google.android.documentsui:id/item_root");
        androidDriver.findElement(eleDoc).click();
        androidDriver.findElement(selectBtnNative).click();
        WebElement scrollPage = androidDriver.findElement(By.id(String.format(pageScroll)));
        Pages.MobileCommon().scrollInMobile(scrollPage, "down", "100");
        Pages.MobileCommon().waitForElementsInteractions();
        mobileWait.until(ExpectedConditions.elementToBeClickable(uploadBtn)).click();
        WebElement verifySuccessMessage = androidDriver.findElement(By.id(String.format(successMessage)));
        Assert.assertEquals(verifySuccessMessage.getText().contains("Uploaded Successfully"), "Uploaded Successfully");
        mobileWait.until(ExpectedConditions.visibilityOfElementLocated(exitAction));

    }
}
