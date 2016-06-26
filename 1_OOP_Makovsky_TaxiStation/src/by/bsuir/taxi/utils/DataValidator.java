package by.bsuir.taxi.utils;

/**
 * Created by Lenovo on 28.02.2016.
 */
public class DataValidator {
    private enum taxiTypes {
        CARGOTAXI,
        PASSENGERTAXI,
        CONVERTIBLE
    }

    public boolean validate(String className, int args[]) {
        if (checkClassName(className) == false) {
            return false;
        }
        if (checkData(className, args) == false) {
            return false;
        }
        return true;
    }

    private boolean checkClassName(String className) {
        for (taxiTypes types : taxiTypes.values()) {
            if (types.name().equals(className)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkData(String className, int args[]) {
        if (args[0] < 500 || args[0] > 1_000_00) {
            return false;
        }
        if (args[1] > 50) {
            return false;
        }
        if (className.equals("CARGOTAXI")) {
            if (args[2] < 1000) {
                return false;
            }
        } else if (className.equals("PASSENGERTAXI") || className.equals("CONVERTIBLE")) {
            if (args[2] > 30 || args[2] < 0) {
                return false;
            }
        }
        return true;
    }
}
