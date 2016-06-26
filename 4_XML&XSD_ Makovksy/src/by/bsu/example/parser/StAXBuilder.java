package by.bsu.example.parser;

import by.bsu.example.entity.Deposit;
import by.bsu.example.entity.DepositOnDemand;
import by.bsu.example.entity.DepositUrgent;
import by.bsu.example.parser.client.DepositEnum;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Created by Lenovo on 06.04.2016.
 */
public class StAXBuilder extends BaseParser {
    private Set<Deposit> deposits;
    private XMLInputFactory inputFactory;
    public StAXBuilder(){
        inputFactory = XMLInputFactory.newInstance();
        deposits = new HashSet<Deposit>();
    }
    public Set<Deposit> getDeposits() {
        return deposits;
    }
    public void buildSetDeposits(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            deposits.clear();
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (DepositEnum.valueOf(name.toUpperCase()) == DepositEnum.DEPOSIT) {
                        Deposit st = buildDeposit(reader);
                        deposits.add(st);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            System.err.println("StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.err.println("File " + fileName + " not found! " + ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Impossible close file "+fileName+" : "+e);
            }
        }
    }
    private Deposit buildDeposit(XMLStreamReader reader) throws XMLStreamException {
        Deposit current;
        switch (reader.getAttributeValue(null, "type")) {
            case "urgent":
                current = new DepositUrgent();
                break;
            case "onDemand":
                current = new DepositOnDemand();
                break;
            default:
                current = new Deposit();
        }
        current.setType(reader.getAttributeValue(null, DepositEnum.TYPE.getValue()));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (DepositEnum.valueOf(name.toUpperCase())) {
                        case NAME:
                            current.setName(getXMLText(reader));
                            break;
                        case COUNTRY:
                            current.setCountry(getXMLText(reader));
                            break;
                        case DEPOSITOR:
                            current.setDepositor(getXMLText(reader));
                            break;
                        case ACCOUNTID:
                            current.setAccountId(Integer.parseInt(getXMLText(reader)));
                            break;
                        case DEPOSITAMOUNT:
                            current.setDepositAmount(Long.parseLong(getXMLText(reader)));
                            break;
                        case PROFITABILITY:
                            current.setProfitability(Short.parseShort(getXMLText(reader)));
                            break;
                        case CHECKACCOUNT:
                            ((DepositOnDemand)current).setCheckAccount(Integer.parseInt(getXMLText(reader)));
                            break;
                        case DEPOSITTIME:
                            ((DepositUrgent)current).setDepositTime(Integer.parseInt(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (DepositEnum.valueOf(name.toUpperCase()) == DepositEnum.DEPOSIT) {
                        return current;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Deposit");
    }
    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
