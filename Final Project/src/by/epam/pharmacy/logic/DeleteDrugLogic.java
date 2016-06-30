package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.DrugListDao;
import by.epam.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteDrugLogic {
    private final static Logger LOG = LogManager.getLogger(LoginLogic.class);

    public boolean delete(String name) {
        boolean result = false;
        DrugListDao drugListDao = new DrugListDao();
        try {
            result = drugListDao.deleteDrug(name);
        } catch (DAOException e) {
            LOG.error(e);
        } finally {
            drugListDao.closeConnection();
        }
        return result;
    }
}
