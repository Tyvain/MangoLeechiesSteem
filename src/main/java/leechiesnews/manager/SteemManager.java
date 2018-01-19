package leechiesnews.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.bittrade.libs.steemj.SteemJ;
import eu.bittrade.libs.steemj.base.models.AccountName;
import eu.bittrade.libs.steemj.configuration.SteemJConfig;
import eu.bittrade.libs.steemj.enums.PrivateKeyType;
import eu.bittrade.libs.steemj.exceptions.SteemCommunicationException;
import eu.bittrade.libs.steemj.exceptions.SteemInvalidTransactionException;
import eu.bittrade.libs.steemj.exceptions.SteemResponseException;
import leechiesnews.model.News;

public class SteemManager {
	private static final Logger logger = LoggerFactory.getLogger(SteemManager.class);

	public static void uploadNews(News news) {

		SteemJConfig myConfig = SteemJConfig.getInstance();
		myConfig.setResponseTimeout(100000);
		myConfig.setDefaultAccount(new AccountName(news.author));

		// Add and manage private keys:
		List<ImmutablePair<PrivateKeyType, String>> privateKeys = new ArrayList<>();
		privateKeys.add(new ImmutablePair<>(PrivateKeyType.POSTING, news.postKey));
		privateKeys.add(new ImmutablePair<>(PrivateKeyType.ACTIVE, news.activeKey));

		myConfig.getPrivateKeyStorage().addAccount(myConfig.getDefaultAccount(), privateKeys);

		try {
			SteemJ steemJ = new SteemJ();
			steemJ.createPost(news.cleanTitle, news.cleanText,
					news.cleanTags.toArray(new String[news.cleanTags.size()]));
		} catch (SteemCommunicationException | SteemResponseException | SteemInvalidTransactionException e) {
			logger.error("Erro upload news", e);
		}
	}

	public static String buildMetaData(News news) {
		String rez = "{\"tags\":[";

		Iterator<String> itemIterator = news.cleanTags.iterator();
		if (itemIterator.hasNext()) {
			// special-case first item. in this case, no comma
			rez += "\"" + itemIterator.next() + "\"";
			while (itemIterator.hasNext()) {
				// process the rest
				rez += ",\"" + itemIterator.next() + "\"";
			}
		}
		rez += "],\"image\":[\"" + news.cleanImgUrl + "\"],";
		rez += "\"app\":\"steemit/0.1\",\"format\":\"markdown\"}";
		return rez;
	}
}