package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.DrugListDao;
import by.epam.pharmacy.entity.Drug;
import by.epam.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddDrugLogic {
    private final static Logger LOG = LogManager.getLogger(RegisterUserLogic.class);

    public boolean add(Drug drug) {
        boolean result = false;
        DrugListDao drugListDao = new DrugListDao();
        try {
            drugListDao.addDrug(drug);
            result = true;
        } catch (DAOException e) {
            LOG.error(e);
        } finally {
            drugListDao.closeConnection();
        }
        return result;
    }
}
