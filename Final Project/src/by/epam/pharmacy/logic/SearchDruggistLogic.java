package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.DrugListDao;
import by.epam.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Lenovo on 14.06.2016.
 */
public class SearchDruggistLogic {
    private final static Logger LOG = LogManager.getLogger(LoginLogic.class);

    public boolean search(String name) {
        boolean result = false;
        DrugListDao drugListDao = new DrugListDao();
        try {
            result = drugListDao.deleteDrug(name);
        } catch (DAOException e) {
            LOG.error(e);
        }
        finally {
            drugListDao.closeConnection();
        }
        return result;
    }
}
