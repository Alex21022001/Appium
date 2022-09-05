package org.example.testing.pages;

import io.appium.java_client.MobileElement;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;

public class GmailMainPage extends BasePage {
    private static final Logger logger = LogManager.getRootLogger();

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Gmail\"]")
    private MobileElement gmailApp;
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView")
    private MobileElement gotIt;
    @AndroidFindBy(id = "com.google.android.gm:id/action_done")
    private MobileElement takeMeToGmail;
    @AndroidFindBy(id = "com.google.android.gm:id/compose_button")
    private MobileElement composeButton;
    @AndroidFindBy(id = "com.google.android.gm:id/dismiss_button")
    private MobileElement popUpElement;
    @AndroidFindBy(id = "com.google.android.gm:id/og_apd_internal_image_view")
    private MobileElement myGmailProfile;
    @AndroidFindBy(id = "com.google.android.gm:id/og_secondary_account_information")
    private MobileElement AccsLoginElement;


    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.EditText")
    private MobileElement recipientElement;
    @AndroidFindBy(id = "com.google.android.gm:id/peoplekit_listview_flattened_row")
    private MobileElement confirmRecipientElement;
    @AndroidFindBy(id = "com.google.android.gm:id/subject")
    private MobileElement subjectName;
    @AndroidFindBy(id = "com.google.android.gm:id/add_attachment")
    private MobileElement addAttachmentButton;
    @AndroidFindBy(id = "com.google.android.gm:id/title")
    private List<MobileElement> listOfAttachmentsTypes;
    @AndroidFindBy(className = "android.widget.TextView")
    private List<MobileElement> listOfImages;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]")
    private MobileElement menuButton;
    @AndroidFindBy(className = "android.widget.TextView")
    private List<MobileElement> listOfMenuTypes;
    @AndroidFindBy(className = "android.widget.TextView")
    private List<MobileElement> listOfMessages;


    @AndroidFindBy(id = "com.google.android.gm:id/send")
    private MobileElement sendButton;

    public GmailMainPage openApp() {
        waitForVisibilityOfElement(WAIT_TIME, gmailApp);
        logger.info("Open gmail");
        gmailApp.click();
        return this;
    }

    public GmailMainPage singInAcc() {
        waitForVisibilityOfElement(WAIT_TIME, gotIt);
        gotIt.click();
        logger.info("Click on GOT IT button");
        waitForVisibilityOfElement(WAIT_TIME, takeMeToGmail);
        takeMeToGmail.click();
        logger.info("Click on TAKE ME TO EMAIL button");
        return this;
    }

    public GmailMainPage closeALlPopUp() {
        try {
            waitForVisibilityOfElement(WAIT_TIME, popUpElement);
            popUpElement.click();
        } catch (NoSuchElementException e) {
            logger.warn("Popup messages are absent");
        }
        return this;
    }

    public boolean isMyGmailAcc(String login) {
        waitForVisibilityOfElement(WAIT_TIME, myGmailProfile);
        logger.info("Try to open Acc profile");
        myGmailProfile.click();
        waitForVisibilityOfElement(WAIT_TIME, AccsLoginElement);
        logger.info("Verify account's login {}", AccsLoginElement.getText());
        boolean check = AccsLoginElement.getText().equals(login);
        logger.info("Is the acc equal to {} \n {}", login, check);
        driver.findElementByAccessibilityId("Close").click();
        logger.info("Close Profile");
        return check;
    }

    public GmailMainPage composeLetter(String recipient, String subject) {
        waitForVisibilityOfElement(WAIT_TIME, composeButton);
        logger.info("Try to click on {} button", composeButton.getText());
        composeButton.click();
        waitForVisibilityOfElement(WAIT_TIME, recipientElement);
        logger.info("Try to write a recipient");
        recipientElement.sendKeys(recipient);
        waitForVisibilityOfElement(WAIT_TIME,confirmRecipientElement);
        confirmRecipientElement.click();
        waitForVisibilityOfElement(WAIT_TIME, subjectName);
        logger.info("Try to write a subject");
        subjectName.sendKeys(subject);
        return this;
    }

    public GmailMainPage composeLetterWithAttachment(String recipient, String subject,String nameOfImage){
        composeLetter(recipient, subject);
        waitForVisibilityOfElement(WAIT_TIME,addAttachmentButton);
        logger.info("Click on Add Attachment button");
        addAttachmentButton.click();
        waitForVisibilityOfElement(WAIT_TIME,listOfAttachmentsTypes.get(0));
        logger.info("Chose add file option");
        listOfAttachmentsTypes.stream().filter(x->x.getText().equals("Attach file")).findFirst().get().click();
        findNeededImage(nameOfImage).click();
        return this;
    }

    public MobileElement findNeededImage(String nameOfImage){
        waitForVisibilityOfElement(WAIT_TIME,listOfImages.get(0));
        logger.info("Chose {} image",nameOfImage);
       return listOfImages.stream().filter(x->x.getText().equals(nameOfImage)).findFirst().get();
    }

    public GmailMainPage sendLetter(){
        waitForVisibilityOfElement(WAIT_TIME,sendButton);
        logger.info("Try to send the letter");
        sendButton.click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public GmailMainPage openGmailMenu(){
        waitForVisibilityOfElement(WAIT_TIME,menuButton);
        logger.info("Open menu");
        menuButton.click();
        return this;
    }
    public GmailMainPage openMenuType(String menuName){
        waitForVisibilityOfElement(WAIT_TIME,listOfMenuTypes.get(0));
        logger.info("Open {} menu",menuName);
        listOfMenuTypes.stream().filter(x->x.getText().equals(menuName)).findFirst().get().click();
        return this;
    }
    public boolean isCorrectMessage(String nameOfImage, String topic){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        waitForVisibilityOfElement(WAIT_TIME,listOfMessages.get(0));
        logger.info("Find a newly message");
        listOfMessages.stream().filter(x->x.getText().equals(topic)).findFirst().get().click();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("//*[@text='").append(nameOfImage).append("']");
        System.out.println(stringBuilder);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MobileElement imageElement = driver.findElement(By.xpath(stringBuilder.toString()));
        System.out.println(imageElement.getText());
        boolean result = imageElement.getText().equals(nameOfImage);
        logger.info("Verify that this message {} is equal to {}",imageElement.getText(),nameOfImage);
        logger.info(result);
        imageElement.click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


}
