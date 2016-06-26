package test.by.epam.pharmacy.pool;

import by.epam.pharmacy.pool.ConnectionPool;
import by.epam.pharmacy.pool.ProxyConnector;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by Lenovo on 19.06.2016.
 */
public class ConnectionPoolTest {
    @Test
    public void getConnectionTest(){
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        ProxyConnector connection = connectionPool.getConnection();
        assertEquals(19, connectionPool.getPoolSize());
    }

    @Test
    public void releaseConnectionTest(){
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        ProxyConnector connection = connectionPool.getConnection();
        connection.close();
        assertEquals(20, connectionPool.getPoolSize());
    }

}
