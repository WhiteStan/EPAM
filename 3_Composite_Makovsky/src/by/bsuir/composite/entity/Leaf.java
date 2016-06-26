package by.bsuir.composite.entity;

/**
 * Created by Lenovo on 15.03.2016.
 */
public class Leaf implements Component {
    private String content;
    private CompositeType type;

    public Leaf(String content, CompositeType type) {
        this.type = type;
        this.content = content;
    }

    public CompositeType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        String result = getContent().toString();

        return result;
    }
}
