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

import java.util.*;
import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.*;
import org.w3c.dom.*;

public class XMLHandler {

    private Document xmlDocument = null;
    private Boolean xmlNamspace = false;
    private static final Logger LOGGER = LoggerFactory.getLogger(XMLHandler.class);
    //namespace stuff
    private final String defaultNamespace = "DEFAULT";
    private Map<String, String> prefix2Uri = new HashMap<String, String>();
    private Map<String, String> uri2Prefix = new HashMap<String, String>();
    //todo getter and setter to switch this
    private boolean toplevelOnly = true;

    /**
     * Constructor to generate an XML object from an existing XML file
     *
     * @param absoluteFile - File object to convert into XML object
     */
    public XMLHandler(File absoluteFile) throws Exception {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        this.xmlDocument = domFactory.newDocumentBuilder().parse(absoluteFile);
        XMLNamespaceResolver();
    }

    /**
     * Constructor to generate an XML object from a String
     *
     * @param sourceXML - Source XML to convert into XML an object
     */
    public XMLHandler(String sourceXML) throws Exception {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        this.xmlDocument = domFactory.newDocumentBuilder().parse(new InputSource(new StringReader(sourceXML)));
        XMLNamespaceResolver();
    }

    //TODO CHECK ALL BELOW
    public final void XMLNamespaceResolver() {
        examineNode(xmlDocument.getFirstChild(), toplevelOnly);
        LOGGER.debug("The list of the cached namespaces:");
        for (String key : prefix2Uri.keySet()) {
            LOGGER.debug("prefix " + key + ": uri " + prefix2Uri.get(key));
        }
    }

    /**
     * A single node is read, the namespace attributes are extracted and stored.
     *
     * @param node           to examine
     * @param attributesOnly - if true no recursion happens
     */
    private void examineNode(Node node, boolean attributesOnly) {
        NamedNodeMap attributes = node.getAttributes();
        if (attributes == null) {
            return;
        }
        for (int i = 0; i < attributes.getLength(); i++) {
            Node attribute = attributes.item(i);
            storeAttribute((Attr) attribute);
        }
        if (!attributesOnly) {
            NodeList childNodes = node.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node child = childNodes.item(i);
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    examineNode(child, false);
                }
            }
        }
    }

    /**
     * This method looks at an attribute and stores it, if it is a namespace
     * attribute.
     *
     * @param attribute to examine
     */
    private void storeAttribute(Attr attribute) {
        // examine the attributes in namespace xmlns
        if (attribute.getNamespaceURI() != null && attribute.getNamespaceURI().equals(XMLConstants.XMLNS_ATTRIBUTE_NS_URI)) {
            // Default namespace xmlns="uri goes here"
            if (attribute.getNodeName().equals(XMLConstants.XMLNS_ATTRIBUTE)) {
                putInCache(defaultNamespace, attribute.getNodeValue());
            } else {
                // The defined prefixes are stored here
                putInCache(attribute.getLocalName(), attribute.getNodeValue());
            }
        }

    }

    private void putInCache(String prefix, String uri) {
        prefix2Uri.put(prefix, uri);
        uri2Prefix.put(uri, prefix);
    }

    /**
     * This method is called by XPath. It returns the default namespace, if the
     * prefix is null or "".
     *
     * @param prefix to search for
     * @return uri
     */
    public String getNamespaceURI(String prefix) {
        if (prefix == null || prefix.equals(XMLConstants.DEFAULT_NS_PREFIX)) {
            return prefix2Uri.get(defaultNamespace);
        } else {
            return prefix2Uri.get(prefix);
        }
    }

    /**
     * This method is not needed in this context, but can be implemented in a
     * similar way.
     *
     * @param namespaceURI
     * @return
     */
    public String getPrefix(String namespaceURI) {
        return uri2Prefix.get(namespaceURI);
    }

    public Iterator getPrefixes(String namespaceURI) {
        // Not implemented
        return null;
    }

    //TODO CHECK ALL ABOVE

    /**
     * Create an XPath Expression.
     *
     * @param locator XPath location to build the Expression from.
     * @return XPathExpression - Created Expression.
     * @throws XPathExpressionException
     */
    private XPathExpression getExpression(String locator) throws XPathExpressionException {
        XPath xpath = XPathFactory.newInstance().newXPath();
        //todo not needed?        xpath.setNamespaceContext(new XMLNamespaceResolver(this.xmlDocument, xmlNamspace));
        return xpath.compile(locator);
    }

    /**
     * Create an Element reference.
     *
     * @param locator - XPath location of the element.
     * @return Element - Element object referenced by the XPath locator.
     * @throws XPathExpressionException
     */
    private Element getElement(String locator) throws XPathExpressionException {
        return (Element) getExpression(locator).evaluate(this.xmlDocument, XPathConstants.NODE);
    }

    /**
     * Add text to an element.
     *
     * @param text    - Text to add to the element.
     * @param locator - XPath location of the element.
     * @throws Exception
     */
    public void addTextToElement(String text, String locator) throws Exception {
        Element element = getElement(locator);
        Node textNode = xmlDocument.createTextNode(text);
        element.appendChild(textNode);
    }

    /**
     * Add a child element to an existing element (Will go to the end of the list)
     *
     * @param elementType - Type of child element to add (e.g. div)
     * @param locator     - XPath location of the element.
     * @throws Exception
     */
    public void addChildElement(String elementType, String locator) throws Exception {
        Element element = getElement(locator);
        Node childNode = xmlDocument.createElement(elementType);
        element.appendChild(childNode);
    }

    /**
     * Add an attribute to an element.
     *
     * @param attributeType - Attribute to add (e.g. class).
     * @param value         - Value to assign to the attribute.
     * @param locator       - XPath location of the attribute.
     * @throws Exception
     */
    public void addAttribute(String attributeType, String value, String locator) throws Exception {
        Element element = getElement(locator);
        element.setAttribute(attributeType, value);
    }

    /**
     * Return the result of an XPath query on an XML Document in int format
     *
     * @param query - The XPath query
     * @return int - Result of the query
     * @throws Exception
     */
    public int xQueryReturnInt(String query) throws Exception {
        return Integer.parseInt(xQuery(query));
    }

    /**
     * Return the result of an XPath query on an XML Document in String format
     *
     * @param query - The XPath query
     * @return String - Result of the query
     * @throws Exception
     */
    public String xQuery(String query) throws Exception {
        return getExpression(query).evaluate(this.xmlDocument, XPathConstants.STRING).toString();
    }

    /**
     * Write the current XML object to file.
     *
     * @param absoluteFileName - Absolute filename to write XML object to
     * @return String - Directory that file has been written to
     * @throws Exception
     */
    public String writeXMLFile(String absoluteFileName) throws Exception {
        if (this.xmlDocument == null) {
            LOGGER.error("The Document object is null, unable to generate a file!");
            return null;
        }
        Source source = new DOMSource(this.xmlDocument);
        FileHandler outputFile = new FileHandler(absoluteFileName, true);
        try {
            Result output = new StreamResult(outputFile.getWriteableFile());
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.transform(source, output);
        } catch (TransformerConfigurationException Ex) {
            LOGGER.error(" Error creating file: " + Ex);
        } catch (TransformerException Ex) {
            LOGGER.error(" Error creating file: " + Ex);
        }
        outputFile.close();
        return outputFile.getAbsoluteFile();
    }
}