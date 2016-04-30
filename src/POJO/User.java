package POJO;

import java.util.HashMap;
import java.util.Map;

public class User {

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
		return (pseudo.length() > 20 || email.length() > 50);
	}
	
	public static Map<String, String> getErrors(String pseudo, String password, String email)
	{
		final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^!&+=])(?=\\S+$).{5,10}";
		Map<String, String> errors = new HashMap<String, String>();
		if(pseudo.length() > 20 || pseudo.length() <= 2)
			errors.put("pseudoLength", "Le pseudo doit contenir au minimum 2 caractères et au maximum 20 caractères.");
		if(!email.matches(EMAIL_PATTERN))
			errors.put("mailValidity", "Votre email est invalide.");
		if(email.length() > 50)
			errors.put("mailLength", "Votre adresse mail doit contenir 50 caractères maximum.");
		if(!password.matches(PASSWORD_PATTERN))
			errors.put("passwordValidity", "Le mot de passe est invalide");
		return errors;
		
	}
	
	
	
	
}
