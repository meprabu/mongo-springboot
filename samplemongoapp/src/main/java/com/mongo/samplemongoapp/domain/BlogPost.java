package com.mongo.samplemongoapp.domain;

import java.util.ArrayList;

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
		    { 'comment' : 'blah', 'user_id' : 654321, 'date' : ISODate("2013-04-06T23:17:35.530Z")},
		    { 'comment' : 'foo', 'user_id' : 987654, 'date' : ISODate("2013-04-06T23:17:35.530Z") },
		    ...
		  ]
}*/
	
	private String author;
	private String body;
	@Id
	private String permalink;
	private String title;
	private String tags;
	private ArrayList<Comments> comments;
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
	public ArrayList<Comments> getComments() {
		return comments;
	}
	public void setComments(ArrayList<Comments> comments) {
		this.comments = comments;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	

}
