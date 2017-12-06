package leechiesnews.cleaner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import leechiesnews.model.News;

public class CryptocoinsnewsCleaner extends Cleaner {

	@Override
	public void clean(News in) {

		// clean title
		in.cleanTitle = in.title;

		// clean text
		in.cleanText = selectOut(in.text, "html");
		in.cleanText = removeAll(in.cleanText, "nav", "em", "aside", "script", ".author-profile-links", ".author-profile", ".mobile", ".showmobile", ".wp-caption-text", ".entry-meta", ".share-comment.click", ".dcl-disqus-thread");
		in.cleanText = selectIn(in.cleanText, "body");

		// clean tags
		// first tag = cryptocurrency
		in.cleanTags.add("cryptocurrency");
		if (in.tags != null) {
			for (String tag : in.tags) {
				tag = tag.replaceAll(" .+$", "");
				tag = tag.replace("News", "");
				tag = tag.replace("news", "");
				if (StringUtils.isNotEmpty(tag)) {
					if (in.cleanTags.size() < 4) {
						// no more than 5 tags
						in.cleanTags.add(tag);
					}
				}
			}
		}

		// clean img
		cleanImg(in);

		// Ajout image
		in.cleanText = in.cleanImgUrl + "\n" + in.cleanText;
		
		// Ajout source		
		in.cleanText += "\n Source : " + in.url;
	}
}
