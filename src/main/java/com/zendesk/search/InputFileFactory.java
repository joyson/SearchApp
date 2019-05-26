package com.zendesk.search;

/**
 * Factory class that creates instances of different file types.
 */
public class InputFileFactory {

    public static InputFileType getInputFileInstance(int fileType) {
        switch (fileType) {
            case 1: return new User();
            case 2: return new Tickets();
            case 3: return new Organization();
            default: return null;
        }
    }
}
