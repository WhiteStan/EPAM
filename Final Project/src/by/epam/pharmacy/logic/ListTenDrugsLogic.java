package by.epam.pharmacy.logic;

import by.epam.pharmacy.dao.DrugListDao;
import by.epam.pharmacy.entity.Drug;
import by.epam.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by Lenovo on 12.06.2016.
 */
public class ListTenDrugsLogic {
    private final static Logger LOG = LogManager.getLogger(LoginLogic.class);

    public ArrayList<Drug> find(int pageNum) {
        ArrayList<Drug> drugs = new ArrayList<>();
        DrugListDao drugListDao = new DrugListDao();
        int startRow = (pageNum-1)*10;
        int lastRow = (pageNum-1)*10 + 10;
        try {
            drugs = (ArrayList<Drug>) drugListDao.findTenDrugs(startRow, lastRow);
        } catch (DAOException e) {
            LOG.error(e);
        }
        finally {
            drugListDao.closeConnection();
        }
        return drugs;
    }
}
