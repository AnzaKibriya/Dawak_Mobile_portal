package Pages;

import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

import static Helper.BaseClass.*;

public class MobileCommon {
    AndroidDriver androidDriver;
    By dawakAppLoader = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + packageName + ":id/success_logo_v\")");
    By addressCheckBox = AppiumBy.className("android.view.ViewGroup");
    By homeBtn = AppiumBy.id(packageName + ":id/home_btn");
    By navigateBackBtn = AppiumBy.id(packageName+":id/imageView7");
    By manageAddressBackButton=AppiumBy.id(packageName+":id/imageView7");
    static public JsonObject patient;
    static public JsonObject order;

    public MobileCommon(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    public void waitForLoaderInvisibility() {
        List<WebElement> loaderElement = androidDriver.findElements(dawakAppLoader);
        if (!loaderElement.isEmpty()) {
            WebElement topLoaderElement = loaderElement.get(0);
            webWait.until(ExpectedConditions.invisibilityOf(topLoaderElement));
        }
    }

    public void waitForAddress() {
        mobileWait.until(ExpectedConditions.visibilityOfElementLocated(addressCheckBox));
        mobileWait.until(ExpectedConditions.elementToBeClickable(addressCheckBox));
    }

    public void waitForAPIResponseToMirrorInAPP() throws InterruptedException {
        Thread.sleep(7000);
    }

    public void waitForElementsInteractions() throws InterruptedException {
        Thread.sleep(5000);
    }

    public void scrollInMobile(WebElement webElement, String direction, String percentage) {
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", direction);
        scrollObject.put("element", ((RemoteWebElement) webElement).getId());
        scrollObject.put("percent", percentage);
        mobileJavascriptExecutor().executeScript("mobile: scrollGesture", scrollObject);
    }

    public void loadJson(String path) throws FileNotFoundException {
        JsonParser jsonParser = new JsonParser();
        FileReader reader;
        reader = new FileReader(path);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
        // Extract patient information
        patient = jsonObject.getAsJsonObject("patient");
        order = jsonObject.getAsJsonObject("order");
    }

    public void navigateToHomePage() {
        mobileWait.until(ExpectedConditions.elementToBeClickable(homeBtn)).click();
    }

    public void navigateBack() {
        mobileWait.until(ExpectedConditions.elementToBeClickable(navigateBackBtn)).click();
        test.log(Status.PASS, "Navigated  Back successfully");

    }
    public void backToDashboardArrowButton() {
        mobileWait.until(ExpectedConditions.elementToBeClickable(manageAddressBackButton)).click();
        test.log(Status.PASS, "Successfully navigated back to dashboard landing page");
    }


}
