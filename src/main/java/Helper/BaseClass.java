package Helper;

import Pages.Pages;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import okhttp3.OkHttpClient;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Random;

import static Helper.AndroidDriverCapabilities.getAPKOptions;

public class BaseClass {
    public static ExtentTest test;
    public static ExtentReports extent;
    public static String storestring;
    public static Properties prop;
    public static WebDriverWait mobileWait;
    public static WebDriverWait webWait;
    public static String prescriptionOrderID;
    public static String accessToken;
    public static OkHttpClient client;
    public static SoftAssert softAssert;
    public static AndroidDriver androidDriver;
    public static String emiratesID;
    public static String formattedDate;
    static FileInputStream fis ;

    public static String propertyFile(String PropFileName, String stringName) {
        try {
            fis = new FileInputStream("./src/main/resources/" + PropFileName + ".properties");
            prop.load(fis);
            storestring = prop.getProperty(stringName);
        } catch (Exception e) {
            System.out.println("File Not Found :" + e.getMessage());
        }
        return storestring;
    }

    @BeforeSuite
    public void setUp() throws MalformedURLException {
        client = new OkHttpClient();
        androidDriver = new AndroidDriver(new URL("http://localhost:4723"), getAPKOptions());
        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(07));
        androidDriver.setFileDetector(new LocalFileDetector());
        softAssert = new SoftAssert();
        extent = new ExtentReports();
        prop = new Properties();
        mobileWait = new WebDriverWait(androidDriver, Duration.ofSeconds(12));
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("target/Dawak_Mobile_Portal.html");
        extent.attachReporter(extentSparkReporter);
        Pages.AndroidAppLogin().handleSplashScreens();
        Pages.AndroidAppLogin().loginToDawakApp();

    }



    public static String screenshot(String filename) throws IOException {
        File obj = ((TakesScreenshot) androidDriver).getScreenshotAs(OutputType.FILE);
        String destination = new File("target//" + filename + ".PNG").getAbsolutePath();
        Files.copy(obj, new File("./target//" + filename + ".PNG"));
        return destination;
    }

    @AfterMethod
    public void getResult(ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "Test case failed", ExtentColor.RED));
            test.fail(result.getThrowable());
            String destination = screenshot("Failed Scenario Screenshot");
            test.fail(result.getThrowable()).addScreenCaptureFromPath(destination);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "Test case passed", ExtentColor.GREEN));
        } else {
            test.log(Status.SKIP,
                    MarkupHelper.createLabel(result.getName() + "Test case skipped", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }

    public static JavascriptExecutor mobileJavascriptExecutor() {
        return androidDriver;
    }

    public static String generateRandomNumericString() {
        int length = 8;
        StringBuilder numericString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            prescriptionOrderID = String.valueOf(numericString.append(digit));
        }
        return prescriptionOrderID;
    }
    public static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        DateTimeFormatter formatte = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        formattedDate=now.format(formatte);
        return now.format(formatter);
    }

    @AfterSuite
    public void tearDown() {
        extent.flush();
        androidDriver.quit();
    }


}
