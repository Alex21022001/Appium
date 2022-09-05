package org.example.testing.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class GmailLoginPage extends BasePage {

    @AndroidFindBy(id = "com.google.android.gm:id/og_apd_internal_image_view")
    private MobileElement usersIcon;

    @AndroidFindBy(id = "com.google.android.gm:id/og_secondary_account_information")
    private MobileElement usersEmail;

    @AndroidFindBy(id = "com.google.android.gm:id/account_setup_item")
    private MobileElement googleLoginTypeButton;

    @AndroidFindBy(className = "android.widget.EditText")
    private MobileElement emailField;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Next\"]")
    private MobileElement nextButton;

    @AndroidFindBy(xpath = "//android.widget.EditText")
    private MobileElement passwordField;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Don’t turn on\"]")
    private MobileElement dontTurnOnButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"I agree\"]")
    private MobileElement agreeButton;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button")
    private MobileElement acceptButton;

    public GmailLoginPage choseGoogleLoginType() {
        waitForVisibilityOfElement(WAIT_TIME, googleLoginTypeButton);
        googleLoginTypeButton.click();
        return this;
    }

    public GmailLoginPage fillInEmail(String email) {
        waitForVisibilityOfElement(WAIT_TIME, emailField);
        emailField.click();
        emailField.sendKeys(email);
        waitForVisibilityOfElement(WAIT_TIME, nextButton);
        nextButton.click();
        return this;
    }

    public GmailLoginPage fillInPassword(String password) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        waitForVisibilityOfElement(WAIT_TIME, passwordField);
        passwordField.sendKeys(password);
        nextButton.click();
        return this;
    }

    public GmailLoginPage canselPopUp() {
        waitForVisibilityOfElement(WAIT_TIME, agreeButton);
        agreeButton.click();
        try {
            waitForVisibilityOfElement(30, acceptButton);
            acceptButton.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return this;
    }


    public GmailLoginPage openGmailProfile() {
        waitForVisibilityOfElement(WAIT_TIME, usersIcon);
        usersIcon.click();
        return this;
    }

    public boolean isUserEmailCorrect(String email) {
        waitForVisibilityOfElement(WAIT_TIME, usersEmail);
        return usersEmail.getText().equals(email);
    }


}
