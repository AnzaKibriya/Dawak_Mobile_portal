package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;


import static Helper.BaseClass.mobileWait;
import static Helper.BaseClass.packageName;

public class DawakPatientDetailsPage{

    DawakAddressAddition dp = new DawakAddressAddition();
    AndroidDriver androidDriver;

    By removePatientButton = AppiumBy.id(packageName+":id/remove_btn");
    By patientButton = AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\""+packageName+":id/patient_list_rv\"]/android.view.ViewGroup");

    public DawakPatientDetailsPage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }


    public void clickOnRemovePatientButton(){
        Pages.MobileCommon().backToDashboardArrowButton();
        Pages.DawakAppLandingPage().navigateToPatientPage();
        mobileWait.until(ExpectedConditions.elementToBeClickable(patientButton)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(removePatientButton)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
    }
}
