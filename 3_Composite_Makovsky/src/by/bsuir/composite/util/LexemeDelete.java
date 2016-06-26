package by.bsuir.composite.util;

import by.bsuir.composite.entity.Component;
import by.bsuir.composite.entity.CompositeElement;
import by.bsuir.composite.entity.CompositeType;
import by.bsuir.composite.entity.Leaf;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by Lenovo on 29.03.2016.
 */
public class LexemeDelete {
    private String fileName;
    private char letter;
    private int length;
    private final static Logger LOG = LogManager.getRootLogger();

    public LexemeDelete(String fileName) {
        this.fileName = fileName;
    }

    public void delete(Component component, char letter, int length) {
        this.letter = letter;
        this.length = length;
        findLexeme(component);
        if (!CompositeWriter.saveFile(fileName, component)) {
            LOG.warn("Results wasn't saved");
        }
    }

    private void findLexeme(Component component) {
        if (component.getType() == CompositeType.LEXEME) {
            doDelete(component);
        } else if (component.getType() != CompositeType.LEXEME) {
            checkAllComponents(component);
        }
    }

    private void doDelete(Component component) {
        ArrayList<Component> components = ((CompositeElement) component).getSubComponents();
        for (int i = 0; i < components.size(); i++) {
            if (((Leaf) components.get(i)).getContent().charAt(0) == letter &&
                    ((Leaf) components.get(i)).getContent().length() == length) {
                components.remove(i);
            }
        }
    }

    private void checkAllComponents(Component component) {
        CompositeElement element = (CompositeElement) component;
        for (Component innerComponent : element.getSubComponents()) {
            findLexeme(innerComponent);
        }
    }
}
