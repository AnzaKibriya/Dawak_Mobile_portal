package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

;import static Helper.BaseClass.*;


public class DawakAppLogin {
    AndroidDriver androidDriver;

    By engLangBtn = AppiumBy.id(
            packageName + ":id/english_langauge_btn");
    By userName = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + packageName + ":id/username_et\")");
    By password = AppiumBy.id(packageName + ":id/password_et");
    By skipBtn = AppiumBy.id(packageName + ":id/skip_tv");
    By signInBtn = AppiumBy.id(packageName + ":id/login_btn");

    public DawakAppLogin(AndroidDriver AndroidDriver) {
        androidDriver = AndroidDriver;
    }

    public void handleSplashScreens() {
        mobileWait.until(ExpectedConditions.elementToBeClickable(engLangBtn)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(skipBtn)).click();
//        test.log(Status.PASS, "splash screen handled successfully");

    }

    public void loginToDawakApp() {
        mobileWait.until(ExpectedConditions.elementToBeClickable(userName)).sendKeys("500022333");//502201010
        mobileWait.until(ExpectedConditions.elementToBeClickable(password)).sendKeys("P@ssword123");//Akhil@2929
        mobileWait.until(ExpectedConditions.elementToBeClickable(signInBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
//        test.log(Status.PASS, "Login is successful");

    }
}
