package org.example.testing;


import org.example.testing.pages.calculator.CalculatorPage;
import org.example.testing.utils.TestListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestListener.class)
public class CalculatorTest extends BaseTest {

    @Test
    public void test1(){
        CalculatorPage calculatorPage = new CalculatorPage();
        Assertions.assertEquals(20, calculatorPage.test());
    }
}
