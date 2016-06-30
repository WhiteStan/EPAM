package by.epam.pharmacy.dao;

import by.epam.pharmacy.exception.DAOException;
import by.epam.pharmacy.pool.ConnectionPool;
import by.epam.pharmacy.pool.ProxyConnector;

import java.util.List;

public abstract class AbstractDAO<T extends Object> {
    protected ProxyConnector connection;

    public AbstractDAO() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }

    /**
     * @return the {@link List} object which contains information about all entities
     * @throws DAOException if a SQLException occurred
     */
    public abstract List<T> findAll() throws DAOException;

    /**
     * Closes connection with database
     */
    public void closeConnection() {
        connection.close();
    }
}