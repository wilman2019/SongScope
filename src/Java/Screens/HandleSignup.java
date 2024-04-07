package Screens;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class HandleSignup {
	private String email;
	private String username;
	private String password;
	private static HashMap<String, HandleSignup> signupMap = new HashMap<>();
	
	public HandleSignup(String email, String username, String password) throws NoSuchAlgorithmException {
        this.email = email;
        this.username = username; 
        String hashedPassword = HidePassword.cipherPassword(password);
        this.password = hashedPassword;
    }
	
	public void addToHash(HandleSignup handleSignup) {
        signupMap.put(handleSignup.getUsername(), handleSignup);
    }
	
	public static HashMap<String, HandleSignup> getSignUpMap() {
        return signupMap;
    }
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setUserame(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) throws NoSuchAlgorithmException {
		String hashedPassword = HidePassword.cipherPassword(password);
        this.password = hashedPassword;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
}

