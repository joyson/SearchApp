package com.zendesk.search;

public class Tickets extends InputFileType {
    private static final String TICKETS_FILE = "tickets.json";

    public Tickets() {
        super();
    }

    @Override
    public void readFile() {
        this.jsonArray = reader.readInputFile(TICKETS_FILE);
        super.readFile();
    }

    @Override
    public void printSearchableFields() {
        System.out.println("Tickets: ");
        super.printSearchableFields();
    }
}
