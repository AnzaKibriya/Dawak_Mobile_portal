package Helper;

import io.appium.java_client.android.options.UiAutomator2Options;

import java.nio.file.Path;
import java.time.Duration;

public class AndroidDriverCapabilities {
    public static UiAutomator2Options getAPKOptions() {
        String apkPath = Path.of(System.getProperty("user.dir"), "/src/main/resources/app-qa-debug.apk").toString();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android")
                .setAvd("Pixel_6_API_33")
                .setAutomationName("UiAutomator2")
                .setDeviceName("Pixel 6 API 33")
                .setAppPackage("ae.purehealth.dawak.qa")
                .setAppActivity("ae.purehealth.dawak.ui.splash.SplashActivity")
                .setApp(apkPath)
                .autoGrantPermissions()
                .setNewCommandTimeout(Duration.ofMinutes(25))
                .setNoReset(false);
        options.setCapability("appium:settings[ignoreUnimportantViews]", true);
        return options;
    }
}
