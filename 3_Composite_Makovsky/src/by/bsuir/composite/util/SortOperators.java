package by.bsuir.composite.util;

import by.bsuir.composite.entity.Component;
import by.bsuir.composite.entity.CompositeElement;
import by.bsuir.composite.entity.CompositeType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Lenovo on 27.03.2016.
 */
public class SortOperators {
    private ArrayList<Component> operatorList = new ArrayList<>();
    private final static Logger LOG = LogManager.getRootLogger();
    private String fileName;

    public SortOperators(String fileName) {
        this.fileName = fileName;
    }

    public void sort(Component component) {
        findOperators(component);
        Collections.sort(operatorList, new OperatorComparator());
        if (!CompositeWriter.saveFile(fileName, operatorList)) {
            LOG.warn("Results wasn't saved");
        }
    }

    private void findOperators(Component component) {
        if (component.getType() == CompositeType.OPERATOR) {
            for (Component innerComponent : ((CompositeElement) component).getSubComponents())
                operatorList.add(innerComponent);
        } else if (component.getType() != CompositeType.LEXEME) {
            checkAllComponents(component);
        }
    }

    private void checkAllComponents(Component component) {
        CompositeElement element = (CompositeElement) component;
        for (Component innerComponent : element.getSubComponents()) {
            findOperators(innerComponent);
        }
    }

    private class OperatorComparator implements Comparator<Component> {
        @Override
        public int compare(Component firstElement, Component secondElement) {
            int firstSize = ((CompositeElement) firstElement).getSubComponents().size();
            int secondSize = ((CompositeElement) secondElement).getSubComponents().size();
            return firstSize < secondSize ? -1 : firstSize == secondSize ? 0 : 1;
        }
    }
}
