package ru.otus.listener.homework;

import ru.otus.listener.Listener;
import ru.otus.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {

    private final List<Message> messagesList;

    public HistoryListener() {
        this.messagesList = new ArrayList<>();
    }

    @Override
    public void onUpdated(Message msg) throws CloneNotSupportedException{
        try {
            messagesList.add(msg.clone());
        } catch (Exception e){
            throw new CloneNotSupportedException();
        }
    }


    @Override
    public Optional<Message> findMessageById(long id) {
        return messagesList.stream()
                .filter(message -> (new Message.Builder(id).build()).equals(message))
                .findAny();
    }
}
