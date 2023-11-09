package idnowmobileautomation.tests;

import idnowmobileautomation.pages.FormPageUI;
import idnowmobileautomation.utilities.ConfigurationReader;
import idnowmobileautomation.utilities.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class IDnowCodeChallenge {

    private int targetRow = 10;
    FormPageUI formPageUI=new FormPageUI();

    public String Ident_ID="";


    @Test
    public void fillPersonalInfoForm() {
        formPageUI.fillPersonalInfoForm(ConfigurationReader.get("baseURL"),
                "UserData.csv", targetRow);
        Ident_ID=formPageUI.ident_ID.getText();
        System.out.println("Ident_ID = " + Ident_ID);

    }
    @AfterMethod
    public void tearDownWebDriver() {
        Driver.closeDriver();
    }


}
