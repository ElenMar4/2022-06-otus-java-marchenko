package ru.otus.dataprocessor;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class FileSerializer implements Serializer {

    String fileName;

    public FileSerializer(String fileName) {
        this.fileName = fileName;
    }

        //формирует результирующий json и сохраняет его в файл
    @Override
    public void serialize(Map<String, Double> data) throws IOException {
        File file = new File(fileName);
        Gson gson = new Gson();
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            String strJson = gson.toJson(data);
            pw.write(strJson);
        }
    }
}
