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

import static Helper.BaseClass.*;

public class DawakAppLandingPage {
    AndroidDriver androidDriver;
    By activePrescriptionWidget = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + packageName + ":id/card_v\").instance(1)");
    By patientBtn = AppiumBy.id(packageName + ":id/managePatient");
    By cancelPrescriptionWidget = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + packageName + ":id/card_v\").instance(3)");
    By uploadPrescriptionBtn = AppiumBy.id(packageName + ":id/uploadPrescription_tv");
    By uploadPrescriptionLink = AppiumBy.id(packageName + ":id/uploadPrescription_iv");
    By insuranceCardFrontLink = AppiumBy.id(packageName + ":id/uploadFront_iv");
    By insuranceCardBackLink = AppiumBy.id(packageName + ":id/uploadBack_iv");
    By selectBtnNative = AppiumBy.id("com.google.android.documentsui:id/action_menu_select");
    By pdfAttached = AppiumBy.id(packageName + ":id/pdf_viewer");
    By patientNameField = AppiumBy.id(packageName + ":id/patient_tv");
    By insuranceField = AppiumBy.id(packageName + ":id/insurance_tv");
    By patientNameRadioBtn = AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"" + packageName + ":id/rvPatient\"]/android.view.ViewGroup");
    By insuranceNameRadioBtn = AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"" + packageName + ":id/rvPatient\"]/android.view.ViewGroup[1]");
    By insuranceListConfirmBtn = AppiumBy.id(packageName + ":id/confirm_btn");
    By uploadBtn = AppiumBy.id(packageName + ":id/upload_btn");
    String successMessage = packageName + ":id/congrats_label_tv";
    String exitAction = packageName + ":id/touch_outside";
    String pageScroll = packageName + ":id/root_view";
    By manageFamilyText = AppiumBy.id(packageName + ":id/textView7");
    By completePrescriptionWidget = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + packageName + ":id/card_v\").instance(2)");
    By totalPatientsWidget = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + packageName + ":id/card_v\").instance(0)");
    By talkToPharmacistButton = AppiumBy.id(packageName + ":id/secondary_button");
    By phoneNumberFromDial = AppiumBy.id("com.google.android.dialer:id/digits");
    By medicalFacilityDropDown = AppiumBy.id(packageName + ":id/title_text_view");
    By medicalFacilityOther = AppiumBy.xpath(
            "//android.widget.TextView[@resource-id=\"" + packageName + ":id/heading_text_view\" and @text=\"Others\"]");
    By confirmBtn = AppiumBy.id(packageName + ":id/confirm_btn");




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

    public void totalPatientswidget() {
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

    public void openUploadPrescriptionPage() throws IOException, InterruptedException {
        mobileWait.until(ExpectedConditions.elementToBeClickable(uploadPrescriptionBtn)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(medicalFacilityDropDown)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(medicalFacilityOther)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(confirmBtn)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(patientNameField)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        mobileWait.until(ExpectedConditions.elementToBeClickable(patientNameRadioBtn)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(insuranceField)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        mobileWait.until(ExpectedConditions.elementToBeClickable(insuranceNameRadioBtn)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(insuranceListConfirmBtn)).click();
        String pdfPath = Path.of(System.getProperty("user.dir"), "/src/main/resources/dummy.pdf").toString();
        androidDriver.pushFile("/sdcard/download/test.pdf", new File(pdfPath));
        mobileWait.until(ExpectedConditions.elementToBeClickable(uploadPrescriptionLink)).click();
        Set<String> contextNames = ((SupportsContextSwitching) androidDriver).getContextHandles();
        for (String strContextName : contextNames) {
            if (strContextName.contains("NATIVE_APP")) {
                androidDriver.context("NATIVE_APP");
                break;
            }
        }

        //Click on files
        By eleFile = By.xpath("//*[@text='File']");
        mobileWait.until(ExpectedConditions.visibilityOfElementLocated(eleFile));
        androidDriver.findElement(eleFile).click();

        //select pdf file from downloads (location of pdf file)
        By eleDoc = By.id("com.google.android.documentsui:id/item_root");
        androidDriver.findElement(eleDoc).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(insuranceCardFrontLink)).click();
        Set<String> contextNamesCardFront = ((SupportsContextSwitching) androidDriver).getContextHandles();
        for (String strContextName : contextNamesCardFront) {
            if (strContextName.contains("NATIVE_APP")) {
                androidDriver.context("NATIVE_APP");
                break;
            }
        }
        androidDriver.findElement(eleFile).click();

        //select pdf file from downloads (location of pdf file)

        androidDriver.findElement(eleDoc).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(insuranceCardBackLink)).click();
        Set<String> contextNamesCardBack = ((SupportsContextSwitching) androidDriver).getContextHandles();
        for (String strContextName : contextNamesCardBack) {
            if (strContextName.contains("NATIVE_APP")) {
                androidDriver.context("NATIVE_APP");
                break;
            }
        }
        androidDriver.findElement(eleFile).click();

        //select pdf file from downloads (location of pdf file)

        androidDriver.findElement(eleDoc).click();

//        androidDriver.findElement(selectBtnNative).click();
        WebElement scrollPage = androidDriver.findElement(By.id(String.format(pageScroll)));
        Pages.MobileCommon().scrollInMobile(scrollPage, "down", "100");
        Pages.MobileCommon().waitForElementsInteractions();
        mobileWait.until(ExpectedConditions.elementToBeClickable(uploadBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        WebElement verifySuccessMessage = androidDriver.findElement(By.id(String.format(successMessage)));
        Assert.assertEquals(verifySuccessMessage.getText().contains("Uploaded Successfully"), true);
        androidDriver.findElement(By.id(String.valueOf(exitAction))).click();
    }
    public void talkToPharmaicst() {
        androidDriver.findElement(By.id(packageName+":id/secondary_button")).click();
        String expectedPharmacistNumber = "028150450";
        Assert.assertEquals(mobileWait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberFromDial)).getText(), expectedPharmacistNumber);
        test.log(Status.PASS, "Successfully navigated back to dashboard landing page" + expectedPharmacistNumber);
    }
}
