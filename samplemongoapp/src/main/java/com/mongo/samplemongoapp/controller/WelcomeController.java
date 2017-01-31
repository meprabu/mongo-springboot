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

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mongo.samplemongoapp.bean.UserForm;

@Controller
public class WelcomeController {

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
	
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public String processLogin(Model model, @ModelAttribute UserForm userForm) {	
		
		if(userForm.getUserName().equalsIgnoreCase("prabu") && userForm.getPassword().equalsIgnoreCase("test")){
			model.addAttribute("message", "Hello welcome to login");
			return "success";
		}else{
			
			model.addAttribute("error", "invalid details");
			return "login";
		}
		
		
	}

}
