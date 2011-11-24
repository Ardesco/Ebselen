package com.lazerycode.ebselen.handlers.filehandler;

import com.lazerycode.ebselen.handlers.FileHandler;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

public class FileHandlerTest {

    private String randomFilename() {
        return "/tmp/" + UUID.randomUUID().toString().replaceAll("-", "") + ".txt";
    }

    @Test(expected = IOException.class)
    public void ifFileDoesNotExistItIsNotCreatedByDefault() throws Exception {
        FileHandler myFile = new FileHandler(randomFilename());
        myFile.getFile();
    }

    @Test
    public void checkThatNewlyCreatedFileCanBeWrittenTo() throws Exception {
        String thisFilename = randomFilename();
        FileHandler myFile = new FileHandler(thisFilename, true);
        myFile.write("This file can be written to.");
        myFile.close();
    }
}

