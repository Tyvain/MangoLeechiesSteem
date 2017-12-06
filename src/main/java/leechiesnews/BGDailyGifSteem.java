package leechiesnews;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import leechiesnews.manager.FileManager;
import leechiesnews.manager.SteemManager;
import leechiesnews.model.News;

public class BGDailyGifSteem extends AbstractBG {
	//static String folder = "../../data/dailygif";
	static String folder = "/projects/data/dailygif";
	static List<String> tags = Arrays.asList("nsfw", "busty", "porn", "gif", "bustygirls");
	static int numberOfPic;

	public static void main(String[] args) throws IOException {
		News news = new News();
		news.author = author;
		news.activeKey = activeKey;
		news.postKey = postKey;
		String texte = getTexte(folder);
		news.cleanTitle = "Busty Girls - gifs (" + numberOfPic + " pics)";
		news.cleanText = texte;
		news.cleanImgUrl = postImageUrl;
		System.out.println("----->\n" + texte);
		news.cleanTags = tags;
		SteemManager.uploadNews(news);
	}

	protected static String getTexte(String folder) throws IOException {
		String texte = "";

		// Getting between 4 and 6 files
		Random r = new Random();
		int maxNb = r.nextInt(6 - 4) + 6;
		System.out.println("-----> -max: " + maxNb);

		// recup le max de file
		File[] filesListSelected = new File[maxNb];
		for (int i = 0; i < maxNb; i++) {
			File selected = new File(FileManager.pickRandomFile(folder));
			filesListSelected[i] = selected;
		}

		for (int i = 0; i < filesListSelected.length; i++) {
			String url = FileManager.uploadImg(filesListSelected[i].getAbsolutePath());
			if (i == 0) {
				postImageUrl = url;
				System.out.println("----------> -url: " + url);
			}
			texte += imgUrlToHTML(url) + "\n\n";
		}

		numberOfPic = filesListSelected.length;

		texte += disclaimer;
		return texte;
	}
}
