package by.bsu.example.parser;

import by.bsu.example.entity.Deposit;
import by.bsu.example.servlet.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

/**
 * Created by Lenovo on 06.04.2016.
 */
public class SAXBuilder extends BaseParser {
    private Set<Deposit> deposits;
    private DepositHandler sh;
    private XMLReader reader;
    private final static Logger LOG = LogManager.getLogger(Controller.class);
    public SAXBuilder() {
        sh = new DepositHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(sh);
        } catch (SAXException e) {
            LOG.error("SAX parser error: " + e);
        }
    }
    public Set<Deposit> getDeposits() {
        return deposits;
    }
    public void buildSetDeposits(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            LOG.error("SAX parser error: " + e);
        } catch (IOException e) {
            LOG.error("I/O error: " + e);
        }
        deposits = sh.getDeposits();
    }
}
