

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

public class DawakAddressAddition {

    AndroidDriver androidDriver;


    public DawakAddressAddition(AndroidDriver AndroidDriver) {
        androidDriver = AndroidDriver;
    }

    By profilebtn= AppiumBy.id("ae.purehealth.dawak.qa:id/profile_btn");

    By manageAddress=AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"ae.purehealth.dawak.qa:id/primary_option_rv\"]/android.widget.FrameLayout[1]/android.view.ViewGroup");

    By addaddressbtn=AppiumBy.id("ae.purehealth.dawak.qa:id/add_patient_btn");


    By search=AppiumBy.id("ae.purehealth.dawak.qa:id/search_view");


    By searchBar=AppiumBy.id("ae.purehealth.dawak.qa:id/places_autocomplete_search_bar");


    By address=AppiumBy.id("//android.widget.TextView[@resource-id=\"ae.purehealth.dawak.qa:id/places_autocomplete_prediction_secondary_text\" and @text=\"Abu Dhabi - United Arab Emirates\"]");

    By savebtn=AppiumBy.id("ae.purehealth.dawak.qa:id/save_btn");

    By savechanges=AppiumBy.id("ae.purehealth.dawak.qa:id/button");

    By getAddress=AppiumBy.id("ae.purehealth.dawak.qa:id/lyAddress1");

    By mamnageAddressBackButton=AppiumBy.id("ae.purehealth.dawak.qa:id/imageView7");

    By profileBackButton=AppiumBy.id("ae.purehealth.dawak.qa:id/imageView7");

    By savedAddress=AppiumBy.id("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"ae.purehealth.dawak.qa:id/rvAddress\"]/android.widget.FrameLayout/android.view.ViewGroup");

    public DawakAddressAddition() {

    }


    public void addAddress() throws AWTException, InterruptedException {
        mobileWait.until(ExpectedConditions.elementToBeClickable(profilebtn)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(manageAddress)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        mobileWait.until(ExpectedConditions.elementToBeClickable(addaddressbtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        mobileWait.until(ExpectedConditions.elementToBeClickable(search)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(searchBar)).sendKeys("SKMC");
        Pages.MobileCommon().waitForElementsInteractions();
        Robot robot =new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        mobileWait.until(ExpectedConditions.elementToBeClickable(savebtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        String addressBeforesaved= androidDriver.findElement((address)).getText();
        mobileWait.until(ExpectedConditions.elementToBeClickable(savechanges)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(mamnageAddressBackButton)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(profileBackButton)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(profilebtn)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(manageAddress)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        Assert.assertEquals(addressBeforesaved,androidDriver.findElement((savedAddress)).getText());
        mobileWait.until(ExpectedConditions.elementToBeClickable(profileBackButton)).click();

    }
}
