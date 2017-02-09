package com.mongo.samplemongoapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mongo.samplemongoapp.domain.BlogPost;

public interface BlogPostRepository extends  CrudRepository<BlogPost, String>{
	
	List<BlogPost> findByAuthor(String author);
	
	BlogPost findByPermalink(String permalink);
	
}
