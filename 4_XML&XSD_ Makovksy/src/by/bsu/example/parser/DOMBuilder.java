package by.bsu.example.parser;

import by.bsu.example.entity.Deposit;
import by.bsu.example.entity.DepositOnDemand;
import by.bsu.example.entity.DepositUrgent;
import by.bsu.example.servlet.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lenovo on 06.04.2016.
 */
public class DOMBuilder extends BaseParser{
    private Set<Deposit> deposits;
    private DocumentBuilder docBuilder;
    private final static Logger LOG = LogManager.getLogger(Controller.class);
    public DOMBuilder() {
        this.deposits = new HashSet<Deposit>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOG.error("Parser configuration error: " + e);
        }
    }

    public  void buildSetDeposits(String fileName){
        Document doc = null;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList depositsList = root.getElementsByTagName("deposit");
            deposits.clear();
            for (int i = 0; i < depositsList.getLength(); i++) {
                Element depositElement = (Element) depositsList.item(i);
                Deposit deposit = buildDeposit(depositElement);
                deposits.add(deposit);
            }
        } catch (IOException e) {
            LOG.error("File error or I/O error: " + e);
        } catch (SAXException e) {
            LOG.error("Parsing failure: " + e);
        }
    }
    public Set<Deposit> getDeposits() {
        return deposits;
    }
    private Deposit buildDeposit(Element depositElement) {
        Deposit current;
        switch (depositElement.getAttribute("type")){
            case "urgent":
                current = new DepositUrgent();
                ((DepositUrgent)current).setDepositTime(Integer.parseInt(getElementTextContent(depositElement, "depositTime")));
                break;
            case "onDemand":
                current = new DepositOnDemand();
                ((DepositOnDemand)current).setCheckAccount(Integer.parseInt(getElementTextContent(depositElement, "checkAccount")));
                break;
            default:
                current = new Deposit();
        }
        current.setType(depositElement.getAttribute("type"));
        current.setName(getElementTextContent(depositElement, "name"));
        current.setCountry(getElementTextContent(depositElement, "country"));
        current.setDepositor(getElementTextContent(depositElement, "depositor"));
        current.setAccountId(Integer.parseInt(getElementTextContent(depositElement, "accountId")));
        current.setDepositAmount(Long.parseLong(getElementTextContent(depositElement, "depositAmount")));
        current.setProfitability(Short.parseShort(getElementTextContent(depositElement, "profitability")));
        return current;
    }
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
