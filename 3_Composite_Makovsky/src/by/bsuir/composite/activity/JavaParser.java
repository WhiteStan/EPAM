package by.bsuir.composite.activity;

import by.bsuir.composite.entity.CompositeElement;
import by.bsuir.composite.entity.CompositeType;
import by.bsuir.composite.util.CompositeWriter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Lenovo on 15.03.2016.
 */
public class JavaParser {
    private final static Logger LOG = LogManager.getRootLogger();
    private CompositeElement element;

    public void parse(String fileName) {
        String content = readFile(fileName);
        TextParserHandler textParserHandler = new TextParserHandler();
        element = textParserHandler.chain(content);
    }

    private String readFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (Scanner fileInput = new Scanner(new File(fileName))) {
            while (fileInput.hasNextLine()) {
                builder.append(fileInput.nextLine());
            }
        } catch (IOException ex) {
            LOG.fatal("Read file error", ex);
            throw new RuntimeException("Read file error");
        }
        return builder.toString();
    }

    public CompositeElement getElement() {
        return element;
    }

    public void assembleText(String fileName) {
        if (!CompositeWriter.saveFile(fileName, element)) {
            LOG.warn("Results wasn't saved");
        }
    }
}
