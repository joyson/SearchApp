package com.zendesk.search;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

/**
 * Base class for all input file types.
 */
public abstract class InputFileType {

    protected JsonReader reader;
    protected Set<String> searchableFields;
    protected JSONArray jsonArray;
    private static final String OUTPUT_FORMAT = "%-30s%s%n";

    public InputFileType() {
        this.reader = new JsonReader();
        this.searchableFields = new HashSet<>();
        this.jsonArray = new JSONArray();
    }

    /**
     * Read the file and build a list of searchable fields.
     */
    protected void readFile() {
        // Json Objects in input file may not contain all properties. So creating a Set from all Json object properties.
        if (jsonArray != null && !jsonArray.isEmpty()) {
            for (Object object: jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                searchableFields.addAll(jsonObject.keySet());
            }
        }
    }

    protected void printSearchableFields() {
        System.out.println(searchableFields);
    }

    /**
     * Gets search term and value from user. Prints the value if search term exists else prints error message.
     */
    protected void getSearchResults() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter search term: ");
        String searchTerm = scanner.nextLine().trim();

        if (!searchableFields.contains(searchTerm)) {
            System.out.println("Search Term is Invalid.");
            System.out.println("Valid search terms: ");
            printSearchableFields();
        } else {
            System.out.println("Enter search value: ");
            String searchValue = scanner.nextLine().trim();

            boolean found = false;
            // Value can be in multiple json objects.
            // if JSONObject contains the key and value. Print it.
            for (Object object: jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                if (jsonObject.containsKey(searchTerm)) {
                    if (valueExists(jsonObject, searchTerm, searchValue)) {
                        found = true;
                        printJsonOutput(jsonObject);
                    }
                }
            }
            if (!found) {
                System.out.println("Searching for " + searchTerm + " with a value of " + searchValue);
                System.out.println("No result found.");
            }
        }
    }

    /**
     * The value of Json property can be String, Long, Array, etc.
     * Method returns true if the search Value exists.
     */
    protected boolean valueExists(JSONObject jsonObject, String searchTerm, String searchValue) {
        List<String> values = new ArrayList<>();
        Object value = jsonObject.get(searchTerm);

        if (value == null) {
            values.add("null");
        } else if (value instanceof JSONArray) {
            JSONArray temp = (JSONArray) value;
            for (Object t: temp) {
                if (t == null) {
                    values.add("null");
                } else {
                    values.add(t.toString());
                }
            }
        } else {
            values.add(value.toString());
        }

        return values.contains(searchValue);
    }

    /**
     * Prints the Json object to terminal.
     */
    private void printJsonOutput(JSONObject jsonObject) {
        // found the value. Print.
        for (Object o : jsonObject.keySet()) {
            String key = (String) o;
            System.out.printf(OUTPUT_FORMAT, key, jsonObject.get(key));
        }
        System.out.println("");
    }
}
