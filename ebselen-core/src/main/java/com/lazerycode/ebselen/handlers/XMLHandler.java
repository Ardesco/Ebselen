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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class XMLHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(XMLHandler.class);
    private Document xmlDocument = null;
    private LocalNamespaceContext namespaceMappings = new LocalNamespaceContext();

    public XMLHandler(File absoluteFile) throws Exception {
        if (absoluteFile.exists()) {
            createDocumentAndCollectPrefixes(new InputSource(new FileReader(absoluteFile)), false);
        } else {
            throw new IOException("File does not exist!");
        }
    }

    public XMLHandler(File absoluteFile, boolean scanEntireDocumentForNamespaces) throws Exception {
        if (absoluteFile.exists()) {
            createDocumentAndCollectPrefixes(new InputSource(new FileReader(absoluteFile)), scanEntireDocumentForNamespaces);
        } else {
            throw new IOException("File does not exist!");
        }
    }

    public XMLHandler(String sourceXML) throws Exception {
        createDocumentAndCollectPrefixes(new InputSource(new StringReader(sourceXML.trim().replaceFirst("^([\\W]+)<", "<"))), false);
    }

    public XMLHandler(String sourceXML, boolean scanEntireDocumentForNamespaces) throws Exception {
        createDocumentAndCollectPrefixes(new InputSource(new StringReader(sourceXML.trim().replaceFirst("^([\\W]+)<", "<"))), scanEntireDocumentForNamespaces);
    }

    private void createDocumentAndCollectPrefixes(InputSource documentSource, boolean scanEntireDocument) throws Exception {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        this.xmlDocument = domFactory.newDocumentBuilder().parse(documentSource);
        this.namespaceMappings.findNamespaces(this.xmlDocument.getFirstChild(), scanEntireDocument);
    }

    class LocalNamespaceContext implements NamespaceContext {

        private final String defaultNamespace = "";
        private Map<String, String> prefixMappedToUri = new HashMap<String, String>();
        private Map<String, String> uriMappedToPrefix = new HashMap<String, String>();

        public void findNamespaces(Node node, boolean attributesOnly) {
            NamedNodeMap attributes = node.getAttributes();
            if (attributes == null) {
                return;
            }
            for (int currentAttribute = 0; currentAttribute < attributes.getLength(); currentAttribute++) {
                storeAttribute((Attr) attributes.item(currentAttribute));
            }
            if (!attributesOnly) {
                NodeList childNodes = node.getChildNodes();
                for (int child = 0; child < childNodes.getLength(); child++) {
                    if (childNodes.item(child).getNodeType() == Node.ELEMENT_NODE) {
                        findNamespaces(childNodes.item(child), false);
                    }
                }
            }
        }

        private void storeAttribute(Attr attribute) {
            if (attribute.getNamespaceURI() != null && attribute.getNamespaceURI().equals(XMLConstants.XMLNS_ATTRIBUTE_NS_URI)) {
                if (attribute.getNodeName().equals(XMLConstants.XMLNS_ATTRIBUTE)) {
                    addToContext(attribute.getNodeValue());
                } else {
                    addToContext(attribute.getLocalName(), attribute.getNodeValue());
                }
            }
        }

        private void addToContext(String prefix, String uri) {
            this.prefixMappedToUri.put(prefix, uri);
            this.uriMappedToPrefix.put(uri, prefix);
        }

        private void addToContext(String uri) {
            this.prefixMappedToUri.put(this.defaultNamespace, uri);
            this.uriMappedToPrefix.put(uri, this.defaultNamespace);
        }

        public String getNamespaceURI(String prefix) {
            if (prefix == null || prefix.equals(XMLConstants.DEFAULT_NS_PREFIX)) {
                return this.prefixMappedToUri.get(this.defaultNamespace);
            } else {
                return this.prefixMappedToUri.get(prefix);
            }
        }

        public String getPrefix(String namespaceURI) {
            return uriMappedToPrefix.get(namespaceURI);
        }

        public Iterator getPrefixes(String namespaceURI) {
            ArrayList<String> prefixes = new ArrayList<String>();
            for (String URI : this.uriMappedToPrefix.keySet()) {
                if (URI.equals(namespaceURI)) {
                    prefixes.add(URI);
                }
            }
            return prefixes.iterator();
        }
    }

    /**
     * Create an XPath Expression.
     *
     * @param xPathLocator XPath location to build the Expression from.
     * @return XPathExpression - Created Expression.
     * @throws XPathExpressionException
     */
    private XPathExpression constructAnXPathExpression(String xPathLocator) throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        xPath.setNamespaceContext(namespaceMappings);
        return xPath.compile(xPathLocator);
    }

    /**
     * Create an Element reference.
     *
     * @param locator - XPath location of the element.
     * @return Element - Element object referenced by the XPath locator.
     * @throws XPathExpressionException
     */
    private Element getElement(String locator) throws XPathExpressionException {
        return (Element) constructAnXPathExpression(locator).evaluate(this.xmlDocument, XPathConstants.NODE);
    }

    /**
     * Return the result of an XPath query on an XML Document in int format
     *
     * @param query - The XPath query
     * @return int - Result of the query
     * @throws Exception
     */
    public int performXPathQueryReturnInteger(String query) throws Exception {
        return Integer.parseInt(performXPathQueryReturnString(query));
    }

    /**
     * Return the result of an XPath query on an XML Document in String format
     *
     * @param query - The XPath query
     * @return String - Result of the query
     * @throws Exception
     */
    public String performXPathQueryReturnString(String query) throws Exception {
        return constructAnXPathExpression(query).evaluate(this.xmlDocument, XPathConstants.STRING).toString();
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
     * Return the current XML as a string
     *
     * @return String - Current XML
     * @throws Exception
     */
    public String returnXML() throws Exception {
        StringWriter xmlAsString = new StringWriter();
        TransformerFactory.newInstance().newTransformer().transform(new DOMSource(this.xmlDocument), new StreamResult(xmlAsString));
        return xmlAsString.toString().trim();
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
            TransformerFactory.newInstance().newTransformer().transform(source, output);
        } catch (TransformerConfigurationException Ex) {
            LOGGER.error(" Error creating file: " + Ex);
        } catch (TransformerException Ex) {
            LOGGER.error(" Error creating file: " + Ex);
        }
        outputFile.close();
        return outputFile.getAbsoluteFile();
    }
}