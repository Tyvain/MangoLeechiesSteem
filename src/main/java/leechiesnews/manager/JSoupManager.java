package leechiesnews.manager;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSoupManager {
	final static Logger logger = LoggerFactory.getLogger("JSoupUtils");

	public static synchronized Document getDocumentFromUrl(String url) {
		Document doc = null;
		int i = 0;
		boolean success = false;

		while (i < 3) {
			try {
				doc = Jsoup.connect(url).userAgent("Mozilla").followRedirects(true).validateTLSCertificates(false).timeout(60 * 1000).get();
				success = true;
				break;
			} catch (IOException ex) {
				logger.error("GetDocumentFromUrl - " + ex + " -> retry " + i);
			}

			i++;
		}

		if (success) {
			return doc;
		}

		return null;
	}
}
