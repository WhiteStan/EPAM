package by.epam.pharmacy.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class whose method create MD5 hash code by string
 */
public class Encryptor {
    private final static Logger LOG = LogManager.getLogger(Encryptor.class);

    /**
     * Returns a string which contains MD5 hash code which was created by string which was passed as parameter.
     *
     * @param src a string on which is created MD5 hash code
     * @return a string which contains MD5 hash code which was created by string which was passed as parameter
     */
    public static String getHash(String src) {
        String result = new String();
        if (src != null) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.reset();
                messageDigest.update(src.getBytes("utf-8"));
                result = new BigInteger(1, messageDigest.digest()).toString(16);
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                LOG.info(e);
            }
        }
        StringBuilder sb = new StringBuilder(32);
        for (int i = 0, count = 32 - result.length(); i < count; i++) {
            sb.append("0");
        }
        return sb.append(result).toString();
    }
}
