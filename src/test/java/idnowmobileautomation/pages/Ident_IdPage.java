package idnowmobileautomation.pages;

import idnowmobileautomation.tests.PersonalInfoTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Ident_IdPage {

   public String accessIdentNumber(){
       String identNumber=PersonalInfoTest.Ident_ID;
       return identNumber;
   }
    AppiumDriver appiumDriver;
   WebDriverWait wait;
    public Ident_IdPage(AppiumDriver<MobileElement> appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    @AndroidFindBy(id="de.idnow.androidaidemo:id/editTextCode")
    public MobileElement identIDBox;

    @AndroidFindBy(id="de.idnow.androidaidemo:id/editTextCode")
    public WebElement identNummerBox;

    @AndroidFindBy(id="de.idnow.androidaidemo:id/start_ident")
    public WebElement confirmButton;

    @AndroidFindBy(id="de.idnow.androidaidemo:id/consentScreenHeader")
    public WebElement termsAndConditionsHeader;

    @AndroidFindBy(id="de.idnow.androidaidemo:id/textViewPrivacyPolicy")
    public WebElement privacyPolicyLink;

    @AndroidFindBy(id="de.idnow.androidaidemo:id/privacy_item_body_checkbox")
    public WebElement termsAndConditionsCheckbox;

    @AndroidFindBy(id="de.idnow.androidaidemo:id/buttonStartIdent")
    public WebElement startIdentButton;

    @AndroidFindBy(id="com.android.permissioncontroller:id/permission_allow_foreground_only_button")
    public WebElement allowCameraAccess;

    public boolean isIdentIDEmpty() {
        wait.until(ExpectedConditions.invisibilityOf(identNummerBox));
        return identNummerBox.getText().isEmpty();
    }

    public void isConfirmButtonActivated() {

        identIDBox.sendKeys(accessIdentNumber());
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
        Assert.assertTrue(confirmButton.isEnabled(),"Confirm button is not activated");
        confirmButton.click();

    }

    public void isTermsAndConditionsScreenDisplayed() {
        wait.until(ExpectedConditions.invisibilityOf(termsAndConditionsHeader));
        Assert.assertTrue(termsAndConditionsHeader.isDisplayed(), "Terms and Conditions screen is not displayed");
    }

    public void navigateToPrivacyPolicy() {

        Assert.assertTrue(privacyPolicyLink.isDisplayed(), "Privacy Policy link is not displayed");
        privacyPolicyLink.click();
        wait.until(ExpectedConditions.urlContains("privacy"));
        Assert.assertTrue(appiumDriver.getCurrentUrl().contains("privacy"),"We are not on the Privacy Policy page");


    }
    public void navigateBackToTermsAndConditions() {
        appiumDriver.navigate().back();
        wait.until(ExpectedConditions.invisibilityOf(termsAndConditionsHeader));
        Assert.assertTrue(termsAndConditionsHeader.isDisplayed(), "Terms and Conditions screen is not displayed");

    }

    public void checkTermsAndConditionsBox() {
        wait.until(ExpectedConditions.elementToBeClickable(termsAndConditionsCheckbox));
        termsAndConditionsCheckbox.click();

    }

    public void confirmTermsAndConditions() {
        wait.until(ExpectedConditions.elementToBeClickable(startIdentButton));
        Assert.assertTrue(startIdentButton.isDisplayed(), "The Start Ident button is not clickable");
        startIdentButton.click();

    }

    public void allowCameraAccess() {
        wait.until(ExpectedConditions.elementToBeClickable(allowCameraAccess));
        allowCameraAccess.click();
    }









}
