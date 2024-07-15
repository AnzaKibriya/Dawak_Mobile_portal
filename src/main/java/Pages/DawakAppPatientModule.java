package Pages;

import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.IExpectedExceptionsHolder;

import java.io.FileNotFoundException;
import java.util.List;

import static Helper.BaseClass.*;
import static Pages.MobileCommon.patient;

public class DawakAppPatientModule {
    AndroidDriver androidDriver;
    String createOrderPath = "./src/main/resources/CreateNewPatient.json";
    String textViews = "//androidx.recyclerview.widget.RecyclerView[@resource-id='"+packageName+":id/patient_detail_rv']/android.view.ViewGroup";
    By addFamilyBtn = AppiumBy.id(packageName+":id/add_patient_btn");
    By relationDropdown = AppiumBy.id(packageName+":id/relation_tv");
    By relationOtherCheckBox = AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\""+packageName+":id/rvRelation\"]/android.view.ViewGroup[5]");
    By confirmRelationBtn = AppiumBy.id(packageName+":id/button3");
    By otpFields = AppiumBy.id(packageName+":id/otp_view");

    By emiratesIdField = AppiumBy.id(packageName+":id/mask_et");
    By registerBtn = AppiumBy.id(packageName+":id/register_btn");
    By verifyBtn = AppiumBy.id(packageName+":id/next_btn");
    By proceedBtn = AppiumBy.id(packageName+":id/register_btn");
    By successLabel = AppiumBy.id(packageName+":id/success_label_tv");
    By dashboardNavigateBtn = AppiumBy.id(packageName+":id/dashbord_navigator_btn");
    By deletePatientBtn = AppiumBy.id(packageName+":id/delete_v");
    By confirmDeleteBtn = AppiumBy.id(packageName+":id/confirm_button");
    String patientRemoveMessage = "//android.widget.Toast[@text=\"Patient removed successfully\"]";
    By manageFamilyText = AppiumBy.id(packageName+":id/textView7");

    public DawakAppPatientModule(AndroidDriver AndroidDriver) {
        androidDriver = AndroidDriver;
    }

    public void clickOnAddFamilyBtn() {
        mobileWait.until(ExpectedConditions.elementToBeClickable(addFamilyBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
    }

    public void addNewPatient() {
        mobileWait.until(ExpectedConditions.elementToBeClickable(relationDropdown)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(relationOtherCheckBox)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(confirmRelationBtn)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(emiratesIdField)).sendKeys("784"+emiratesID);
        mobileWait.until(ExpectedConditions.elementToBeClickable(registerBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        test.log(Status.PASS, "New patient added successfully");

    }
    public void verifyOTP() {
        mobileWait.until(ExpectedConditions.elementToBeClickable(otpFields)).sendKeys("1234");
        mobileWait.until(ExpectedConditions.elementToBeClickable(verifyBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        Pages.MobileCommon().waitForLoaderInvisibility();
        test.log(Status.PASS, "otp verified successfully");

    }

    public void verifyPatientDetailsAndProceed() throws FileNotFoundException {
        List<WebElement> listviews = androidDriver.findElements(By.xpath(textViews));
        Pages.MobileCommon().loadJson(createOrderPath);
        softAssert.assertEquals(patient.getAsJsonPrimitive("eid").getAsString(), listviews.get(0).findElement(By.id(packageName+":id/description_tv")).getText());
        softAssert.assertEquals(prescriptionOrderID, listviews.get(0).findElement(By.id(packageName+":id/description_2_tv")).getText());
        softAssert.assertEquals(patient.getAsJsonPrimitive("dob").getAsString().replaceAll("/", "-"), listviews.get(1).findElement(By.id(packageName+":id/description_tv")).getText());
        softAssert.assertEquals("9715" + prescriptionOrderID, listviews.get(2).findElement(By.id(packageName+ ":id/description_tv")).getText());
        softAssert.assertEquals("Others", listviews.get(2).findElement(By.id(packageName+":id/description_2_tv")).getText());
        softAssert.assertEquals(patient.getAsJsonObject("nationality").getAsJsonPrimitive("value").getAsString(), listviews.get(3).findElement(By.id(packageName+":id/description_tv")).getText());
        softAssert.assertEquals(patient.getAsJsonPrimitive("patGender"), listviews.get(3).findElement(By.id(packageName+":id/description_2_tv")).getText());
        softAssert.assertEquals(patient.getAsJsonObject("maritalStatus").getAsJsonPrimitive("value").getAsString(), listviews.get(4).findElement(By.id(packageName+":id/description_tv")).getText());
        softAssert.assertEquals(patient.getAsJsonObject("language").getAsJsonPrimitive("value").getAsString(), listviews.get(4).findElement(By.id(packageName+":id/description_2_tv")).getText());
        mobileWait.until(ExpectedConditions.elementToBeClickable(proceedBtn)).click();
        test.log(Status.PASS, "successfully verified patient and proceed");

    }

    public void navigateBackToDashboard(){
        mobileWait.until(ExpectedConditions.visibilityOfElementLocated(successLabel)).getText();
        Assert.assertEquals(mobileWait.until(ExpectedConditions.visibilityOfElementLocated(successLabel)).getText(), "Patient Added Successfully!");
        mobileWait.until(ExpectedConditions.elementToBeClickable(dashboardNavigateBtn)).click();
        test.log(Status.PASS, "Navigate back to Dashboard");

    }

        public void deletePatient() {
            mobileWait.until(ExpectedConditions.elementToBeClickable(deletePatientBtn)).click();
            mobileWait.until(ExpectedConditions.elementToBeClickable(confirmDeleteBtn)).click();
            Assert.assertEquals(androidDriver.findElement(By.xpath(patientRemoveMessage)).getText(), "Patient removed successfully" );
            test.log(Status.PASS, "patient deleted successfully");

    }
}
