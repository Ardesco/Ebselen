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

package com.lazerycode.ebselen.handlers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileHandler.class);
    private final String sepReg = "(\\\\|/)";
    private boolean createIfNotExist;
    private boolean appendToFile;
    private boolean fileIsWritable = false;
    private boolean fileIsReadable = false;
    private String filePath;
    private String fileName;
    private String fileExtension;
    private FileWriter writeableFile;
    private File currentFile;
    private OutputStream writableFileOutputStream;

    /**
     * @param absoluteFilename
     */
    public FileHandler(String absoluteFilename) {
        initialiseFile(absoluteFilename, false);
    }

    /**
     * @param absoluteFilename
     * @param value
     */
    public FileHandler(String absoluteFilename, boolean value) {
        initialiseFile(absoluteFilename, value);
    }

    public void initialiseFile(String absoluteFilename, boolean value) {
        setAbsoluteFilename(absoluteFilename);
        setCreateIfNotExist(value);
        setAppendToFile(false);
    }

    public final void setAbsoluteFilename(String value) {
        setFileName(value.replaceFirst("^.*" + sepReg, ""));
        setFilePath(value.substring(0, value.length() - this.fileName.length()));
    }

    public final void setFileName(String value) {
        if (value.matches(sepReg)) {
            LOGGER.error("The filename '{}' is not valid!", value);
            return;
        }
        this.fileName = value;
        String[] fileComponents = this.fileName.split("\\.");
        if (fileComponents.length > 1) {
            this.fileExtension = fileComponents[fileComponents.length - 1];
        } else {
            this.fileExtension = "";
        }
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getExtension() {
        return this.fileExtension;
    }

    private boolean isFileWriteable() {
        return this.fileIsWritable;
    }

    public final void setFilePath(String value) {
        String[] pathExploded = value.split(sepReg);
        String path = "";
        for (String pathSegment : pathExploded) {
            path += pathSegment + System.getProperty("file.separator");
        }
        this.filePath = path;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getAbsoluteFile() {
        return this.filePath + this.fileName;
    }

    public final void setCreateIfNotExist(boolean value) {
        this.createIfNotExist = value;
    }

    public boolean getCreateIfNotExist() {
        return this.createIfNotExist;
    }

    public final void setAppendToFile(boolean value) {
        this.appendToFile = value;
    }

    public boolean getAppendToFile() {
        return this.appendToFile;
    }

    public FileWriter getWriteableFile() throws Exception {
        if (!this.fileIsWritable) {
            this.openFileForWriting();
        }
        return this.writeableFile;
    }

    public OutputStream getWritableFileOutputStream() throws Exception {
        if (!this.fileIsWritable) {
            this.openFileForWriting();
        }
        return this.writableFileOutputStream;
    }

    public File getFile() throws Exception {
        if (!this.fileIsReadable) {
            this.openFile();
        }
        return this.currentFile;
    }

    private void openFile() throws Exception {
        File fileToOpen = new File(this.filePath + this.fileName);
        if (fileToOpen.exists()) {
            if (fileToOpen.canRead()) {
                this.currentFile = fileToOpen;
                this.fileIsReadable = true;
            } else {
                LOGGER.error("Unable to read '{}'", this.filePath + this.fileName);
                throw new IOException("Unable to read file " + this.filePath + this.fileName);
            }
        } else if (this.createIfNotExist) {
            File directory = new File(this.filePath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            fileToOpen.createNewFile();
            this.currentFile = fileToOpen;
        } else {
            LOGGER.error("'{}' does not exist!", this.filePath + this.fileName);
            throw new IOException(this.filePath + this.fileName + "does not exist!");
        }

    }

    private void openFileForWriting() throws Exception {
        if (this.fileIsReadable != true) {
            this.openFile();
        }
        if (this.fileIsWritable == false) {
            this.currentFile.setWritable(true);
            this.fileIsWritable = true;
        }
        this.writeableFile = new FileWriter(this.currentFile, this.appendToFile);
        this.writableFileOutputStream = new FileOutputStream(this.currentFile);
        this.fileIsWritable = true;
    }

    public void write(String value) throws Exception {
        if (!this.fileIsWritable) {
            this.openFileForWriting();
        }
        this.writeableFile.write(value);
    }

    public void close() throws Exception {
        if (this.writeableFile != null) {
            this.writeableFile.close();
        }
        if (writableFileOutputStream != null) {
            this.writableFileOutputStream.close();
            this.writeableFile = null;
        }
        this.fileIsWritable = false;
        this.currentFile = null;
        this.fileIsReadable = false;
    }

    /**
     * Copy the file to a specific location
     *
     * @param absoluteFileName - Target location for copy.
     * @return
     * @throws Exception
     */
    public boolean copyFileTo(String absoluteFileName) throws Exception {
        if (this.fileIsReadable != true) {
            this.openFile();
        }
        File fileDestination = new File(absoluteFileName);
        if (this.currentFile.exists()) {
            if (this.currentFile.canRead()) {
                try {
                    FileUtils.copyFile(this.currentFile, fileDestination);
                    return true;
                } catch (Exception Ex) {
                    LOGGER.warn("Failed to copy file to '{}'", absoluteFileName);
                    return false;
                }
            } else {
                LOGGER.error("Unable to read '{}'", this.filePath + this.fileName);
                throw new IOException("Unable to read file " + this.filePath + this.fileName);
            }
        } else {
            LOGGER.error("'{}' does not exist!", this.filePath + this.fileName);
            throw new IOException(this.filePath + this.fileName + "does not exist!");
        }
    }
}
