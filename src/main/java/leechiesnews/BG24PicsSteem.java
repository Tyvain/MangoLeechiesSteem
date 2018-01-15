package leechiesnews;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import leechiesnews.manager.FileManager;
import leechiesnews.manager.SteemManager;
import leechiesnews.model.News;

public class BG24PicsSteem extends AbstractBG {
	//static String folder = "../../data/dailygif";
	static String folder = "/media/usb/data/24pics";
	static List<String> tags = Arrays.asList("nsfw", "bg24", "photography", "sexy", "bustygirls");
	static int numberOfPic=24;

	public static void main(String[] args) throws IOException {
		News news = new News();
		news.author = author;
		news.activeKey = activeKey;
		news.postKey = postKey;
		String texte = getTexte(folder);
		news.cleanTitle = "Busty Girls - 24 naked (" + numberOfPic + " pics)";
		news.cleanText = texte;
		news.cleanImgUrl = postImageUrl;
		System.out.println("----->\n" + texte);
		news.cleanTags = tags;
		SteemManager.uploadNews(news);
	}

	protected static String getTexte(String folder) throws IOException {
		String texte = "";
		int maxNb = 24;

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
