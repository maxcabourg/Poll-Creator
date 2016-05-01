package POJO;

import java.util.HashMap;
import java.util.Map;

public class User {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
	private int id;
	private String pseudo;
	private String password;
	private String email;
	
	public User(int id, String pseudo, String password, String email) {
		super();
		this.id = id;
		this.pseudo = pseudo;
		this.password = password;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public static boolean isValid(String pseudo, String password, String email){
		return (pseudo.length() <= 20 && email.length() <= 50 && password.matches(User.PASSWORD_PATTERN));
	}
	
	public static Map<String, String> getErrors(String pseudo, String password, String email)
	{
		Map<String, String> errors = new HashMap<String, String>();
		if(pseudo.length() > 20 || pseudo.length() <= 2)
			errors.put("pseudoLength", "Name must at least contain 2 characters and 20 maximum.");
		if(!email.matches(EMAIL_PATTERN))
			errors.put("mailValidity", "Invalid email.");
		if(email.length() > 50)
			errors.put("mailLength", "Your email adress must contain 50 characters maximum");
		if(!password.matches(PASSWORD_PATTERN))
			errors.put("passwordValidity", "Invalid Password.");		
		return errors;
		
	}
	
	
	
	
}
