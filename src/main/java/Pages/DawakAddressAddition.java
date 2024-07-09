

package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;

import static Helper.BaseClass.mobileWait;
import static Helper.BaseClass.packageName;

public class DawakAddressAddition {

    AndroidDriver androidDriver;


    public DawakAddressAddition(AndroidDriver AndroidDriver) {
        androidDriver = AndroidDriver;
    }

    By profileBtn= AppiumBy.id(packageName+":id/profile_btn");

    By manageAddress=AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\""+packageName+":id/primary_option_rv\"]/android.widget.FrameLayout[1]/android.view.ViewGroup");

    By addAddressBtn=AppiumBy.id(packageName+":id/add_patient_btn");


    By search=AppiumBy.id(packageName+":id/search_view");


    By searchBar=AppiumBy.id(packageName+":id/places_autocomplete_search_bar");


    By address=AppiumBy.id("//android.widget.TextView[@resource-id=\""+packageName+":id/places_autocomplete_prediction_secondary_text\" and @text=\"Abu Dhabi - United Arab Emirates\"]");

    By saveBtn=AppiumBy.id(packageName+":id/save_btn");

    By saveChanges=AppiumBy.id(packageName+":id/button");

    By getAddress=AppiumBy.id(packageName+":id/lyAddress1");

    By manageAddressBackButton=AppiumBy.id(packageName+":id/imageView7");

    By profileBackButton=AppiumBy.id(packageName+":id/imageView7");

    By savedAddress=AppiumBy.id("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"ae.purehealth.dawak.qa:id/rvAddress\"]/android.widget.FrameLayout/android.view.ViewGroup");

    public DawakAddressAddition() {

    }


    public void addAddress() throws AWTException, InterruptedException {
        mobileWait.until(ExpectedConditions.elementToBeClickable(profileBtn)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(manageAddress)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        mobileWait.until(ExpectedConditions.elementToBeClickable(addAddressBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        mobileWait.until(ExpectedConditions.elementToBeClickable(search)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(searchBar)).sendKeys("SKMC");
        Pages.MobileCommon().waitForElementsInteractions();
        Robot robot =new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        mobileWait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        String addressBeforesaved= androidDriver.findElement((address)).getText();
        mobileWait.until(ExpectedConditions.elementToBeClickable(saveChanges)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(manageAddressBackButton)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(profileBackButton)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(profileBtn)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(manageAddress)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        Assert.assertEquals(addressBeforesaved,androidDriver.findElement((savedAddress)).getText());
        mobileWait.until(ExpectedConditions.elementToBeClickable(profileBackButton)).click();

    }
}
