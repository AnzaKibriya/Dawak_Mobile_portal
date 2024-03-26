package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static Helper.BaseClass.mobileWait;
import static Helper.BaseClass.prescriptionOrderID;

public class DawakAppPrescriptionPage {
    AndroidDriver androidDriver;

    By prescriptionNumber = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"ae.purehealth.dawak.qa:id/order_number_tv\")");
    By deliverMedicationBtn = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"ae.purehealth.dawak.qa:id/main_button\")");
    By confirmLocationBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/button3");
    By goToHomeBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/goto_home_btn");
    By proceedBtn = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"ae.purehealth.dawak.qa:id/main_button\")");
    By goToPharmacyBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/hollow_button");
    By cancelPrescriptionReason = AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"ae.purehealth.dawak.qa:id/reject_reasons_rv\"]/android.view.ViewGroup[2]");
    By submitBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/button3");
    By closeBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/cancel_action");

    public DawakAppPrescriptionPage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    public void verifyPrescriptionID() throws InterruptedException {
        Pages.MobileCommon().waitForElementsInteractions();
        String number = mobileWait.until(ExpectedConditions.visibilityOfElementLocated(prescriptionNumber)).getText();
        String[] arrOfStr = number.split("#");
        Assert.assertEquals(arrOfStr[1].replaceAll("\\s", ""), prescriptionOrderID);
    }

    public void deliverMedicine() {
        mobileWait.until(ExpectedConditions.elementToBeClickable(deliverMedicationBtn)).click();
        Pages.MobileCommon().waitForAddress();
        mobileWait.until(ExpectedConditions.elementToBeClickable(confirmLocationBtn)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(goToHomeBtn)).click();
    }

    public void clickOnProceedBtn() {
        mobileWait.until(ExpectedConditions.elementToBeClickable(proceedBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
    }

    public void clickOnGoToPharmacy() {
        mobileWait.until(ExpectedConditions.elementToBeClickable(goToPharmacyBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
    }

    public void cancelPrescription() {
        mobileWait.until(ExpectedConditions.elementToBeClickable(cancelPrescriptionReason)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        mobileWait.until(ExpectedConditions.elementToBeClickable(closeBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
    }
}