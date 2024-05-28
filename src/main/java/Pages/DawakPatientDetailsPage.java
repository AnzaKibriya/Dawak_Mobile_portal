package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;


import static Helper.BaseClass.mobileWait;

public class DawakPatientDetailsPage{

    DawakAddressAddition dp = new DawakAddressAddition();
    AndroidDriver androidDriver;

    By removePatientButton = AppiumBy.id("ae.purehealth.dawak.qa:id/remove_btn");
    By patientButton = AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"ae.purehealth.dawak.qa:id/patient_list_rv\"]/android.view.ViewGroup");

    public DawakPatientDetailsPage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }


    public void clickOnRemovePatientButton(){
        mobileWait.until(ExpectedConditions.elementToBeClickable(dp.mamnageAddressBackButton)).click();
        Pages.DawakAppLandingPage().navigateToPatientPage();
        mobileWait.until(ExpectedConditions.elementToBeClickable(patientButton)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(removePatientButton)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
    }
}
