package by.bsuir.composite.activity;

import by.bsuir.composite.entity.CompositeElement;
import by.bsuir.composite.entity.CompositeType;

import java.util.ArrayList;

/**
 * Created by Lenovo on 15.03.2016.
 */
public abstract class AbstractHandler {
    protected CompositeElement element;
    private CompositeType type;
    protected ArrayList<AbstractHandler> listOfSuccessors = new ArrayList<>();

    public void setType(CompositeType type) {
        this.type = type;
    }

    abstract public void parse(String text);

    public CompositeElement chain(String text) {
        element = new CompositeElement(type);
        parse(text);
        return element;
    }

    public void addSuccessor(AbstractHandler successor) {
        listOfSuccessors.add(successor);
    }
}
