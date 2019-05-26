package com.zendesk.search;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

class JsonReader {

    JSONArray readInputFile(String fileName) {
        JSONArray jsonArray = null;
        JSONParser parser = new JSONParser();

        try {
            File file = new File(fileName);
            Reader reader = new FileReader(file);
            jsonArray = (JSONArray) parser.parse(reader);

            System.out.println("Successfully read: " + fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonArray;
    }
}
