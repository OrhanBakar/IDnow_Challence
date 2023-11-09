package idnowmobileautomation.tests;

import idnowmobileautomation.pages.Ident_IdPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Ident_IdTest {
    private AppiumDriver<MobileElement> appiumDriver;
    public Ident_IdPage identIdPage;

    @BeforeTest
    public void setUp() {
        try {
            DesiredCapabilities cap = new DesiredCapabilities();

            cap.setCapability("deviceName", "samsung SM-A715F");
            cap.setCapability("platformName", "Android");
            cap.setCapability("appPackage", "de.idnow.androidaidemo");
            cap.setCapability("appActivity", "de.idnow.android.IDnowDemoMainActivity");
            cap.setCapability("skipUnlock", "true");
            cap.setCapability("noReset", "false");
            cap.setCapability("automationName", "UIAutomator2");
            cap.setCapability("app", "C://Users//obkr4//Desktop//Java17//Appium//src//test//java//resources//AutoIdentSampleApp (5).apk");

            appiumDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723"), cap);
            identIdPage = new Ident_IdPage(appiumDriver);
        } catch (MalformedURLException mx) {
            System.out.println("URL exception");
        }
    }

    @Test
    public void IdentIdPageValidation() {

        //1.Assert Ident-Id field is empty
        Assert.assertTrue(identIdPage.isIdentIDEmpty(),"Ident-ID field is not empty");

        //2.Fill in the Ident-ID above.Is start button activated? Click it.
        identIdPage.isConfirmButtonActivated();

        //3. Do we now see the "Terms and Condition screen"?
        identIdPage.isTermsAndConditionsScreenDisplayed();

// 4. Click on the links to open the ‘Terms and Conditions’, check they are shown
        identIdPage.navigateToPrivacyPolicy();

        //5. Navigate back to active "Terms and Conditions" screen of the app
        identIdPage.navigateBackToTermsAndConditions();

        // 6. Check the box and confirm terms and conditions and click on ‘Start identification’
        identIdPage.checkTermsAndConditionsBox();
        identIdPage.confirmTermsAndConditions();

        //7. Allow camera access

        identIdPage.allowCameraAccess();

        //8. Abort the identification by killing the app

        appiumDriver.closeApp();


    }
}
