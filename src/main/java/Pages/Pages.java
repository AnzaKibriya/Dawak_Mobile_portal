package Pages;

import Helper.BaseClass;
import org.openqa.selenium.support.PageFactory;

public class Pages {

    public static DawakAppLogin AndroidAppLogin() {
        DawakAppLogin pg = new DawakAppLogin(BaseClass.androidDriver);
        PageFactory.initElements(BaseClass.androidDriver, pg);
        return pg;
    }

    public static DawakAppLandingPage DawakAppLandingPage() {
        DawakAppLandingPage pg = new DawakAppLandingPage(BaseClass.androidDriver);
        PageFactory.initElements(BaseClass.androidDriver, pg);
        return pg;
    }

    public static MobileCommon MobileCommon() {
        MobileCommon pg = new MobileCommon(BaseClass.androidDriver);
        PageFactory.initElements(BaseClass.androidDriver, pg);
        return pg;
    }

    public static DawakAppPrescriptionPage DawakAppPrescriptionPage() {
        DawakAppPrescriptionPage pg = new DawakAppPrescriptionPage(BaseClass.androidDriver);
        PageFactory.initElements(BaseClass.androidDriver, pg);
        return pg;
    }

    public static DawakAppPatientModule DawakAppPatientModule() {
        DawakAppPatientModule pg = new DawakAppPatientModule(BaseClass.androidDriver);
        PageFactory.initElements(BaseClass.androidDriver, pg);
        return pg;
    }

    public static DawakAppPaymentModule DawakAppPaymentModule() {
        DawakAppPaymentModule pg = new DawakAppPaymentModule(BaseClass.androidDriver);
        PageFactory.initElements(BaseClass.androidDriver, pg);
        return pg;
    }

}
