package utils;

import org.apache.commons.codec.binary.Base64;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * User: nathanchen
 * Date: 29/01/2014
 * Time: 9:56 PM
 * Description:
 */
public class Encryption
{
    public static String md5Encryption(String message) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] byteEnc = message.getBytes();
        md.update(byteEnc);
        byteEnc = md.digest();
        return byte2hex(byteEnc);
    }

    public static boolean MD5Compare(String message, String md5)
            throws Exception
    {
        if (message == null || "".equals(message) || md5 == null || "".equals(md5))
        {
            return false;
        }
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] byteEnc = message.getBytes();
        byte[] byteMD5 = md5.getBytes();

        md.update(byteEnc);
        byteEnc = md.digest();

        byteEnc = byte2hex(byteEnc).getBytes();
        return MessageDigest.isEqual(byteEnc, byteMD5);
    }

    private static String byte2hex(byte[] byteArr)
    {
        String lstrHs = "";
        String lstrTmp = "";
        for (byte aByte : byteArr)
        {
            lstrTmp = (Integer.toHexString(aByte & 0XFF));
            if (lstrTmp.length() == 1)
            {
                lstrHs = lstrHs + "0" + lstrTmp;
            }
            else
            {
                lstrHs = lstrHs + lstrTmp;
            }
        }
        return lstrHs.toUpperCase();
    }

    private static final int RADIX = 16;
    private static final String SEED = "01035395482399294871910385";

    public static String reversibleEncrypt(String message)
    {
        if (message == null)
        {
            return "";
        }
        if (message.length() == 0)
        {
            return "";
        }

        BigInteger bi_passwd = new BigInteger(message.getBytes());

        BigInteger bi_r0 = new BigInteger(SEED);
        BigInteger bi_r1 = bi_r0.xor(bi_passwd);

        return bi_r1.toString(RADIX);
    }

    public static String reversibleDecrypt(String encrypted)
    {
        if (encrypted == null)
        {
            return "";
        }
        if (encrypted.length() == 0)
        {
            return "";
        }

        BigInteger bi_confuse = new BigInteger(SEED);

        try
        {
            BigInteger bi_r1 = new BigInteger(encrypted, RADIX);
            BigInteger bi_r0 = bi_r1.xor(bi_confuse);

            return new String(bi_r0.toByteArray());
        }
        catch (Exception e)
        {
            return "";
        }
    }

    public static String base64Encrypt(String message)
    {
        byte[] rawBytes = message.getBytes();
        byte[] apacheBytes = Base64.encodeBase64(rawBytes);
        return new String(apacheBytes);
    }

    public static String base64Decrypt(String encrypted)
    {
        byte[] encryptBytes = encrypted.getBytes();
        byte[] apacheBytes = Base64.decodeBase64(encryptBytes);
        return new String(apacheBytes);
    }
}
