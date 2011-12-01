package com.lazerycode.ebselen.handlers.xmlhandler;

import com.lazerycode.ebselen.handlers.XMLHandler;
import org.junit.Test;

import java.io.File;
import java.net.URI;
import java.net.URL;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class XMLHandlerTest {

    private final URL normalXMLFile = this.getClass().getResource("/xml/normal.xml");
    private final URL namespaceXMLFile = this.getClass().getResource("/xml/namespace.xml");
    private final String cr = System.getProperty("line.separator");

    @Test
    public void performXPathQueryWithNormalXML() throws Exception {
        XMLHandler normal = new XMLHandler(new File(new URI(normalXMLFile.toExternalForm())));
        assertThat(normal.performXPathQueryReturnInteger("count(//fruit)"), is(equalTo(2)));
    }

    @Test
    public void performXPathQueryWithNamespaceXML() throws Exception {
        XMLHandler namespace = new XMLHandler(new File(new URI(namespaceXMLFile.toExternalForm())));
        assertThat(namespace.performXPathQueryReturnInteger("count(//atom:link)"), is(equalTo(2)));
        assertThat(namespace.performXPathQueryReturnInteger("count(//link)"), is(equalTo(3)));
    }

    @Test
    public void returnXMLAsString() throws Exception {
        XMLHandler normal = new XMLHandler(new File(new URI(normalXMLFile.toExternalForm())));
        String XMLResult = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>" + cr +
                "    <foo>" + cr +
                "        <food>" + cr +
                "            <fruit>Apple</fruit>" + cr +
                "            <fruit>Banana</fruit>" + cr +
                "        </food>" + cr +
                "    </foo>" + cr +
                "</root>";
        assertThat(normal.returnXML(), is(equalTo(XMLResult)));
    }

    @Test
    public void addTextToElement() throws Exception {
        XMLHandler textBased = new XMLHandler("<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><foo></foo></root>");
        textBased.addTextToElement("Some Text", "/root/foo");
        assertThat(textBased.returnXML(), is(equalTo("<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><foo>Some Text</foo></root>")));
    }

    @Test
    public void addChildElement() throws Exception {
        XMLHandler textBased = new XMLHandler("<?xml version=\"1.0\" encoding=\"UTF-8\"?><root></root>");
        textBased.addChildElement("foo", "/root");
        assertThat(textBased.returnXML(), is(equalTo("<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><foo/></root>")));
    }

    @Test
    public void addAttributeToElement() throws Exception {
        XMLHandler textBased = new XMLHandler("<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><bar/></root>");
        textBased.addAttribute("element", "foo", "/root/bar");
        assertThat(textBased.returnXML(), is(equalTo("<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><bar element=\"foo\"/></root>")));
    }
}
