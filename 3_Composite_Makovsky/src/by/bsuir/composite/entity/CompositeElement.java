package by.bsuir.composite.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lenovo on 15.03.2016.
 */
public class CompositeElement implements Component {
    private ArrayList<Component> subComponents = new ArrayList<>();
    private CompositeType type;

    public CompositeElement(CompositeType type) {
        this.type = type;
    }

    public CompositeType getType() {
        return type;
    }

    public void add(Component component) {
        subComponents.add(component);
    }

    public ArrayList<Component> getSubComponents() {
        return subComponents;
    }

    @Override
    public String toString() {
        String result;
        switch (type) {
            case LEXEME:
                result = takeSubComponentString();
                break;
            case CLASS:
                result = takeSubComponentString();
                result += "}";
                break;
            default:
                result = takeSubComponentString();
        }
        return result;
    }

    private String takeSubComponentString() {
        String result = new String();
        for (Component component : subComponents) {
            result += component.toString() + " ";
        }
        result += "\n";
        return result;
    }
}
