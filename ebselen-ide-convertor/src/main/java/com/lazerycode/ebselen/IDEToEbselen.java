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

package com.lazerycode.ebselen;

import com.lazerycode.ebselen.handlers.FileHandler;
import com.lazerycode.ebselen.handlers.XMLHandler;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IDEToEbselen {

    public static final Logger LOGGER = LoggerFactory.getLogger(IDEToEbselen.class);
    private boolean walkTree;
    private String startPoint;
    private String testCode = "";
    private ArrayList<String> fileList = new ArrayList<String>();
    private CreateTestCode codeGenerator = new CreateTestCode();
    private String templateLocation = "ebselen-ide-convertor/src/main/java/com/lazerycode/ebselen/templates/template.jav"; //.replaceAll("/", File.separator);
    private String conversionLocation = "ebselen-test/src/test/java/com/lazerycode/ebselen/website/convertedfromide"; //.replaceAll("/", File.separator);

    public IDEToEbselen(String value, boolean doWalkTree) throws Exception {
        walkTree = doWalkTree;
        startPoint = value;
        createFileList();
        convertFileList();
    }

    private void addToTestCode(String value) {
        testCode += value + ";\n";
    }

    /**
     * Cleans the relevant file and generates a valid XML file ready for processing to Sel 2 java File.
     *
     * @param absoluteFilename - name of the file to convert.
     * @return String - location of the converted file.
     */
    public String convertToXML(String absoluteFilename) throws Exception {
        FileHandler fromSelIDE = new FileHandler(absoluteFilename);
        FileHandler toXML = new FileHandler(System.getProperty("java.io.tmpdir") + File.separator + fromSelIDE.getFileName() + ".xml", true);
        if (fromSelIDE.getFile().isDirectory()) {
            LOGGER.error("Cannot convert directory {} into a Selenium Test!", fromSelIDE.getFileName());
            return null;
        }
        //Clean up html so that we can read it as XML properly
        HtmlCleaner cleaner = new HtmlCleaner();
        CleanerProperties XMLPrefs = cleaner.getProperties();
        XMLPrefs.setUseEmptyElementTags(true);
        XMLPrefs.setTranslateSpecialEntities(true);
        XMLPrefs.setTransResCharsToNCR(true);
        XMLPrefs.setOmitComments(true);
        XMLPrefs.setOmitComments(true);
        XMLPrefs.setOmitDoctypeDeclaration(true);
        XMLPrefs.setNamespacesAware(false);
        TagNode tagNode = new HtmlCleaner(XMLPrefs).clean(fromSelIDE.getFile());
        new PrettyXmlSerializer(XMLPrefs).writeToStream(tagNode, toXML.getWritableFileOutputStream(), "utf-8");
        toXML.close();
        return toXML.getAbsoluteFile();
    }

    /**
     * Traverse directory and create a list of all the files that need to be converted
     */
    public void createFileList() throws Exception {
        FileHandler checkFileType = new FileHandler(startPoint);
        if (!checkFileType.getFile().isDirectory()) {
            LOGGER.error("The file '{}' is not a directory, will only try to convert this file...", checkFileType.getFileName());
            if (checkFileType.getFile().isFile()) {
                fileList.add(checkFileType.getFilePath() + checkFileType.getFileName());
            }
            checkFileType.close();
            return;
        }
        checkFileType.close();
        LOGGER.error("Scanning all files in the directory '{}'...", checkFileType.getFileName());
        ArrayList scanList = new ArrayList();
        scanList.add(startPoint);
        while (scanList.size() > 0) {
            FileHandler directoryToScan = new FileHandler(scanList.get(0).toString());
            File[] listOfFiles = directoryToScan.getFile().listFiles();
            for (File currentFile : listOfFiles) {
                FileHandler examineFile = new FileHandler(currentFile.getAbsolutePath());
                if (!examineFile.getExtension().equals("java")) {
                    if (examineFile.getFile().isFile()) {
                        fileList.add(examineFile.getFilePath() + examineFile.getFileName());
                    } else if (examineFile.getFile().isDirectory()) {
                        if (walkTree) {
                            scanList.add(currentFile);
                        }
                    }
                }
                examineFile.close();
            }
            directoryToScan.close();
            scanList.remove(0);
        }
    }

    /**
     * Iterate through a list of files converting them to Ebselen implementation tests
     *
     * @throws Exception
     */
    public void convertFileList() throws Exception {
        for (String file : fileList) {
            generateJavaFile(generateTestCode(file));
        }
    }

    /**
     * Reads in a sky.sns.selenium IDE file and creates Sky Selenium format test code
     *
     * @param filename - Selenium IDE file to convert
     * @return Name of the Selenium IDE file
     * @throws Exception
     */
    public String generateTestCode(String filename) throws Exception {
        FileHandler convertFrom = new FileHandler(convertToXML(filename));
        XMLHandler seleniumXMLFile = new XMLHandler(convertFrom.getFile());
        int commandCount = seleniumXMLFile.performXPathQueryReturnInteger("count(/html/body/table/tbody/tr)");
        for (int i = 1; i <= commandCount; i++) {
            String command = "";
            String target = "";
            String value = "";
            try {
                command = seleniumXMLFile.performXPathQueryReturnString("//table/tbody/tr[" + i + "]/td[1]");
                target = seleniumXMLFile.performXPathQueryReturnString("/html/body/table/tbody/tr[" + i + "]/td[2]");
                value = seleniumXMLFile.performXPathQueryReturnString("/html/body/table/tbody/tr[" + i + "]/td[3]");
            } catch (Exception Ex) {
                LOGGER.warn("Invalid command '{}' found", command);
            }
            addToTestCode(codeGenerator.convertCommandToEbselenCode(command, target, value));
        }
        convertFrom.close();
        return convertFrom.getFileName().split("\\.")[0];
    }

    /**
     * Writes Sky Selenium format test code into a Java file ready for tests to be run
     *
     * @param name - Name of the test
     * @throws Exception
     */
    public void generateJavaFile(String name) throws Exception {
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        Template ebselenTemplate = ve.getTemplate(templateLocation);
        VelocityContext context = new VelocityContext();
        context.put("template", name);
        context.put("templateclass", name + ".class");
        context.put("testname", name);
        context.put("testdata", testCode);
        FileHandler convertedFile = new FileHandler(conversionLocation + File.separator + name + ".java", true);
        StringWriter writer = new StringWriter();
        ebselenTemplate.merge(context, writer);
        convertedFile.write(writer.toString());
        convertedFile.close();
        LOGGER.info("Selenium IDE test converted and saved as '" + convertedFile.getFilePath() + convertedFile.getFileName() + "'");
    }

    public static void main(String[] args) throws Exception {
        boolean walkDirectories = false;
        if (null == args || args.length < 1) {
            LOGGER.error("Invalid argument set passed!\n" +
                    "Arguments required are:\n\n" +
                    "[0] String filename - File or directory full of files to convert.\n" +
                    "[1] Boolean true/false - if above is a directory traverse child directories as well.\n" +
                    "\n");
            System.exit(1);
        } else {
            if (args.length > 2) {
                LOGGER.info("More than 2 arguments detected, the first two will be used and the rest will be ignored.");
            }
            if (args.length > 1) {
                walkDirectories = Boolean.parseBoolean(args[1].toString());
            }
            new IDEToEbselen(args[0], walkDirectories);
        }
    }
}
