package leechiesnews.manager;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.bittrade.libs.steemj.SteemApiWrapper;
import eu.bittrade.libs.steemj.base.models.AccountName;
import eu.bittrade.libs.steemj.base.models.GlobalProperties;
import eu.bittrade.libs.steemj.base.models.Transaction;
import eu.bittrade.libs.steemj.base.models.operations.CommentOperation;
import eu.bittrade.libs.steemj.base.models.operations.DeleteCommentOperation;
import eu.bittrade.libs.steemj.base.models.operations.Operation;
import eu.bittrade.libs.steemj.configuration.SteemJConfig;
import eu.bittrade.libs.steemj.enums.PrivateKeyType;
import eu.bittrade.libs.steemj.exceptions.SteemCommunicationException;
import eu.bittrade.libs.steemj.exceptions.SteemInvalidTransactionException;
import eu.bittrade.libs.steemj.exceptions.SteemResponseError;
import leechiesnews.model.News;

public class SteemManager {
	private static final Logger logger = LoggerFactory.getLogger(SteemManager.class);

	public static void uploadNews(News news) {
			String firstTag = news.cleanTags.get(0);
			CommentOperation commentOperation = new CommentOperation();
			commentOperation.setParentAuthor(new AccountName(""));
			commentOperation.setParentPermlink(firstTag); // on met le premier tag
			commentOperation.setPermlink(firstTag + new Random().nextInt(1000000));
			commentOperation.setAuthor(new AccountName(news.author));
			commentOperation.setTitle(news.cleanTitle);
			commentOperation.setBody(news.cleanText);
			commentOperation.setJsonMetadata(buildMetaData(news));
			executeOperation(commentOperation, news);		
	}	
	
	public static void deleteNews (String permalink, News news) {
		DeleteCommentOperation deleteCommentOperation = new DeleteCommentOperation();
		deleteCommentOperation.setAuthor(new AccountName(news.author));
		deleteCommentOperation.setPermlink(permalink);
		executeOperation(deleteCommentOperation, news);
	}	
	
	private static void executeOperation(Operation operation, News news) {

		// Change the default settings if needed.
		SteemJConfig myConfig = SteemJConfig.getInstance();

		myConfig.setTimeout(100000L);
		try {
			myConfig.setWebsocketEndpointURI(new URI("wss://this.piston.rocks"));
			myConfig.setSslVerificationDisabled(true);
		} catch (URISyntaxException e) {
			throw new RuntimeException("The given URI is not valid.", e);
		}

		List<ImmutablePair<PrivateKeyType, String>> privateKeys = new ArrayList<>();

		privateKeys.add(new ImmutablePair<>(PrivateKeyType.POSTING, news.postKey));
		privateKeys.add(new ImmutablePair<>(PrivateKeyType.ACTIVE, news.activeKey));

		AccountName myAccount = new AccountName(news.author);

		myConfig.getPrivateKeyStorage().addAccount(myAccount, privateKeys);

		try {
			// Create a new apiWrapper with your config object.
			SteemApiWrapper steemApiWrapper = new SteemApiWrapper();

			logger.info("Performing a transaction");
	

			ArrayList<Operation> operations = new ArrayList<>();
			operations.add(operation);

			logger.info("Get the current RefBlockNum and RefBlockPrefix from the global properties.");
			GlobalProperties globalProperties = steemApiWrapper.getDynamicGlobalProperties();
			int refBlockNum = (int) (globalProperties.getHeadBlockNumber() & 0xFFFF);

			Transaction transaction = new Transaction();
			transaction.setRefBlockNum(refBlockNum);
			transaction.setRefBlockPrefix(globalProperties.getHeadBlockId().getHashValue());
			transaction.setOperations(operations);
			try {
				transaction.sign();
			} catch (SteemInvalidTransactionException e) {
				logger.error("A propblem occured while signing your Transaction.", e);
			}
			steemApiWrapper.broadcastTransaction(transaction);
		} catch (SteemResponseError e) {
			// The SteemResponseError contains the error response.
			logger.error("An error with code {} occured with the following message {}.", e.getError().getSteemErrorDetails().getData().getCode(), e.getError().getSteemErrorDetails().getMessage());
		} catch (SteemCommunicationException e) {
			logger.error("Error!", e);
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
		rez += "],\"image\":[\""+news.cleanImgUrl+"\"],";
		rez += "\"app\":\"steemit/0.1\",\"format\":\"markdown\"}";
		return rez;
	}
}