package ru.otus;

import com.google.common.base.CharMatcher;

import java.util.Random;

public class HelloOtus {
    public static void main(String... args) {
        Random random = new Random();
        int number = random.nextInt(1000, 9999);
        String numberStr = Integer.toString(number);
        char digit = '8';
        boolean result = CharMatcher.anyOf(numberStr).matches(digit);

        System.out.println("Цифровой код: " + number);
        if (result) {
            System.out.println("В цифровом коде найдено число " + digit);
        } else System.out.println("В цифровом коде не найдено число " + digit);
    }
}
