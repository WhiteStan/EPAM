package by.bsuir.port.exception;

/**
 * Created by Lenovo on 15.03.2016.
 */
public class CargoOverflowException extends Exception {
    public CargoOverflowException()
    {
    }
    public CargoOverflowException(String message)
    {
        super(message);
    }
    public CargoOverflowException(Throwable cause)
    {
        super(cause);
    }
    public CargoOverflowException(String message, Throwable cause)
    {
        super(message, cause);
    }
    public CargoOverflowException(String message, Throwable cause,
                           boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
