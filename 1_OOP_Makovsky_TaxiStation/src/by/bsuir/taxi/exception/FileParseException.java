package by.bsuir.taxi.exception;

import java.io.File;

/**
 * Created by Lenovo on 02.03.2016.
 */
public class FileParseException extends Exception {
    public FileParseException() {
        super();
    }

    public FileParseException(Exception e) {
        super(e);
    }

    public FileParseException(String message) {
        super(message);
    }
}
