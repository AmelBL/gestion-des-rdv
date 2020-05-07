package metier;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class connexionbdd{

    static ResultSet rst;
    static java.sql.Connection connexion = null;
    static Statement st;

    public static java.sql.Connection connectBD() throws Exception {
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/alogtp?autoReconnect=true&useSSL=false";
            String utilisateur = "root";
            String mdp = "amelamel";
            Class.forName(driver);

            java.sql.Connection connexion = DriverManager.getConnection(url, utilisateur, mdp);

            return connexion;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connexion;
    }

    public static void fermerBD(){}

}
