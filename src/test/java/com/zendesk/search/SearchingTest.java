package com.zendesk.search;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.*;

public class SearchingTest {
    @Test
    public void testpropertyExists() {
        JSONObject testObj = new JSONObject();
        testObj.put("id", 10);
        testObj.put("name", "Sam");

        JSONArray list = new JSONArray();
        list.add("Kenwood");
        list.add("Lynn");
        testObj.put("tags", list);

        JSONArray fileArray = new JSONArray();
        fileArray.add(testObj);

        try (FileWriter file = new FileWriter("testingFile.json")) {
            file.write(fileArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputFileType user = new MockFileType();
        user.readFile();

        assert(user.jsonArray != null);
        assert(!user.searchableFields.isEmpty()); // cannot be empty

        // search in property
        assertTrue(user.valueExists(testObj, "id", "10"));
        assertFalse(user.valueExists(testObj, "id", "12000"));
        assertTrue(user.valueExists(testObj, "name", "Sam"));

        // search in property array
        assertTrue(user.valueExists(testObj, "tags", "Lynn"));
        assertFalse(user.valueExists(testObj, "tags", "Testing"));
    }
}
