import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class ReadHeadlines {
	//Var URL pour faire la lecture � partir de l'adresse 
	static private URL newsapi;
	static private BufferedReader reader;
	//Array list qui va contenir toutes nos objets nouvelles
	static private ArrayList<News> listeNews = new ArrayList<News>();
	 
	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		//Pour aller lire le contenu dans l'adresse URL
		newsapi = new URL("https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=57f4696d8c28484f849bc09108670849");
		reader = new BufferedReader(new InputStreamReader(newsapi.openStream()));
		
		//La connexion my sql ne fonctionnait pas...
		//mySql.connect();
		
		try {
			readURLChars();
			System.out.println("Transfert des nouvelles compl�t�!\n");			
			//On envoit finalement la liste de nouvelles � notre fichier CSV 
			CSVwriter.writeNews(listeNews);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void readURLChars() throws IOException, ParseException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		int car = 0; 
		String contenu = "";
		
		//Lecture du contenu du site caract�re par caract�re
		while ((car = reader.read()) != -1) {
			contenu += (char)car;		
			//Si on 
			if ((contenu.split("\"title\"", -1).length-1) == 2) {
				try {
					splitNews(contenu);
					contenu = contenu.substring(contenu.length() - 7, contenu.length());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
		
		//Quand on sort de la boucle �a veut dire qu'il ne reste plus de texte et la derni�re nouvelle a �t� lue et elle est � ajouter
		splitNews(contenu);
		
        reader.close();
	}
	
	public static void splitNews(String contenu) throws ParseException {
		String title = "";
		String descr = "";
		String url = "";
		String publish = "";
			
		//Pour d�limiter seulement le titre de la news
		title = contenu.subSequence(contenu.indexOf("\"title\""), contenu.indexOf("\"description\"")).toString();
		title = title.substring(9, title.length() - 2);
		contenu = contenu.substring(title.length(), contenu.length());
		
		//Pour d�limiter seulement le texte de la description
		descr = contenu.subSequence(contenu.indexOf("\"description\""), contenu.indexOf("\"url\"")).toString();
		descr = descr.substring(15, descr.length() - 2); 
		contenu = contenu.substring(descr.length(), contenu.length());
		
		//Pour d�limiter seulement le texte de l'url
		url = contenu.subSequence(contenu.indexOf("\"url\""), contenu.indexOf("\"urlToImage\"")).toString();
		url = url.substring(7, url.length() - 2);
		contenu = contenu.substring(url.length(), contenu.length());
		
		//Pour d�limiter seulement le texte de la date publi�e
		if (contenu.indexOf("\"source\"") != -1) {
			publish = contenu.subSequence(contenu.indexOf("\"publishedAt\""), contenu.indexOf("\"source\"")).toString();
			publish = publish.substring(15, publish.length() - 4);
		}
		else //Si jamais on est � la fin du texte, on d�limite la s�quence jusqu'� la fin du fichier
		{
			publish = contenu.subSequence(contenu.indexOf("\"publishedAt\""), contenu.length()).toString();
			publish = publish.substring(15, publish.length() - 4);
		}
					
		//On ajoute finalement notre objet news � notre arraylist
		listeNews.add(new News(title, descr, url, publish));
	}
		
	
	/*public void insertSql(News n) throws ParseException {
		News n1 = new News("Dollar, yields slide on soft US inflation, stocks rally", "Lorem ipsum dolor sit amet, eu suspendisse, posuere cras. Nam montes curabitur", "https://www.reuters.com/article/us-global-markets/dollar-yields-slide-on-soft-u-s-inflation-idUSKBN1IB02F", "2018-05-10T00:43:07Z");
		try {
			mySql.insert(n1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}*/

}
