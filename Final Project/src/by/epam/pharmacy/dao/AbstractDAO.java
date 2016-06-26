package by.epam.pharmacy.dao;

import by.epam.pharmacy.exception.DAOException;
import by.epam.pharmacy.pool.ConnectionPool;
import by.epam.pharmacy.pool.ProxyConnector;

import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO <T extends Object> {
    protected ProxyConnector connection;
    public AbstractDAO(){
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }
    public abstract List<T> findAll() throws DAOException;
    public void closeConnection() {
            connection.close();
    }
}