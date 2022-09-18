package ru.otus.dataprocessor;

import com.google.gson.*;
import ru.otus.model.Measurement;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResourcesFileLoader implements Loader {

    String fileName;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    //читает файл, парсит и возвращает результат
    @Override
    public List<Measurement> load(){
        String json = getJsonFromFile(fileName);
        Gson gson = new Gson();
        Measurement[] measurementArray = gson.fromJson(json, Measurement[].class);
        List<Measurement> measurementList = Arrays.asList(measurementArray);
        return measurementList;
    }

    public String getJsonFromFile (String fileName){
        InputStream path = getClass().getClassLoader().getResourceAsStream(fileName);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(path))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
        }
}
