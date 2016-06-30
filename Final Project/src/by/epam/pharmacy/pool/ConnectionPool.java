package by.epam.pharmacy.pool;

import by.epam.pharmacy.resource.ConfigurationManager;
import com.mysql.jdbc.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private final int POOL_SIZE = 20;
    private final static String URL = "jdbc:mysql://localhost:3306/epam_project";
    private static final long CLOSE_TIMEOUT_SEC = 1;
    private ArrayBlockingQueue<ProxyConnector> connectionQueue;
    private static ConnectionPool instance;
    private static ReentrantLock lock = new ReentrantLock();
    private final static Logger LOG = LogManager.getLogger(ConnectionPool.class);
    private static AtomicBoolean isCreated = new AtomicBoolean(false);

    /**
     * Implementation of a connection pool to the database.
     */
    private ConnectionPool() {
        try {
            DriverManager.registerDriver(new Driver());
            connectionQueue = new ArrayBlockingQueue<ProxyConnector>(POOL_SIZE);

            Properties prop = new Properties();

            prop.put("user", ConfigurationManager.getProperty("db.conf.user"));
            prop.put("password", ConfigurationManager.getProperty("db.conf.password"));
            prop.put("autoReconnect", ConfigurationManager.getProperty("db.conf.autoReconnect"));
            prop.put("characterEncoding", ConfigurationManager.getProperty("db.conf.characterEncoding"));
            prop.put("useUnicode", ConfigurationManager.getProperty("db.conf.useUnicode"));
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(URL, prop);// create connection
                ProxyConnector proxyConnector = new ProxyConnector(connection);
                connectionQueue.offer(proxyConnector);
            }
        } catch (SQLException e) {
            LOG.fatal("Pool creation failed");
            throw new RuntimeException(e);
        }
    }

    public static ConnectionPool getInstance() {
        if (!isCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    isCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public ProxyConnector getConnection() {
        ProxyConnector connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            LOG.error(e);
        }
        return connection;
    }

    public void returnConnection(ProxyConnector connection) {
        connectionQueue.offer(connection);
    }

    @PreDestroy
    private void releasePool() {
        try {
            TimeUnit.SECONDS.sleep(CLOSE_TIMEOUT_SEC);
            for (ProxyConnector connection : connectionQueue) {
                connection.close();
                LOG.info("Close connect");
            }
        } catch (InterruptedException ex) {
            LOG.warn("Release connection exception", ex);
        }
    }

    public int getPoolSize() {
        return connectionQueue.size();
    }

}