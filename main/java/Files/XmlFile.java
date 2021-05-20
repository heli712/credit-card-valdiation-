package Files;

import card.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

import javax.xml.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import creditcard.*;


import  java.util.Date;

public class XmlFile extends Format {
    public ArrayList<Card> readFile(String path)  {
        AmercianExpressCard americanEx = new AmercianExpressCard();
        DiscoverCard discover = new DiscoverCard();
        MasterCard master = new MasterCard();
        VisaCard visa = new VisaCard();

        americanEx.identifyCard(discover);
        discover.identifyCard(master);
        master.identifyCard(visa);

        ArrayList<Card> creditcard = new ArrayList<>();
        System.out.println("Xml file");
        try {
            File myObj = new File(path);
            Scanner scanner = new Scanner(myObj);
            String text = scanner.useDelimiter("\\A").next();
            scanner.close();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = null;
            try
            {
            //Create DocumentBuilder with default configuration
                builder = factory.newDocumentBuilder();

            //Parse the content to Document object
                Document doc = builder.parse(new InputSource(new StringReader(text)));
                SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yy");
                NodeList cardinfo = doc.getElementsByTagName("row");
                for (int i = 0; i < cardinfo.getLength(); i++) {

                    Node nNode = cardinfo.item(i);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        String cardNumber = String.format("%.0f", Double.parseDouble(eElement.getElementsByTagName("CardNumber").item(0).getTextContent()));
                        Date expirationDate = dateFormat.parse(eElement.getElementsByTagName("ExpirationDate").item(0).getTextContent());
                        String nameOfCardHolder = eElement.getElementsByTagName("NameOfCardholder").item(0).getTextContent();

                        creditcard.add(americanEx.check(cardNumber, expirationDate, nameOfCardHolder));
                    }
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return creditcard;
    }

    public boolean writeFile(ArrayList<Card> creditcard, String input_path) {

        System.out.println("Writing to xml file...");
        File xmlFile = new File(input_path + "output.xml");

        try {
            //make an instance of DocumentBuilderFactory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db= dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element rootElement = doc.createElementNS("", "root");
            doc.appendChild(rootElement);

            //className objectName : arraylist
            for (Card cc : creditcard) {
                Element rowElement = doc.createElement("row");
                Element nodeCardNumber = doc.createElement("CardNumber");
                nodeCardNumber.appendChild(doc.createTextNode(cc.getNumber()));
                rowElement.appendChild(nodeCardNumber);
                Element nodeType = doc.createElement("Type");
                nodeType.appendChild(doc.createTextNode(cc.getcardType()));
                rowElement.appendChild(nodeType);
                Element errorMessage = doc.createElement("ERRORMessage");

                if (cc.getValidation()) {
                    errorMessage.appendChild(doc.createTextNode(""));
                } else {
                    errorMessage.appendChild(doc.createTextNode("ERROR:InvalidNumber"));
                }
                rowElement.appendChild(errorMessage);
                rootElement.appendChild(rowElement);
            }
            //create indent in xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(doc);
            StreamResult outputFile = new StreamResult(xmlFile);
            transformer.transform(domSource, outputFile);

        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}
