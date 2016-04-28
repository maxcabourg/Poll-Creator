package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
	
	private static final String FICHIER_PROPERTIES       = "/database/database.properties";
    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_USERNAME 		 = "nomutilisateur";
    private static final String PROPERTY_PASSWORD   	 = "motdepasse";
    
    private static Connection conn;
    
    public Database()
    {
    	Properties properties = new Properties();
		String url;
		String driver;
		String nomUtilisateur;
		String motDePasse;

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );
		
		try {
			properties.load( fichierProperties );
			url = properties.getProperty( PROPERTY_URL );
	        driver = properties.getProperty( PROPERTY_DRIVER );
	        nomUtilisateur = properties.getProperty( PROPERTY_USERNAME);
	        motDePasse = properties.getProperty(PROPERTY_PASSWORD);
	        Connection conn = DriverManager.getConnection(url, nomUtilisateur, motDePasse);
		} catch (IOException e) {
			System.out.println("Erreur : fichier de configuration introuvable.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Erreur lors de la connexion à la base de données.");
			e.printStackTrace();
		}
		        
    }
    
    public static Connection getConnexion()
    {
    	return conn;
    }

}
