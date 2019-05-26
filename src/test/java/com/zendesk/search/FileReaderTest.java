package com.zendesk.search;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class FileReaderTest {

    @Test
    public void testClassCreation() {
        InputFileType user = InputFileFactory.getInputFileInstance(1);
        assertThat(user, instanceOf(User.class));

        InputFileType test1 = InputFileFactory.getInputFileInstance(100);
        assert(test1 == null);

        // File Not found exception caught
        InputFileType test2 = new MockFileType();
        test2.readFile();
        assert(test2.jsonArray == null);
    }
}

class MockFileType extends InputFileType {
    private static final String FILE = "testingFile.json";

    @Override
    public void readFile() {
        this.jsonArray = reader.readInputFile(FILE);
        super.readFile();
    }


}
