package leechiesnews.cleaner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import leechiesnews.model.News;

public class LifeHackCleaner extends Cleaner {

	@Override
	public void clean(News in) {

		// clean title
		in.cleanTitle = in.title;

		// clean text
		in.cleanText = in.text;

		// clean tags
		// first tag = cryptocurrency
		in.cleanTags.add("life");
		in.cleanTags.add("blog");

		// clean img
		cleanImg(in);

		// Ajout image
		in.cleanText = in.cleanImgUrl + "\n" + in.cleanText;
		
		// Ajout source		
		in.cleanText += "\n Source : " + in.url;
	}
}
