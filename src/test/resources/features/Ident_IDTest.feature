Feature: Ident_ID MobileApp Test

  User story: As I User I should be able to use IDnow Mobile App

  Scenario:
    Given user navigates to "https://go.test.idnow.de/regressionallsteps/userdata"
    And user fills in the required fields retrieving the data from the "10" row of the "UserData.csv"
    Then Continues to the next screen and selects (identify using your mobile phone) and notes down the Ident-ID, which will look like this: TST-XXXXX

  Scenario:With the sample app from https://go.wetransfer.com/t-BCrrwNSzUF

    Given user launches to the app and assert Ident-ID field is empty
    When the Ident-ID box is filled with the code above
    Then the start-button should be activated
    When user clicks the start-button
    Then the Terms of Conditions screen should be seen
    When user clicks on the links to open the Terms and Conditions
    Then Terms and Conditions should be seen
    And user navigates back to active Terms and Conditions screen of the app
    Then user should be able to check the box and confirm Terms and Conditions and click on Start Identification button
    And user should be able to allow camera access
    Then user should be able to abort the identification by killing the app.
