package leechiesnews;

import java.io.IOException;

import leechiesnews.manager.FileManager;

public abstract class AbstractBG {
	static String author = "bustygirls";
	static String activeKey = "5J6QqeqnEcGsAv4vMfSSa7geiEnwZPpKnDM9fa3wApcxJNmCmn2";		
	static String postKey = "5KRS3P14ccgEMf2p1XsL7rD4BrVnSGNMeLPYL3fJtH4aD5Qfzmz";
	static String disclaimer = "<p><b>This blog shares busty girls pictures into different categories:</b></p>"
	+"<p>- <a href=\"https://steemit.com/created/bgfunny\">BG-funny - funny boobs pictures - safe to open - no nudity</a></p>"
	+"<p>- <a href=\"https://steemit.com/created/bgphotography\">BG-photography - beautiful photos of busty girls - safe to open - no nudity</a></p>"
	+"<p>- <a href=\"https://steemit.com/created/bg24\">BG-24 - 24 pictures of sexy girls everyday - NSFW - nudity</a></p>"		
	+"<p>- <a href=\"https://steemit.com/created/bgintroducing\">BG-introducing - presenting a beautiful busty girl - NSFW - nudity</a></p>"
	+"<p>- <a href=\"https://steemit.com/created/bggifs\">bggifs - sexual gifs - NSFW - porn</a></p>"
	+"<p></p><p>Why this blog: I have been gathering busty girls pictures over the years, here is my contribution to the steemit network :)</p>"
	+"<p></p><p>If you want to help me: upvote / follow / resteem / donate</p><p></p>"
	+"<p><b>Disclaimer: I didn't make those pictures - I don't own any rights to them - I just downloaded them and decided to share them with you.</b></p>";
		
	protected static String postImageUrl = "";
	
//	# Sharing busty girls pictures into different categories:
//		- <a href="https://steemit.com/trending/BG-funny">BG-funny - funny boobs pictures - safe to open - no nudity</a>
//		- <a href="https://steemit.com/trending/BG-photography">BG-photography - beautiful photos of busty girls - safe to open - no nudity</a>
//		- <a href="https://steemit.com/trending/BG-introducing">BG-introducing - presenting a beautiful busty girl - NSFW - nudity</a>
//		- <a href="https://steemit.com/trending/BG-gifs">BG-gifs - sexual gifs - NSFW - porn</a>
//
//		Why this blog: I have been gathering busty girls pictures over the years, here is my contribution to the steemit network :)
//
//		If you want to help me: upvote / follow / resteem / donate
//
//		<b>Disclaimer: I didn't make those pictures - I don't own any rights to them - I just downloaded them and decided to share them with you.</b>
	
	
	protected static String getRandomComment () {		
//		String[] part1 = {"What do you think about this?\n", "oO\n", "Not sure about this one...\n", "I'm confused here...\n", "Hmmmm....\n", "Is this good or bad?\n", "I like this ones :)", 
//				"Really?", "Woah!", "Boobs!", "Those curves!"};
		String[] part2 = {"Please ", "Feel free to ", "Don't hesitate to "};
		String[] part3 = {"share", "resteem", "repost", "comment ", "follow us"};
		String[] part4 = {" if you liked", " if you enjoyed", ".", " if you like boobs"};
		
		return FileManager.randomFromStringArray(part2) + FileManager.randomFromStringArray(part3) + FileManager.randomFromStringArray(part4);
	}
	
	protected static String getTexte (String folder) throws IOException {
		String texte = "";
		postImageUrl = FileManager.uploadRandomImg(folder);
		texte += imgUrlToHTML(postImageUrl) + "\n\n";
		texte += imgUrlToHTML(FileManager.uploadRandomImg(folder)) + "\n\n";
		texte += imgUrlToHTML(FileManager.uploadRandomImg(folder)) + "\n\n\n\n";
		texte += "<p>"+BGDemotivationalSteem.getRandomComment() + "\n\n</p>";
		texte += disclaimer;
		return texte;
	}
	
	protected static String imgUrlToHTML(String imgUrl) {
		return "<p><img src=" + imgUrl + "/></p>";
	}
}
