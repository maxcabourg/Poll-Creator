package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Utility class that initializes the JDBC driver. It reads a file to get the logins.
 * @author MAX
 *
 */
public class Database {
	
	private static final String FICHIER_PROPERTIES       = "/database/database.properties";
    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_USERNAME 		 = "nomutilisateur";
    private static final String PROPERTY_PASSWORD   	 = "motdepasse";
    private static int NB_INSTANCES = 0;
    public static Connection conn;
    
    /**
     * Initialize the JDBC driver
     */
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
	        Class.forName(driver);
	        NB_INSTANCES++;
	        if(NB_INSTANCES <= 1)
	        	conn = DriverManager.getConnection(url, nomUtilisateur, motDePasse);
		} catch (IOException e) {
			System.out.println("Erreur : fichier de configuration introuvable.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Erreur lors de la connexion à la base de données.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Driver introuvable");
			e.printStackTrace();
		}
		        
    }
    
    public Connection getConnexion()
    {
    	return conn;
    }

}
