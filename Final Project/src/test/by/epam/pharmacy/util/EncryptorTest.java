package test.by.epam.pharmacy.util;
import by.epam.pharmacy.util.Encryptor;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by Lenovo on 19.06.2016.
 */

public class EncryptorTest {
    @Test
    public void getHash() {
        final String SOURCE_STRING = "password";
        final String EXPECTED_RESULT = "5f4dcc3b5aa765d61d8327deb882cf99";
        assertEquals(EXPECTED_RESULT, Encryptor.getHash(SOURCE_STRING));
    }

}
