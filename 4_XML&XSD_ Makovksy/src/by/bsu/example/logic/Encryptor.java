package by.bsu.example.logic;

import by.bsu.example.servlet.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Lenovo on 10.04.2016.
 */
public class Encryptor {
    private final static Logger LOG = LogManager.getLogger(Controller.class);

    public static String getHash(String str) {
        String s2 = new String();
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(str.getBytes("utf-8"));
            s2  = new BigInteger(1, m.digest()).toString(16);
        }
        catch (NoSuchAlgorithmException e)
        {
            LOG.info(e);
        }
        catch(UnsupportedEncodingException e) {
            LOG.info(e);
        }
        StringBuilder sb = new StringBuilder(32);
        for (int i = 0, count = 32 - s2.length(); i < count; i++) {
            sb.append("0");
        }
        return sb.append(s2).toString();
    }
}
