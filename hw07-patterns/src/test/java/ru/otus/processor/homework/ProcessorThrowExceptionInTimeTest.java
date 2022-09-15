package ru.otus.processor.homework;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.model.Message;
import ru.otus.processor.Processor;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

class ProcessorThrowExceptionInTimeTest {

    @Test
    @DisplayName("выбрасывает исключение RuntimeException в чётную секунду")
    void process() {

        Message testMessage = new Message.Builder(1L).build();

        CurrentTime currentSecond = Mockito.mock(CurrentTime.class);
        when(currentSecond.getValueSecond()).thenReturn(2);

        Processor testProcessor = new ProcessorThrowExceptionInTime(currentSecond);
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> testProcessor.process(testMessage));
    }
}