package by.bsuir.taxi.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import by.bsuir.taxi.app.cars.Car;
import by.bsuir.taxi.exception.FileParseException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Created by Lenovo on 28.02.2016.
 */
public class TaxiParser {
    private Document doc;
    private TaxiFactory taxiFactory;
    private DataValidator validator;
    private final static Logger LOG = LogManager.getRootLogger();

    public TaxiParser() {
        taxiFactory = new TaxiFactory();
        validator = new DataValidator();
    }

    private void fileOpener(String fileName) {
        try {
            File inputFile = new File(fileName);
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOG.error(e);
        }
    }

    public ArrayList<Car> parseFile(String fileName) {
        fileOpener(fileName);
        NodeList nList = doc.getElementsByTagName("car");
        Car car;
        boolean isValid;
        String className;
        ArrayList<Car> carList = new ArrayList<>();
        int[] args = new int[3];
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nNode;
                className = element.getAttribute("name");
                if (!readArgs(args, element, className)) {
                    continue;
                }
                isValid = validator.validate(className, args);
                if (isValid == true) {
                    car = taxiFactory.makeTaxi(className, args);
                    carList.add(car);
                }
            }
        }
        return carList;
    }

    private boolean readArgs(int[] args, Element element, String className) {
        try {
            Node value;


            value = element.getElementsByTagName("price").item(0);
            if (value == null) {
                throw new FileParseException();
            }
            args[0] = Integer.parseInt(value.getTextContent());


            value = element.getElementsByTagName("fuelConsumption").item(0);
            if (value == null) {
                return false;
            }
            args[1] = Integer.parseInt(value.getTextContent());


            if (className.equals("CARGOTAXI")) {
                value = element.getElementsByTagName("load").item(0);
                if (value == null) {
                    throw new FileParseException();
                }
                args[2] = Integer.parseInt(value.getTextContent());
            } else if (className.equals("PASSENGERTAXI") || className.equals("CONVERTIBLE")) {
                value = element.getElementsByTagName("seats").item(0);
                if (value == null) {
                    throw new FileParseException();
                }
                args[2] = Integer.parseInt(value.getTextContent());
            } else {
                throw new FileParseException();
            }


        } catch (NumberFormatException | FileParseException e) {
            LOG.warn(e);
            return false;
        }
        return true;
    }
}

