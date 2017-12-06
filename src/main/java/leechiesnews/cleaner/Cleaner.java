package leechiesnews.cleaner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import leechiesnews.model.News;

public abstract class Cleaner {
	public abstract void clean(News in);

	protected void cleanImg(News in) {
		if (StringUtils.isNotEmpty(in.imgUrl)) {
			String urlRegex = "(http.*?(jpg|gif))";
			Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
			Matcher urlMatcher = pattern.matcher(in.imgUrl);
			urlMatcher.find();
			in.cleanImgUrl = urlMatcher.group(0);
		}
	}
	
	protected String selectOut(String text, String selector) {
		if (StringUtils.isNotEmpty(text)) {
			Document doc = Jsoup.parse(text);
			return doc.select(selector).outerHtml();
		}
		return text;
	}

	protected String selectIn(String text, String selector) {
		if (StringUtils.isNotEmpty(text)) {
			Document doc = Jsoup.parse(text);
			return doc.select(selector).html();
		}
		return text;
	}

	protected String removeAll(String text, String... selector) {
		String rez = text;
		for (String sel : selector) {
			rez = remove (rez, sel);
		}
		return rez;
	}
	
	protected String remove(String text, String selector) {
		if (StringUtils.isNotEmpty(text)) {
			Document doc = Jsoup.parse(text);
			doc.select(selector).remove();
			return doc.outerHtml();
		}
		return text;
	}
}
