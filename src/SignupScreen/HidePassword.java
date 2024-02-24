package SignupScreen;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HidePassword {
	public static String cipherPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest hidePassword = MessageDigest.getInstance("MD5");
		
		byte[] messageDigest = hidePassword.digest(password.getBytes());
		
		BigInteger bigInteger = new BigInteger(1, messageDigest);
		
		return bigInteger.toString(16);
	}
}
