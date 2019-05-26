package com.zendesk.search;

public class Organization extends InputFileType {
    private static final String ORG_FILE = "organizations.json";

    public Organization() {
        super();
    }

    @Override
    public void readFile() {
        this.jsonArray = reader.readInputFile(ORG_FILE);
        super.readFile();
    }

    @Override
    public void printSearchableFields() {
        System.out.println("Organizations: ");
        super.printSearchableFields();
    }
}
