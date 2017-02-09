package com.mongo.samplemongoapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mongo.samplemongoapp.dao.BlogPostRepository;
import com.mongo.samplemongoapp.domain.BlogPost;
import com.mongo.samplemongoapp.domain.Comments;

@Controller
public class BlogController {
	
	
	@Autowired
	BlogPostRepository blogpostRepo;
	
	
	@RequestMapping(value = "/createBlog", method=RequestMethod.GET)
	public String postBlog(Model model) {	
		
		model.addAttribute("blogPost", new BlogPost());
		
		return "authorBlogPost";
	}
	


	@RequestMapping(value = "/save", method=RequestMethod.POST)
	public String saveBlog(@ModelAttribute BlogPost blogpost, BindingResult result, HttpServletRequest request){
		
		if(null!=blogpost.getBody() && null!= blogpost.getTitle()){
			String userName = (String) request.getSession().getAttribute("userName");
			blogpost.setAuthor(userName);
			blogpost.setPermalink(blogpost.getTitle().replaceAll("[^a-zA-Z0-9]", "")+ "-" +userName);
			blogpostRepo.save(blogpost);
		}
		return "redirect:authorBlogPost";
	}
	
	
	@RequestMapping(value = "/getBlogByPermalink/{permalink}", method=RequestMethod.GET)
	public String getBlogByPermalink(Model model, HttpServletRequest request, @PathVariable(value = "permalink") String permalink ){
		
		BlogPost blogpost = blogpostRepo.findByPermalink(permalink);
		
		model.addAttribute("blog",blogpost);
		model.addAttribute("comment", new Comments());
		return "viewBlog";
	}
	
	@RequestMapping(value = "/addComments", method=RequestMethod.POST)
	public String addComments(Model model,
			@ModelAttribute Comments comment, @RequestParam(value = "permalink") String permalink ){
		
		
		BlogPost blog = blogpostRepo.findByPermalink(permalink);
		ArrayList<Comments> commentList = new ArrayList<Comments>();
		
		if(null!= blog.getComments()){
			for(Comments prevComments: blog.getComments()){
				commentList.add(prevComments);
			}
		}
		comment.setCommentDate(new java.util.Date());
		commentList.add(comment);
		blog.setComments(commentList);
		blogpostRepo.save(blog);
		
		
		model.addAttribute("blog",blog);
		model.addAttribute("comment", new Comments());
		
		return "viewBlog";
	}
	
	
}
