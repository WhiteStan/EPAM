package by.epam.pharmacy.exception;

/**
 * Created by Lenovo on 22.04.2016.
 */
public class ServiceLogicException extends Exception {
    public ServiceLogicException(){
        super();
    }
    public ServiceLogicException(Throwable e){
        super(e);
    }
    public ServiceLogicException(String message){
        super(message);
    }
}
