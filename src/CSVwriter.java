import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//Classe pour aller �crire notre nouveau fichier CSV
public class CSVwriter {
	private static BufferedWriter br;
	private static StringBuilder sb;
	
	//M�thode pour �crire l'en-t�te
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
	   
		//On �crit dans notre fichier cvs chacune des news de notre liste ligne par ligne
		for(News n : listeNews) {
			sb.append(n.getTitle() + "\t");
	        sb.append(" , ");
	        sb.append(n.getDescription() + "\t");
	        sb.append(',');
	        sb.append(n.getUrl()+ "\t");
	        sb.append(',');
	        sb.append(n.getPublishedAt()+ "\t");
	        br.write(sb.toString()+ "\t");
	        
	        sb.append('\n');
        }
           
        br.close();
        
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            openCSV();
        }
    }
	
	//m�thode d'ouverture du fichier csv apr�s son �criture
	public static void openCSV() {
		 try{
			 if (Desktop.isDesktopSupported()) {
				    try {
				        File myFile = new File("innodb.csv");
				        Desktop.getDesktop().open(myFile);
				    } catch (IOException ex) {
				        ex.getMessage();
				    } finally {
				    	System.out.println("Ouverture du fichier innodb.csv compl�t�!");
				    }				    				
				}

		      } catch (Exception ex) {
		        ex.printStackTrace();
		      }
		
	}
}
