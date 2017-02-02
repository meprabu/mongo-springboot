/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mongo.samplemongoapp.controller;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mongo.samplemongoapp.bean.UserForm;
import com.mongo.samplemongoapp.dao.CustomerRepository;
import com.mongo.samplemongoapp.dao.UserRepository;
import com.mongo.samplemongoapp.domain.BlogPost;
import com.mongo.samplemongoapp.domain.Users;
import com.mongo.samplemongoapp.utility.MongoAppUtility;

@Controller
public class WelcomeController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	CustomerRepository customerRepository;
	
	@Value("${application.message:Hello World}")
	private String message = "Hello World";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", this.message);
		return "welcome";
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String login(Model model) {	
		model.addAttribute("userForm", new UserForm());
		return "login";
	}
	
	@RequestMapping(value = "/signup", method=RequestMethod.GET)
	public String signup(Model model) {	
		model.addAttribute("userForm", new UserForm());
		return "signup";
	}
	
	@RequestMapping(value = "/authorBlogPost", method=RequestMethod.GET)
	public String gotoBlog(Model model) {	
		model.addAttribute("blogPost", new BlogPost());
		return "authorBlogPost";
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public String processLogin(Model model, @ModelAttribute UserForm userForm, BindingResult result) {	
		Users user = userRepository.findOne(userForm.getUserName());
		if(null!=user && userForm.getUserName().equalsIgnoreCase(user.getUserName())){
			String salt = (user.getPassword().toString()).split(",")[1];
			
			if(user.getPassword().toString().equals(MongoAppUtility.makePasswordHash(userForm.getPassword().toString(), salt))){
				model.addAttribute("message", "Welcome "+ userForm.getUserName());
				return "authorBlogPost";
			}else{
				result.rejectValue("password", "error.userForm", "Password Incorrect. Try again.");
				model.addAllAttributes(result.getAllErrors());
				return "login";
			}
			
		}else{
			result.rejectValue("userName", "error.userForm", "User name not found. Please signup");
			model.addAllAttributes(result.getAllErrors());
		}
	return "login";
	}
	
	
	@RequestMapping(value = "/signup", method=RequestMethod.POST)
	public String processSignup(Model model, @ModelAttribute UserForm userForm, BindingResult result) {	
		Users user = userRepository.findOne(userForm.getUserName());
		
		if(null == user && userForm.getUserName()!="" && userForm.getUserName()!=""){
			user = new Users();
			user.setUserName(userForm.getUserName());
			Random random = new SecureRandom();
			//this.password = ; //i know it overrides
			user.setPassword(MongoAppUtility.makePasswordHash(userForm.getPassword(), Integer.toString(random.nextInt())));
			user.setEmail(userForm.getEmail());
			userRepository.save(user);
		}else if(userForm.getUserName().equals("") && userForm.getUserName().equals("")){
			result.rejectValue("userName", "error.userForm", "Please complete the Form.");
			return "signup";
		}
		else{
			result.rejectValue("userName", "error.userForm", "Username taken already.");
			return "signup";
		}
		
		return "redirect:login";
	}
	
	
	@RequestMapping(value = "/postBlog", method=RequestMethod.POST)
	public String postBlog(Model model) {	
		
		return "authorBlogPost";
	}
	

}
