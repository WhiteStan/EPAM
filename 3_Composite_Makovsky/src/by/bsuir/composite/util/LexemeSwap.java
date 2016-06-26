package by.bsuir.composite.util;

import by.bsuir.composite.entity.Component;
import by.bsuir.composite.entity.CompositeElement;
import by.bsuir.composite.entity.CompositeType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Collections;

/**
 * Created by Lenovo on 27.03.2016.
 */
public class LexemeSwap {
    private String fileName;
    private final static Logger LOG = LogManager.getRootLogger();

    public LexemeSwap(String fileName) {
        this.fileName = fileName;
    }

    public void swap(Component component) {
        if (component.getType() == CompositeType.OPERATOR) {
            for (Component innerComponent : ((CompositeElement) component).getSubComponents()) {
                doSwap((CompositeElement) innerComponent);
            }
        } else if (component.getType() != CompositeType.LEXEME) {
            checkAllComponents(component);
        }
        if (!CompositeWriter.saveFile(fileName, component)) {
            LOG.warn("Results wasn't saved");
        }
    }

    private void checkAllComponents(Component component) {
        CompositeElement element = (CompositeElement) component;
        for (Component innerComponent : element.getSubComponents()) {
            swap(innerComponent);
        }
    }

    private void doSwap(CompositeElement component) {
        if (component.getSubComponents().size() != 0) {
            Collections.swap(component.getSubComponents(), 0, component.getSubComponents().size() - 1);
        }
    }

}
