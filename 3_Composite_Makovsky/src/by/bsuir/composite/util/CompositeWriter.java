package by.bsuir.composite.util;

import by.bsuir.composite.entity.Component;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Collection;
import java.util.Scanner;

/**
 * Created by Lenovo on 23.03.2016.
 */
public class CompositeWriter {
    private static Logger logger = Logger.getRootLogger();

    private CompositeWriter() {
    }

    public static boolean saveFile(String fileName, Component component) {
        boolean result = false;
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(fileName))))) {
            writer.print(component.toString());
            result = true;
        } catch (IOException ex) {
            logger.error("Write file error", ex);
        }

        return result;
    }

    public static boolean saveFile(String fileName, Collection<? extends Component> components) {
        boolean result = false;
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(fileName))))) {
            for (Component component : components) {
                writer.println(component.toString());
            }
            result = true;
        } catch (IOException ex) {
            logger.error("Write file error", ex);
        }

        return result;
    }

}
