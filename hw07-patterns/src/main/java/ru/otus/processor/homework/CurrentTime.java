package ru.otus.processor.homework;

import java.time.LocalTime;

public class CurrentTime {

    public int getValueSecond() {
        LocalTime thisTime = LocalTime.now();
        return thisTime.getSecond();
    }
}
