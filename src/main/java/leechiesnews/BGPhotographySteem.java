package leechiesnews;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import leechiesnews.manager.SteemManager;
import leechiesnews.model.News;

public class BGPhotographySteem extends AbstractBG {
	//static String folder = "../../data/GImg/pictures";
	static String folder = "/projects/data/GImg/pictures";
	static List<String> tags = Arrays.asList("photography", "busty", "boobs", "bgphotography", "bustygirls");
		
	public static void main(String[] args) throws IOException {
		News news = new News();
		news.author = author;
		news.activeKey = activeKey;		
		news.postKey = postKey;
		news.cleanTitle = "Busty Girls - photography";
		String texte = getTexte(folder);
		news.cleanText = texte;
		news.cleanImgUrl = postImageUrl;
		System.out.println("----->\n"  + texte);
		news.cleanTags = tags;
		SteemManager.uploadNews(news);
	}	
}
