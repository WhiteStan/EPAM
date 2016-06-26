package by.epam.pharmacy.resource;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    private ResourceBundle resourceBundle;

    public MessageManager(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("resources.pagecontent", locale);
    }

    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}