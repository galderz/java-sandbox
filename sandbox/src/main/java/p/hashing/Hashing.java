package p.hashing;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Hashing
{
    public static void main(String[] args) throws NoSuchAlgorithmException
    {
        final var msg1 = "git clone abc";
        final var msg2 = "git clone abc";
        System.out.println(msg1.hashCode());
        System.out.println(msg2.hashCode());
        System.out.println(sha256(msg1));
        System.out.println(sha256(msg2));
        System.out.println(sha1b64(msg1));
        System.out.println(sha1b64(msg2));
        System.out.println(sha1h(msg1));
        System.out.println(sha1h(msg1).length());
        System.out.println(sha1h(msg2));
        System.out.println(sha1h(msg2).length());
        System.out.println(base64(msg1));
        System.out.println(base64(msg2));
    }

    static String sha256(String s) throws NoSuchAlgorithmException
    {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(s.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(messageDigest.digest());
    }

    static String sha1b64(String s) throws NoSuchAlgorithmException
    {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        messageDigest.update(s.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(messageDigest.digest());
    }

    static String sha1h(String s) throws NoSuchAlgorithmException
    {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        messageDigest.update(s.getBytes(StandardCharsets.UTF_8));
        return String.format("%x", new BigInteger(1, messageDigest.digest()));
    }

    static String base64(String s)
    {
        return Base64.getEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
    }
}
