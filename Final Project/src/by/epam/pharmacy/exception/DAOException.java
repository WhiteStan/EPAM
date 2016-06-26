package by.epam.pharmacy.exception;

/**
 * Created by Lenovo on 14.04.2016.
 */
public class DAOException extends Exception {
    public DAOException(){
        super();
    }
    public DAOException(Throwable e){
        super(e);
    }
    public DAOException(String message){
        super(message);
    }
    public DAOException(String message, Throwable cause){
        super(message, cause);
    }
}
