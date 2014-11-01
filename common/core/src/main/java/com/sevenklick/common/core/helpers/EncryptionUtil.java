package com.sevenklick.common.core.helpers;

import com.sevenklick.common.core.exception.TicketNotValidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * Utility class for encrypting/decrypting strings with AES crypto <br>
 * Crypto class<p/>
 *
 * <p/>
 *
 * @author Pierre Petersson
 */
@Component
@Scope(value = "singleton")
@Lazy(value = false)
public class EncryptionUtil {
    private static final Logger logger = LoggerFactory.getLogger(EncryptionUtil.class);
    private final static String privateSecurityKey = "f0a1767a3e050e3b78556dfa7aac25a8ae16589f";
    private static final byte[] SALT = {
            (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
            (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03
    };
    private static final int KEY_LENGTH = 128;
    private static final int ITERATION_COUNT = 65536;

    private static boolean enableEncryption = true;
    private static Cipher dCipher;
    private static EncryptionUtil encryptionUtil;
    public static EncryptionUtil getInstance() {
        if(encryptionUtil==null){
            EncryptionUtil.encryptionUtil = new EncryptionUtil();
            try {
                EncryptionUtil.encryptionUtil.dCipher= initCipher(privateSecurityKey,Cipher.DECRYPT_MODE);
            } catch (NoSuchAlgorithmException e) {
                logger.error("Could not initialiaze encryption util", e);
            } catch (InvalidKeySpecException e) {
                logger.error("Could not initialiaze encryption util",e);
            } catch (NoSuchPaddingException e) {
                logger.error("Could not initialiaze encryption util",e);
            } catch (InvalidKeyException e) {
                logger.error("Could not initialiaze encryption util",e);
            } catch (InvalidAlgorithmParameterException e) {
                logger.error("Could not initialiaze encryption util",e);
            }

        }else{
            return EncryptionUtil.encryptionUtil;
        }
        return EncryptionUtil.encryptionUtil;
    }
    /**
     * This method  encrypts a string with privateSecurityKey
     * @param message - String that you want to encrypt
     * @return String - Returns the message encrypted
     */
    public static String encrypt(String message) {
          String encryptedMessage = message;
        try {

            Cipher eCipher = initCipher(privateSecurityKey,Cipher.ENCRYPT_MODE);
            byte[] encrypted = eCipher.doFinal(message.getBytes());
            byte[] encoded = Base64Util.encodeUrlSafeNoChunks(encrypted);
            encryptedMessage = new String(encoded);

        } catch (Exception e) {
            encryptedMessage = null;
        }


        return encryptedMessage;
    }

    /**
     * This metod decrypts a message with a privateSecuritykey
     * @param message - String that you want to decrypt

     * @return String - Returns the message decrypted
     */
    public static String decrypt(String message) throws TicketNotValidException {
        String decryptedMessage=message;
        try {
            byte[] decoded = Base64Util.decodeUrlSafeNoChunks(message.getBytes());

            byte[] decrypted = EncryptionUtil.dCipher.doFinal(decoded);
            decryptedMessage = new String(decrypted);
        } catch (Exception e) {
            throw new TicketNotValidException(TicketNotValidException.NOT_VALID,"Ticket has been manipulated, not valid ticket");

        }
        return decryptedMessage;
    }

    /**
     * Initialize the crypto algorithm
     * @param serverSecurityKey
     * @param opmode
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     */
    private static Cipher initCipher(String serverSecurityKey,int opmode) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec spec = new PBEKeySpec(serverSecurityKey.toCharArray(), SALT, ITERATION_COUNT, KEY_LENGTH);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
        Cipher echiper = Cipher.getInstance("AES");
        echiper.init(opmode, secret);

        return echiper;
    }


}
