package ru.otus.model;

import java.util.ArrayList;
import java.util.List;

public class ObjectForMessage {
    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    protected ObjectForMessage clone() throws CloneNotSupportedException {
        try {
            ObjectForMessage obj = new ObjectForMessage();
            obj.setData(new ArrayList<>(data));
            return obj;
        } catch (Exception e){
            throw new CloneNotSupportedException();
        }

    }
}