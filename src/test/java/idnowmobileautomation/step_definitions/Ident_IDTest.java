package idnowmobileautomation.step_definitions;

import idnowmobileautomation.pages.PersonalInfoPage;
import idnowmobileautomation.pages.Ident_IdPage;
import idnowmobileautomation.tests.Ident_IdTest;
import idnowmobileautomation.utilities.ConfigurationReader;
import idnowmobileautomation.utilities.Driver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ident_IDTest {
    private AppiumDriver<MobileElement> appiumDriver;
    public Ident_IdPage identIdPage;
    PersonalInfoPage formPageUI = new PersonalInfoPage();
    Ident_IdPage identIdPageApp = new Ident_IdPage(appiumDriver);
    WebDriverWait wait;
    String Ident_ID;

    @Given("user navigates to baseURL")
    public void userNavigatesTo() {
        Driver.get().get(ConfigurationReader.get("baseURL"));

    }

    @And("user fills in the required fields retrieving the data from the {string} row of the {string}")
    public void userFillsInTheRequiredFieldsRetrievingTheDataFromTheRowOfThe(int rowNum, String csvFilePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            int currentRow = 1;

            while ((line = br.readLine()) != null && currentRow != rowNum) {
                currentRow++;
            }

            if (line == null) {

                System.out.println("The target row couldn't be found");
                return;
            }

            String[] values = line.split(",");

            formPageUI.transactionNumberInputBox.sendKeys(values[0], Keys.ENTER);
            formPageUI.salutationDropdown.sendKeys("Mr.");
            formPageUI.firstnameInputBox.sendKeys(values[1], Keys.ENTER);
            formPageUI.surnameInputBox.sendKeys(values[2], Keys.ENTER);
            formPageUI.birthdayDropdown.sendKeys("12");
            formPageUI.monthDropdown.sendKeys("June");
            formPageUI.yearDropdown.sendKeys("1972");
            formPageUI.placeOfBirthInputBox.sendKeys(values[3], Keys.ENTER);
            formPageUI.streetInputBox.sendKeys(values[4], Keys.ENTER);
            formPageUI.houseNumberInputBox.sendKeys(values[5], Keys.ENTER);
            formPageUI.zipCodeInputBox.sendKeys(values[6], Keys.ENTER);
            formPageUI.cityInputBox.sendKeys(values[7], Keys.ENTER);
            formPageUI.countryDropdown.sendKeys("Germany");
            formPageUI.eMailInputBox.sendKeys(values[8], Keys.ENTER);
            formPageUI.mobileNumberInputBox.sendKeys(values[9], Keys.ENTER);

            ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);",
                    formPageUI.consentCheckBox);
            wait.until(ExpectedConditions.elementToBeClickable(formPageUI.consentCheckBox)).click();
            Assert.assertTrue("Consent Box is not selected", formPageUI.consentCheckBox.isSelected());
            formPageUI.continueButton.click();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Then("Continues to the next screen and selects \\(identify using your mobile phone) and notes down the Ident-ID, which will look like this: TST-XXXXX")
    public void continuesToTheNextScreenAndSelectsIdentifyUsingYourMobilePhoneAndNotesDownTheIdentIDWhichWillLookLikeThisTSTXXXXX() {
        Ident_ID = formPageUI.ident_ID.getText();
        System.out.println("Ident_ID = " + Ident_ID);
    }

    @Given("user launches to the app and assert Ident-ID field is empty")
    public void user_launches_to_the_app_and_assert_ident_id_field_is_empty() {
        Ident_IdTest ident_idTest = new Ident_IdTest();
        ident_idTest.setUp();
        wait.until(ExpectedConditions.invisibilityOf(identIdPageApp.identIDBox));
        Assert.assertTrue("Ident-ID field is not empty", identIdPageApp.isIdentIDEmpty());
    }

    @When("the Ident-ID box is filled with the code above")
    public void the_ident_id_box_is_filled_with_the_code_above() {
        identIdPageApp.identIDBox.sendKeys(Ident_ID);
    }

    @Then("the start-button should be activated")
    public void the_start_button_should_be_activated() {
        wait.until(ExpectedConditions.elementToBeClickable(identIdPageApp.confirmButton));
        Assert.assertTrue("Start-Button is activated",identIdPageApp.confirmButton.isEnabled());
    }
    @When("user clicks the start-button")
    public void userClicksTheStartButton() {

        identIdPageApp.confirmButton.click();
    }

    @Then("the Terms of Conditions screen should be seen")
    public void the_terms_of_conditions_screen_should_be_seen() {
        wait.until(ExpectedConditions.invisibilityOf(identIdPageApp.termsAndConditionsHeader));
        Assert.assertTrue("Terms and Conditions screen is not displayed", identIdPageApp.termsAndConditionsHeader.isDisplayed());

    }

    @When("user clicks on the links to open the Terms and Conditions")
    public void user_clicks_on_the_links_to_open_the_terms_and_conditions() {
        identIdPageApp.privacyPolicyLink.click();
    }

    @Then("Terms and Conditions should be seen")
    public void terms_and_conditions_should_be_seen() {
        wait.until(ExpectedConditions.urlContains("privacy"));
        Assert.assertTrue("We are not on the Privacy Policy page", appiumDriver.getCurrentUrl().contains("privacy"));
    }

    @Then("user navigates back to active Terms and Conditions screen of the app")
    public void user_navigates_back_to_active_terms_and_conditions_screen_of_the_app() {
        appiumDriver.navigate().back();
        wait.until(ExpectedConditions.invisibilityOf(identIdPageApp.termsAndConditionsHeader));
        Assert.assertTrue("Terms and Conditions screen is not displayed", identIdPageApp.termsAndConditionsHeader.isDisplayed());
    }

    @Then("user should be able to check the box and confirm Terms and Conditions and click on Start Identification button")
    public void user_should_be_able_to_check_the_box_and_confirm_terms_and_conditions_and_click_on_start_identification_button() {
        wait.until(ExpectedConditions.elementToBeClickable(identIdPageApp.termsAndConditionsCheckbox));
        identIdPageApp.termsAndConditionsCheckbox.click();
    }

    @Then("user should be able to allow camera access")
    public void user_should_be_able_to_allow_camera_access() {
        wait.until(ExpectedConditions.elementToBeClickable(identIdPageApp.startIdentButton));
        Assert.assertTrue("The start Ident button is not clickable",identIdPageApp.startIdentButton.isDisplayed());
        identIdPageApp.startIdentButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(identIdPageApp.allowCameraAccess));
        identIdPageApp.allowCameraAccess.click();
    }

    @Then("user should be able to abort the identification by killing the app.")
    public void user_should_be_able_to_abort_the_identification_by_killing_the_app() {
        appiumDriver.closeApp();
    }


}
