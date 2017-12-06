package leechiesnews;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import leechiesnews.manager.FileManager;
import leechiesnews.manager.SteemManager;
import leechiesnews.model.News;
import leechiesnews.model.RandomNames;

public class BGStorySteem extends AbstractBG {
	//static String folder = "../../data/BTP/Photos";
	static String folder = "/projects/data/BTP/Photos";
	static List<String> tags = Arrays.asList("nsfw", "busty", "boobs", "sexy", "bustygirls");
	static String randomFile = FileManager.pickRandomFile(folder);
	static int numberOfPic;

	public static void main(String[] args) throws IOException {
		News news = new News();
		news.author = author;
		news.activeKey = activeKey;
		news.postKey = postKey;		
		String texte = getTexte(folder);
		news.cleanTitle = "Busty Girls - introducing " + extractNameFrommFile() + " (" + numberOfPic + " pics)";
		news.cleanText = texte;
		news.cleanImgUrl = postImageUrl;
		System.out.println("----->\n" + texte);
		news.cleanTags = tags;
		SteemManager.uploadNews(news);
	}

	private static String extractNameFrommFile() {
		// extract name from file
//		String name = randomFile.substring(randomFile.indexOf("Photos") + 7, randomFile.length());
//		name = name.substring(name.indexOf("/") + 1, name.length());
//		System.out.println("-----> name: " + name);
//		name = name.split("\\\\")[0];
		String name = RandomNames.getRandomName();
		System.out.println("-----> -random name: " + name);
		return name;
	}

	protected static String getTexte(String folder) throws IOException {
		String texte = "";
		System.out.println("-----> random file: " + randomFile);

		// List all files from file repo
		File parentFile = new File(randomFile).getParentFile();
		System.out.println("-----> -parentFile: " + parentFile);

		// Number of files in repo
		File[] filesList = parentFile.listFiles();
		System.out.println("-----> -nb: " + filesList.length);

		// Getting between 8 and 12 files
		Random r = new Random();
		int maxNb = r.nextInt(12 - 6) + 6;
		System.out.println("-----> -max: " + maxNb);

		// recup le max de file
		File[] filesListSelected = new File[maxNb];
		if (maxNb < filesList.length) {
			int fileEvery = filesList.length / maxNb;
			System.out.println("-----> -fileEvery: " + fileEvery);
			for (int i = 0; i < maxNb; i++) {
				File selected = filesList[i * fileEvery];
				System.out.println(i * fileEvery + "----------> -selection: " + selected);
				filesListSelected[i] = selected;
			}
		} else {
			filesListSelected = filesList;
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
