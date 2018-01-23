package leechiesnews;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import leechiesnews.manager.FileManager;
import leechiesnews.model.News;

public class NerdzSteem extends AbstractBG {
	static String folder = "nerdz";
	static List<String> tags = Arrays.asList("comic", "funny", "nerdz", "life", "steemit");
		
	public static void main(String[] args) throws IOException {
		News news = new News();
		news.author = "";
		news.activeKey = "";		
		news.postKey = "";		
		
		HashMap<String, String> randomMap = new HashMap<>();

		// pick 3 different files
		while (randomMap.size() < 3) {
			String randomFile = FileManager.pickRandomFile (folder);
			randomMap.put(randomFile, "toto" );//FileManager.uploadImg(randomFile)
		}
		
		
		String texte = "";		
		for (String key:randomMap.keySet()){
			texte += imgUrlToHTML(randomMap.get(key)) + "\n\n";	
		}
		
		System.out.println(texte);
		
		//
		
		news.cleanTitle = "Nerdz - A steemit commic strip (bestof)";
//		String texte = getTexte(folder);
//		news.cleanText = texte;
//		news.cleanImgUrl = postImageUrl;
//		System.out.println("----->\n"  + texte);
//		news.cleanTags = tags;

		//SteemManager.uploadNews(news);
	}	
}
