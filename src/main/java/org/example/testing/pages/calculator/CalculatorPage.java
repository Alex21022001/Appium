package org.example.testing.pages.calculator;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.example.testing.pages.BasePage;

public class CalculatorPage extends BasePage {
    public CalculatorPage() {
        super();
    }

    @AndroidFindBy(id = "com.google.android.calculator:id/op_mul")
    private MobileElement multiplyButton;
    @AndroidFindBy(id = "com.google.android.calculator:id/eq")
    private MobileElement equalButton;
    @AndroidFindBy(id = "com.google.android.calculator:id/result_final")
    private MobileElement results;
    private MobileElement getNumberButton(String number) {
        MobileElement numberButton = driver.findElementById("com.google.android.calculator:id/digit_" + number);
        return numberButton;
    }


    public int test() {
        waitForVisibilityOfElement(WAIT_TIME, getNumberButton("1"));
        getNumberButton("1").click();
        getNumberButton("0").click();
        multiplyButton.click();
        getNumberButton("22").click();
        equalButton.click();
        return Integer.parseInt(results.getText());
    }

}
