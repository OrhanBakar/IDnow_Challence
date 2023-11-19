package idnowmobileautomation.tests;

import idnowmobileautomation.pages.Ident_IdPage;
import idnowmobileautomation.pages.PersonalInfoPage;
import idnowmobileautomation.utilities.ConfigurationReader;
import idnowmobileautomation.utilities.Driver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PersonalInfoTest {

    PersonalInfoPage personalInfoPage=new PersonalInfoPage();

    public static String Ident_ID;


    @Test
    public void fillPersonalInfoForm() {
        personalInfoPage.fillPersonalInfoForm(ConfigurationReader.get("baseURL"),
                "UserData.csv", 2);
        Ident_ID=personalInfoPage.ident_ID.getText();

        System.out.println("Ident_ID = " + Ident_ID);

    }

    @AfterMethod
    public void tearDownWebDriver() {
        Driver.closeDriver();
    }


}
