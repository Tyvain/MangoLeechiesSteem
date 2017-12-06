package leechiesnews.manager;

import java.util.HashSet;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;

import leechiesnews.model.News;

public class DBManager {
	final static Logger logger = LoggerFactory.getLogger("DBManager");	
	private static MongoClientURI uri  = new MongoClientURI("mongodb://finvalab:fv2017@ds119524.mlab.com:19524/finvalab"); 
	private static MongoClient client = new MongoClient(uri);
	private static MongoDatabase db = client.getDatabase(uri.getDatabase());
	private static MongoCollection<Document> table = db.getCollection("steem");
	private static HashSet<String> cacheUrls = null;
	
	public static void saveNews(News news) {
		try {
			Bson filter = Filters.eq("_id", news.url);
			UpdateOptions options = new UpdateOptions().upsert(true);
			Bson update =  new Document("$set",news.toDocument());
			table.updateOne(filter, update, options);
			cacheUrls.add(news.url);
		} catch (Exception e) {
			logger.error("saveAnnonce: " + e);
		}
	}

	public static boolean newsExists(String url) {
		if (cacheUrls == null) {
			cacheUrls = new HashSet<>();
			table.find().forEach((Block<Document>) document -> {
			News annonce = News.toAnnonce(document);
			cacheUrls.add(annonce.url);					
			});	 
		}			
		return cacheUrls.contains(url);		
	}
}
