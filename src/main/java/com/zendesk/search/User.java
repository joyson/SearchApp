package com.zendesk.search;

public class User extends InputFileType {
    private static final String USER_FILE = "users.json";

    public User() {
        super();
    }

    @Override
    public void readFile() {
        this.jsonArray = reader.readInputFile(USER_FILE);
        super.readFile();
    }

    @Override
    public void printSearchableFields() {
        System.out.println("User: ");
        super.printSearchableFields();
    }
}
