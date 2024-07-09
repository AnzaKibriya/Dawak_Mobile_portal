package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static Helper.BaseClass.mobileWait;
import static Helper.BaseClass.packageName;

public class DawakAppPaymentModule {
    AndroidDriver androidDriver;
    By timeSlotDropDown = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + packageName + ":id/choose_time_slot_v\")");
    By timeSlotCheckBox = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"10:00 PM - 10:30 PM\")");
    By confirmTimeSlotBtn = AppiumBy.id(packageName + ":id/button3");
    By goToHomeAfterPayment = AppiumBy.xpath("//android.widget.Button[@text='GO TO HOME']");
    By placeOrderBtn = AppiumBy.id(packageName + ":id/place_order_btn");
    String dateSlotScroll = packageName + ":id/rvDatesSlots";
    String pageScroll = packageName + ":id/root_view";
    By paymentByCardBtn = AppiumBy.id(packageName + ":id/mode_of_payment_card_v");
    By cardHolderName = AppiumBy.id(packageName + ":id/bt_card_form_cardholder_name");
    By cardNumber = AppiumBy.id(packageName + ":id/bt_card_form_card_number");
    By cardExpire = AppiumBy.id(packageName + ":id/bt_card_form_expiration");
    By cardCvv = AppiumBy.id(packageName + ":id/bt_card_form_cvv");
    By payNowBtn = AppiumBy.id(packageName + ":id/pt2_pay_button_id");


    public DawakAppPaymentModule(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    public void selectTimeSlotForDelivery() {
        mobileWait.until(ExpectedConditions.elementToBeClickable(timeSlotDropDown)).click();
        WebElement timeScroll = androidDriver.findElement(By.id(String.format(dateSlotScroll)));
        Pages.MobileCommon().scrollInMobile(timeScroll, "down", "80");
        mobileWait.until(ExpectedConditions.visibilityOfElementLocated(timeSlotCheckBox));
        mobileWait.until(ExpectedConditions.elementToBeClickable(timeSlotCheckBox)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(confirmTimeSlotBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
    }

    public void placeOrderSuccessfully() throws InterruptedException {
        WebElement scrollPage = androidDriver.findElement(By.id(String.format(pageScroll)));
        Pages.MobileCommon().scrollInMobile(scrollPage, "down", "80");
        Pages.MobileCommon().waitForElementsInteractions();
        mobileWait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
        Pages.MobileCommon().waitForElementsInteractions();
        mobileWait.until(ExpectedConditions.visibilityOfElementLocated(goToHomeAfterPayment));
        mobileWait.until(ExpectedConditions.elementToBeClickable(goToHomeAfterPayment)).click();
    }

    public void paymentByCard() throws InterruptedException {
        mobileWait.until(ExpectedConditions.elementToBeClickable(paymentByCardBtn)).click();
        WebElement scrollPage = androidDriver.findElement(By.id(String.format(pageScroll)));
        Pages.MobileCommon().scrollInMobile(scrollPage, "down", "80");
        Pages.MobileCommon().waitForElementsInteractions();
        mobileWait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        mobileWait.until(ExpectedConditions.elementToBeClickable(cardHolderName)).sendKeys("Test Card");
        mobileWait.until(ExpectedConditions.elementToBeClickable(cardNumber)).sendKeys("4000000000000002");
        mobileWait.until(ExpectedConditions.elementToBeClickable(cardExpire)).sendKeys("1225");
        mobileWait.until(ExpectedConditions.elementToBeClickable(cardCvv)).sendKeys("123");
        mobileWait.until(ExpectedConditions.elementToBeClickable(payNowBtn)).click();
    }
}
