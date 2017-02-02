package com.mongo.samplemongoapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "blogPost")
public class BlogPost {
	
	
/*	Article {
		  "_id" : "A",
		  "title" : "Hello World",
		  "user_id" : 12345,
		  "text" : 'My test article',

		  "comments" : [
		    { 'text' : 'blah', 'user_id' : 654321, 'votes' : [987654]},
		    { 'text' : 'foo', 'user_id' : 987654, 'votes' : [12345, 654321] },
		    ...
		  ]
}*/
	
	private String author;
	private String body;
	@Id
	private String permalink;
	private String title;
	private String tags;
	private String[] comments;
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getPermalink() {
		return permalink;
	}
	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String[] getComments() {
		return comments;
	}
	public void setComments(String[] comments) {
		this.comments = comments;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	

}
