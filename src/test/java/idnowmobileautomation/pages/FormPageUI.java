package idnowmobileautomation.pages;
import idnowmobileautomation.utilities.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Rotatable;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class FormPageUI {

    public FormPageUI() {

        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(css = "#ember486")
    public WebElement salutationDropdown;

    @FindBy(css = "#ember521")
    public WebElement firstnameInputBox;

    @FindBy(css = "#ember523")
    public WebElement surnameInputBox;

    @FindBy(css = "#ember526")
    public WebElement birthdayDropdown;

    @FindBy(css = "#ember528")
    public WebElement monthDropdown;

    @FindBy(css = "#ember530")
    public WebElement yearDropdown;

    @FindBy(css = "#ember844")
    public WebElement placeOfBirthInputBox;

    @FindBy(css = "#ember847")
    public WebElement nationalityDropdown;

    @FindBy(css = "#ember1256")
    public WebElement streetInputBox;

    @FindBy(css = "#ember1258")
    public WebElement houseNumberInputBox;

    @FindBy(css = "p[class='transaction-token']")
    public WebElement ident_ID;


    @FindBy(css = "#ember1260")
    public WebElement zipCodeInputBox;

    @FindBy(css = "#ember1262")
    public WebElement cityInputBox;

    @FindBy(css = "#ember1254")
    public WebElement countryDropdown;

    @FindBy(css = "#ember1775")
    public WebElement eMailInputBox;

    @FindBy(css = "#ember1778")
    public WebElement mobileNumberInputBox;

    @FindBy(css = "#start-has-webcam")
    public WebElement consentCheckBox;

    @FindBy(css = "button[data-ember-action='464']")
    public WebElement continueButton;

    @FindBy(css = "#ember477")
    public WebElement transactionNumberInputBox;


    WebDriverWait wait=new WebDriverWait(Driver.get(), Duration.ofSeconds(5000L));

    public void fillPersonalInfoForm(String baseURL,String csvFilePath, int targetRow) {

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            int currentRow = 1;

            while ((line = br.readLine()) != null && currentRow != targetRow) {
                currentRow++;
            }

            if (line == null) {

                System.out.println("Target row couldn't be found");
                return;
            }

            String[] values = line.split(",");

            Driver.get().get(baseURL);

            transactionNumberInputBox.sendKeys(values[0], Keys.ENTER);
            salutationDropdown.sendKeys("Mr.");
            firstnameInputBox.sendKeys(values[1],Keys.ENTER);
            surnameInputBox.sendKeys( values[2],Keys.ENTER);
            birthdayDropdown.sendKeys("12");
            monthDropdown.sendKeys("June");
            yearDropdown.sendKeys("1972");
            placeOfBirthInputBox.sendKeys( values[3],Keys.ENTER);
            streetInputBox.sendKeys(values[4],Keys.ENTER);
            houseNumberInputBox.sendKeys( values[5],Keys.ENTER);
            zipCodeInputBox.sendKeys( values[6],Keys.ENTER);
            cityInputBox.sendKeys( values[7],Keys.ENTER);
            countryDropdown.sendKeys("Germany");
            eMailInputBox.sendKeys( values[8],Keys.ENTER);
            mobileNumberInputBox.sendKeys( values[9],Keys.ENTER);

            ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);",
                    consentCheckBox);
            wait.until(ExpectedConditions.elementToBeClickable(consentCheckBox)).click();
            Assert.assertTrue(consentCheckBox.isSelected(),"Please select the Consent Box!");
            continueButton.click();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
