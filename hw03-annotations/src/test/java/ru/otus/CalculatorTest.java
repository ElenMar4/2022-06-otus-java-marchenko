package ru.otus;

import org.junit.jupiter.api.DisplayName;
import ru.otus.Annotations.After;
import ru.otus.Annotations.Before;
import ru.otus.Annotations.Test;

@DisplayName("____calculatorTest____")
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
    public boolean calculateAddingTest(){
        int sum = numberOne + numberTwo;
        System.out.println("____Test run____" + Integer.toHexString(hashCode()));
        return sum == calc.calculateAdding(numberOne, numberTwo);
    }

    @Test
    public boolean calculateSubtractionTest(){
        //int diff = numberOne - numberTwo;
        int diff = (numberOne - numberTwo) + 1;
        System.out.println("____Test run____" + Integer.toHexString(hashCode()));
        return diff == calc.calculateSubtraction(numberOne, numberTwo);
    }

    @Test
    public boolean calculateMultiplication(){
        int mult = numberOne * numberTwo;
        System.out.println("____Test run____" + Integer.toHexString(hashCode()));
        return mult == calc.calculateMultiplication(numberOne, numberTwo);
    }

    @After
    public void tearDown(){
        calc = null;
        System.out.println("____After run____" + Integer.toHexString(hashCode()) + "\nTEST STOP");
    }

}
