package leechiesnews.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;

public class News implements Serializable {
	private static final long serialVersionUID = 1L;

	public String url; // key unique
	public String cleanUrl;		
	
	public String title;
	public String cleanTitle;
		
	public String text;
	public String cleanText;
	
	public List<String> tags;
	public List<String> cleanTags = new ArrayList<String>();
		
	public String imgUrl;
	public String cleanImgUrl;
	
	public boolean uploaded;
	public Date uploadedTime=null; // date d'envoi sur steem
	public boolean hasError=false;
	public String error="";
	public String cleanerClass;
	
	public String author;
	public String postKey;
	public String activeKey;
		
	public Document toDocument () {
		return new Document("_id", url)
		        .append("uploadedTime", uploadedTime)
		        .append("hasError", hasError)
		        .append("error", error)
		        .append("cleanText", cleanText);
	}
	
	public static News toAnnonce (Document doc) {
		News rez = new News ();
		rez.url = doc.getString("_id");
		rez.uploadedTime = doc.getDate("uploadedTime");
		return rez;
	}
		
	@Override
	public boolean equals(Object o) {
		return url == ((News) o).url;
	}

	@Override
	public int hashCode() {
		return Objects.hash(text);
	}

	@Override
	public String toString() {
		String ret = "\nurl : "+ url;
		ret += "\ntitre : "+ title;
		ret += "\ntexte : "+ StringUtils.substring(text, 0, 20) + "...";
		ret += "\nuploadedTime : "+ uploadedTime;
		ret += "\nuhasError : "+ hasError;
		String wtf = StringUtils.isEmpty(error) ? "none":error;
		ret += "\nerror : " + wtf;
		ret+= "\nimgUrl : " + imgUrl;
		ret+= "\ntags : " +displayTags();
		return ret;
	}
	
	public String displayTags () {
		String rez = "";
		for (String i : tags) {
			rez += i + " # ";
		}
		return rez;
	}
}
