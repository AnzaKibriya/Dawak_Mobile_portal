package Pages;

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

public class DawakExternalPrescription {
    AndroidDriver androidDriver;
    By uploadPrescriptionBtn = AppiumBy.id(packageName + ":id/uploadPrescription_tv");
    By uploadPrescriptionLink = AppiumBy.id(packageName + ":id/uploadPrescription_iv");
    By insuranceCardFrontLink = AppiumBy.id(packageName + ":id/uploadFront_iv");
    By insuranceCardBackLink = AppiumBy.id(packageName + ":id/uploadBack_iv");
    By patientNameField = AppiumBy.id(packageName + ":id/patient_tv");
    By insuranceField = AppiumBy.id(packageName + ":id/insurance_tv");
    By patientNameRadioBtn = AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"" + packageName + ":id/rvPatient\"]/android.view.ViewGroup");
    By insuranceNameRadioBtn = AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"" + packageName + ":id/rvPatient\"]/android.view.ViewGroup[1]");
    By insuranceListConfirmBtn = AppiumBy.id(packageName + ":id/confirm_btn");
    By uploadBtn = AppiumBy.id(packageName + ":id/upload_btn");
    String successMessage = packageName + ":id/congrats_label_tv";
    String exitAction = packageName + ":id/close_btn";
    String pageScroll = packageName + ":id/root_view";
    By medicalFacilityDropDown = AppiumBy.id(packageName + ":id/title_text_view");
    By medicalFacilityOther = AppiumBy.xpath(
            "//android.widget.TextView[@resource-id=\"" + packageName + ":id/heading_text_view\" and @text=\"Others\"]");
    By confirmBtn = AppiumBy.id(packageName + ":id/confirm_btn");


    public DawakExternalPrescription(AndroidDriver AndroidDriver) {
        androidDriver = AndroidDriver;
    }

    public void navigateToUploadExternalPrescription() {
        mobileWait.until(ExpectedConditions.elementToBeClickable(uploadPrescriptionBtn)).click();
    }

    public void pushFileToDevice() throws IOException {
        String pdfPath = Path.of(System.getProperty("user.dir"), "/src/main/resources/dummy.pdf").toString();
        androidDriver.pushFile("/sdcard/download/test.pdf", new File(pdfPath));
    }

    public void switchContextToMobileNativeApp() {
        Set<String> contextNames = ((SupportsContextSwitching) androidDriver).getContextHandles();
        for (String strContextName : contextNames) {
            if (strContextName.contains("NATIVE_APP")) {
                androidDriver.context("NATIVE_APP");
                break;
            }
        }
    }

    public void selectTheFileToUpload() {
        //Click on files
        By eleFile = By.xpath("//*[@text='File']");
        mobileWait.until(ExpectedConditions.visibilityOfElementLocated(eleFile));
        androidDriver.findElement(eleFile).click();
        //select pdf file from downloads (location of pdf file)
        By eleDoc = By.id("com.google.android.documentsui:id/item_root");
        androidDriver.findElement(eleDoc).click();
    }

    public void openUploadPrescriptionPage() throws IOException, InterruptedException {
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
        pushFileToDevice();
        mobileWait.until(ExpectedConditions.elementToBeClickable(uploadPrescriptionLink)).click();
        switchContextToMobileNativeApp();
        selectTheFileToUpload();
        mobileWait.until(ExpectedConditions.elementToBeClickable(insuranceCardFrontLink)).click();
        switchContextToMobileNativeApp();
        selectTheFileToUpload();
        mobileWait.until(ExpectedConditions.elementToBeClickable(insuranceCardBackLink)).click();
        switchContextToMobileNativeApp();
        selectTheFileToUpload();
        WebElement scrollPage = androidDriver.findElement(By.id(String.format(pageScroll)));
        Pages.MobileCommon().scrollInMobile(scrollPage, "down", "100");
        Pages.MobileCommon().waitForElementsInteractions();
        mobileWait.until(ExpectedConditions.elementToBeClickable(uploadBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        WebElement verifySuccessMessage = androidDriver.findElement(By.id(String.format(successMessage)));
        String successMessage =verifySuccessMessage.getText();
        prescriptionOrderID = successMessage.split("#")[1].split(" ")[0];
        Assert.assertEquals(verifySuccessMessage.getText().contains("Uploaded Successfully"), true);
        androidDriver.findElement(By.id(String.valueOf(exitAction))).click();
    }
}
