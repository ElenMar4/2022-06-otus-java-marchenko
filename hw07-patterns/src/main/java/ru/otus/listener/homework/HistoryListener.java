package ru.otus.listener.homework;

import ru.otus.listener.Listener;
import ru.otus.model.Message;

import java.time.LocalTime;
import java.util.*;

public class HistoryListener implements Listener, HistoryReader {

    private final Map<LocalTime, Message> messagesList;

    public HistoryListener() {

        this.messagesList = new HashMap<>();
    }

    @Override
    public void onUpdated(Message msg) throws CloneNotSupportedException{
        try {
            messagesList.put(LocalTime.now(), msg.clone());
        } catch (Exception e){
            throw new CloneNotSupportedException();
        }
    }


    @Override
    public Optional<Message> findMessageById(long id) {
        return messagesList.values().stream()
                .filter(message -> (new Message.Builder(id).build()).equals(message))
                .findAny();
    }
}
