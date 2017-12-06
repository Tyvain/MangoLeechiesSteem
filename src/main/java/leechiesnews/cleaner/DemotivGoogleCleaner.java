package leechiesnews.cleaner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import leechiesnews.model.News;

public class DemotivGoogleCleaner extends Cleaner {

	@Override
	public void clean(News in) {	

		// clean img
		cleanImg(in);

		// Ajout image
		in.cleanText = in.cleanImgUrl + "\n" + in.cleanText;
		
		// Ajout source		
		in.cleanText += "\n Source : " + in.url;
	}
}
