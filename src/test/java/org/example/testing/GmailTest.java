package org.example.testing;

import org.example.testing.pages.GmailMainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GmailTest extends BaseTest {

    private static final String EMAIL = "testaccforwebdriver@gmail.com";
    private static final String PASSWORD = "testaccforwebdriver21";
    private static final String TOPIC_FOR_LETTER = "Test topic";
    private static final String NAME_OF_IMAGE = "randomCat.jpg";
//    @Test
//    public void singInGmailAcc(){
//        GmailLoginPage gmailLoginPage = new GmailLoginPage();
//        gmailLoginPage.choseGoogleLoginType().fillInEmail(EMAIL)
//                .fillInPassword(PASSWORD)
//                .canselPopUp();
//    }

    @Test
    public void sendMessToAnotherAccTest() {
        GmailMainPage gmailMainPage = new GmailMainPage();
        Assertions.assertTrue(gmailMainPage.openApp().isMyGmailAcc(EMAIL));
        gmailMainPage.composeLetterWithAttachment(EMAIL,TOPIC_FOR_LETTER,NAME_OF_IMAGE).sendLetter();
        Assertions.assertTrue(gmailMainPage.openGmailMenu()
                .openMenuType("Outbox")
                .isCorrectMessage(NAME_OF_IMAGE,TOPIC_FOR_LETTER));
    }
}
