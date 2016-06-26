package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.DrugListDao;
import by.epam.pharmacy.dao.LoginInfoDAO;
import by.epam.pharmacy.entity.Drug;
import by.epam.pharmacy.entity.LoginInfo;
import by.epam.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by Lenovo on 05.05.2016.
 */
public class DrugListLogic {
    private final static Logger LOG = LogManager.getLogger(LoginLogic.class);

    public ArrayList<Drug> listDrugs() {
        ArrayList<Drug> drugs = new ArrayList<>();
        DrugListDao drugListDao = new DrugListDao();
        try {
            drugs = (ArrayList<Drug>) drugListDao.findAll();
        } catch (DAOException e) {
            LOG.error(e);
        }
        finally {
            drugListDao.closeConnection();
        }
        return drugs;
    }
}
