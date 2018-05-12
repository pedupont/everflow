import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//Classe pour les interractions avec la base de données mysql
public class mySql {	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";  
	private static final String URL = "jdbc:mysql://localhost:3306/mysql?autoReconnect=true&useSSL=false";
	
	//Login
	private static final String USER = "root";
	private static final String PASS = "";	
    
	static Connection conn = null;
		
	//pour ouvrir notre connexion avec exception déclenchée
	public static void connect() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{	
		try
	    {	//On lance notre driver
			Class.forName(DRIVER).newInstance();

			conn = DriverManager.getConnection(URL, USER, PASS);
	    }
	    catch (Exception e)
	    {
	     System.err.println(e.getMessage());
	    }
		finally 
	    {
			if (conn != null)
		        System.out.println("Connexion réussie!");
			else
		        System.out.println("Connexion échouée!");
	    }
	}

	public void close(){
        if(conn != null){
            try {
                conn.close();
		        System.out.println("Connexion fermée!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
	public static void insert(News n) throws SQLException {
		  Statement st = conn.createStatement();
		  String sqlCmd = "INSERT INTO `innodb`(`id`, `title`, `description`, `url`, `publishedAt`) VALUES (' + n.title +',' + n.description +',' + n.url +',' + n.publishedAt +'";
	      st.executeUpdate(sqlCmd);
	      conn.close();
	}
	
}
