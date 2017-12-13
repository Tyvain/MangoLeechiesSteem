package leechiesnews;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import leechiesnews.manager.SteemManager;
import leechiesnews.model.News;

public class BGDemotivationalSteem extends AbstractBG {
	//static String folder = "../../data/GImg/demotivational";
	static String folder = "/projects/data/GImg/demotivational";
	static List<String> tags = Arrays.asList("funny", "busty", "boobs", "bgfunny", "bustygirls");
	
	
	public static void main(String[] args) throws IOException {
		News news = new News();
		news.author = author;
		news.activeKey = activeKey;		
		news.postKey = postKey;
		news.cleanTitle = "Busty Girls - funny";
		String texte = getTexte(folder);
		news.cleanText = texte;
		news.cleanImgUrl = postImageUrl;
		System.out.println("----->\n"  + texte);
		news.cleanTags = tags;	
		SteemManager.uploadNews(news);
	}	
}
