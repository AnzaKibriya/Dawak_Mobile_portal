package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static Helper.BaseClass.mobileWait;

;


public class DawakAppLogin {
    AndroidDriver androidDriver;

    By engLangBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/english_langauge_btn");
    By userName = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"ae.purehealth.dawak.qa:id/username_et\")");
    By password = AppiumBy.id("ae.purehealth.dawak.qa:id/password_et");
    By skipBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/skip_tv");
    By signInBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/login_btn");

    public DawakAppLogin(AndroidDriver AndroidDriver) {
        androidDriver = AndroidDriver;
    }

    public void handleSplashScreens() {
        mobileWait.until(ExpectedConditions.elementToBeClickable(engLangBtn)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(skipBtn)).click();
    }

    public void loginToDawakApp() {
        mobileWait.until(ExpectedConditions.elementToBeClickable(userName)).sendKeys("855509929");//502201010
        mobileWait.until(ExpectedConditions.elementToBeClickable(password)).sendKeys("Pass_123");//Akhil@2929
        mobileWait.until(ExpectedConditions.elementToBeClickable(signInBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
    }
}
