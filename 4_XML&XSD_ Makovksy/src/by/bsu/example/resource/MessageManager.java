package by.bsu.example.resource;
import java.util.ResourceBundle;
public class MessageManager {
    private ResourceBundle resourceBundle;

    public MessageManager(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("resources.messages", locale);
    }

    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}