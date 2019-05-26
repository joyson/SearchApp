package com.zendesk.search;

import java.util.Scanner;

/**
 * Main class and entry point to the command line Search Application.
 */
public class SearchApp {

    public static void main(String[] args) {

        System.out.println("Welcome to Zendesk Search");
        System.out.println("Type 'quit' to exit at anytime, Press 'Enter' to continue \n");

        // Read the command-line input
        Scanner scanner = new Scanner(System.in);
        InputFileType user = InputFileFactory.getInputFileInstance(1);
        user.readFile();
        InputFileType tickets = InputFileFactory.getInputFileInstance(2);
        tickets.readFile();
        InputFileType org = InputFileFactory.getInputFileInstance(3);
        org.readFile();

        while(true) {
            printWelcomeOptions();

            String searchOption = scanner.nextLine().trim();
            switch (searchOption) {
                case "1":
                    while (true) {
                        printSearchOptions();
                        String searchFilesOption = scanner.nextLine().trim();

                        switch(searchFilesOption) {
                            case "1":
                                user.getSearchResults();
                                break;
                            case "2":
                                tickets.getSearchResults();
                                break;
                            case "3":
                                org.getSearchResults();
                                break;
                            case "quit":
                                exitWithMessage();
                                break;
                            default:
                                System.out.println("Invalid input! \n");
                        }
                    }
                case "2":
                    user.printSearchableFields();
                    tickets.printSearchableFields();
                    org.printSearchableFields();
                    break;

                case "quit":
                    exitWithMessage();
                    break;

                default:
                    System.out.println("Invalid input! \n");
            }
        }
    }

    private static void printWelcomeOptions() {
        System.out.println("\n\t Select search options:");
        System.out.println("\t\t * Press 1 to search Zendesk");
        System.out.println("\t\t * Press 2 to view a list of searchable fields");
        System.out.println("\t\t * Type 'quit' to exit");
    }

    private static void printSearchOptions() {
        System.out.println("Select 1) Users or 2) Tickets or 3) Organizations");
    }

    private static void exitWithMessage() {
        System.out.println("Thank you for using ZenDesk Search App.");
        System.exit(0);
    }
}
