package com.primelog.cirrus.common.util;

import com.sevenklick.common.util.helpers.EncryptionUtil;
import com.sevenklick.common.util.exception.TicketNotValidException;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by pierre petersson on 2014-04-06.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:junit-test-context.xml")
public class EncryptUtilTest extends TestCase {
@Test
public void encryptAndDecryptSuccessful() throws TicketNotValidException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidKeyException, InvalidAlgorithmParameterException {
    //Message to encrypt
    String messageToEncrypt = "784131410641374852";
    //Encrypt message with privateSecurityKey
    String  encryptedMessage  = EncryptionUtil.getInstance().encrypt(messageToEncrypt);

    //Decrypted encrypted message with password
    String decryptedMessage =   EncryptionUtil.getInstance().decrypt(encryptedMessage);
    String decryptedMessage2 =   EncryptionUtil.getInstance().decrypt(encryptedMessage);
    assertEquals(messageToEncrypt, decryptedMessage);

}
    @Test(expected =TicketNotValidException.class )
         public void decryptUnsuccessful() throws TicketNotValidException {
        //Message to encrypt
        String messageToEncrypt = "784131410641374852";
        //Encrypt message with privateSecurityKey
        String  encryptedMessage  = EncryptionUtil.getInstance().encrypt(messageToEncrypt);
        // Manipulate encrypteMessage
        encryptedMessage=encryptedMessage+"Manipulated";

        //Decrypted encrypted message with password
        String decryptedMessage =   EncryptionUtil.getInstance().decrypt(encryptedMessage);

        String decryptedMessage2 =   EncryptionUtil.getInstance().decrypt(encryptedMessage);

        assertEquals(messageToEncrypt, decryptedMessage);


    }
}
