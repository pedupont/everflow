import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

//Objet racine News qui a comme attribut le contenu d'une nouvelle 
public class News {
	private String title;
	private String description;
	private String url;
	private int publishedAt;

	public News(String title, String descrip, String url, String publishedAt) throws ParseException {
		this.title = title;
		this.description = descrip;
		this.url = url;  //Pour convertir en nombre de secondes avant d'affecter (unixTimeStamp)
		setPublishedAt(publishedAt); 
	}

	//GETTER-SETTER des attributs
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPublishedAt() {
		return publishedAt;
	}

	//Setter pour convertir la date obtenue en unixtimestamp
	@SuppressWarnings("deprecation")
	public void setPublishedAt(String publishedAt) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		Date date = sdf.parse(publishedAt);
		
		this.publishedAt =  (int) (date.getTime()/1000);		
	}
}
