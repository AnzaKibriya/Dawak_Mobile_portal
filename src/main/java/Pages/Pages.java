package Pages;

import Helper.BaseClass;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.PageFactory;

public class Pages {

    public static DawakAppLogin AndroidAppLogin() {
        DawakAppLogin pg = new DawakAppLogin((AndroidDriver) BaseClass.androidDriver);
        PageFactory.initElements(BaseClass.androidDriver, pg);
        return pg;
    }

    public static DawakAppLandingPage DawakAppLandingPage() {
        DawakAppLandingPage pg = new DawakAppLandingPage((AndroidDriver) BaseClass.androidDriver);
        PageFactory.initElements(BaseClass.androidDriver, pg);
        return pg;
    }

    public static MobileCommon MobileCommon() {
        MobileCommon pg = new MobileCommon((AndroidDriver) BaseClass.androidDriver);
        PageFactory.initElements(BaseClass.androidDriver, pg);
        return pg;
    }

    public static DawakAppPrescriptionPage DawakAppPrescriptionPage() {
        DawakAppPrescriptionPage pg = new DawakAppPrescriptionPage((AndroidDriver) BaseClass.androidDriver);
        PageFactory.initElements(BaseClass.androidDriver, pg);
        return pg;
    }

    public static DawakAppPatientModule DawakAppPatientModule() {
        DawakAppPatientModule pg = new DawakAppPatientModule((AndroidDriver) BaseClass.androidDriver);
        PageFactory.initElements(BaseClass.androidDriver, pg);
        return pg;
    }

    public static DawakAppPaymentModule DawakAppPaymentModule() {
        DawakAppPaymentModule pg = new DawakAppPaymentModule((AndroidDriver) BaseClass.androidDriver);
        PageFactory.initElements(BaseClass.androidDriver, pg);
        return pg;
    }


    public static DawakAddressAddition DawakAddressAddition() {
        DawakAddressAddition pg = new DawakAddressAddition((AndroidDriver) BaseClass.androidDriver);
        PageFactory.initElements(BaseClass.androidDriver, pg);
        return pg;
    }

}
