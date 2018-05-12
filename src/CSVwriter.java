import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

//Classe pour aller écrire notre nouveau fichier CSV
public class CSVwriter {
		
	static BufferedWriter br;
	static StringBuilder sb;
	
	//Méthode pour écrire l'en-tête
	public static void writeHeader() throws IOException {
		
		try {
		br = new BufferedWriter(new FileWriter("innodb.csv"));
        sb = new StringBuilder();
        
        sb.append("title " + "\t");
        sb.append(" , ");
        sb.append("description " + "\t");
        sb.append(" , ");
        sb.append(" url " + "\t");
        sb.append(" , ");
        sb.append(" publishedAt" + "\t");
        sb.append("\n");

        br.write(sb.toString());
        
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
	
	public static void writeNews(ArrayList<News> listeNews) throws IOException {
        
		writeHeader();
		
		try {
			
		sb = new StringBuilder();
	   
		for(News n : listeNews) {
			sb.append(n.getTitle());
	        sb.append(',');
	        sb.append(n.getDescription());
	        sb.append(',');
	        sb.append(n.getUrl());
	        sb.append(',');
	        sb.append(n.getPublishedAt());
	        br.write(sb.toString());
	        sb.append('\n');
        }
           
        br.close();

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
	
}
