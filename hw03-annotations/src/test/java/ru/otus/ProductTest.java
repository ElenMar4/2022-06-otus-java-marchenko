package ru.otus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addProduct() {

    }

    @Test
    void getSumOrder() {
    }
}
/*
public class testOutputToConsole {
    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void testString() {
        String value = "сорок пять ";
        String currency = "рублей";
        String outputStrTest = value + currency;

        ConsoleUserInteractionService example = new ConsoleUserInteractionService();
        example.outputToConsole(value, currency);
        Assert.assertEquals(outputStrTest, output.toString());
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        }
        }
 */