package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.DrugListDao;
import by.epam.pharmacy.entity.Drug;
import by.epam.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DrugSelectLogic {
    private final static Logger LOG = LogManager.getLogger(LoginLogic.class);

    public Drug find(String name) {
        Drug drug = null;
        DrugListDao drugListDao = new DrugListDao();
        try {
            drug = drugListDao.findDrug(name);
        } catch (DAOException e) {
            LOG.error(e);
        } finally {
            drugListDao.closeConnection();
        }
        return drug;
    }
}
