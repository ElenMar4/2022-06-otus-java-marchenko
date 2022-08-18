package ru.otus.tests;

import ru.otus.Calculator;
import ru.otus.testLauncher.Annotations.After;
import ru.otus.testLauncher.Annotations.Before;
import ru.otus.testLauncher.Annotations.Test;
import ru.otus.testLauncher.UnitTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CalculatorTest implements UnitTest {

    Calculator calc = new Calculator();
    int numberOne = 10;
    int numberTwo = 5;

    @Before
    public void setup(){
        System.out.println("\nTEST START");
        calc = new Calculator();
        System.out.println("____Before run____" + Integer.toHexString(hashCode()));
    }

    @Test
    public void calculateAddingTest(){
        int sum = numberOne + numberTwo;
        System.out.println("____Test run____" + Integer.toHexString(hashCode()));
        assertThat(sum).isEqualTo(calc.calculateAdding(numberOne, numberTwo));
    }

    @Test
    public void calculateSubtractionTest(){
        //int diff = numberOne - numberTwo;
        int diff = (numberOne - numberTwo) + 1;
        System.out.println("____Test run____" + Integer.toHexString(hashCode()));
        assertThat(diff).isEqualTo(calc.calculateSubtraction(numberOne, numberTwo));
    }

    @Test
    public void calculateMultiplication(){
//        int mult = numberOne * numberTwo;
        int mult = numberOne * numberTwo + 1;
        System.out.println("____Test run____" + Integer.toHexString(hashCode()));
        assertThat(mult).isEqualTo(calc.calculateMultiplication(numberOne, numberTwo));
    }

    @After
    public void tearDown(){
        calc = null;
        System.out.println("____After run____" + Integer.toHexString(hashCode()) + "\nTEST STOP");
    }
}
