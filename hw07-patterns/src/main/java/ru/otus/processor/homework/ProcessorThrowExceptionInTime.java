package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.processor.Processor;

public class ProcessorThrowExceptionInTime implements Processor {

    private final CurrentTime currentTime;
     public ProcessorThrowExceptionInTime (CurrentTime currentTime){
         this.currentTime = currentTime;
     }

    @Override
    public Message process(Message message) {
         if (currentTime.getValueSecond()%2 == 0){
             throw new RuntimeException("it's an even second");
         }
        return message;
    }
}
