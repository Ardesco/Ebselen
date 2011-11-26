/*
 * Copyright (c) 2010-2011 Ardesco Solutions - http://www.ardescosolutions.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

