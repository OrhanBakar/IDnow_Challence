package idnowmobileautomation.tests;

import idnowmobileautomation.pages.PersonalInfoPage;
import idnowmobileautomation.utilities.ConfigurationReader;
import idnowmobileautomation.utilities.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PersonalInfoTest {

    private int targetRow;
    PersonalInfoPage formPageUI=new PersonalInfoPage();

    public static String Ident_ID;


    @Test
    public void fillPersonalInfoForm() {
        formPageUI.fillPersonalInfoForm(ConfigurationReader.get("baseURL"),
                "UserData.csv", 10);
        Ident_ID=formPageUI.ident_ID.getText();
        System.out.println("Ident_ID = " + Ident_ID);

    }
    @AfterMethod
    public void tearDownWebDriver() {
        Driver.closeDriver();
    }


}
