package com.learnSphere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {

	@GetMapping("/index")
	public String index()
	{
		return "index";
	}
	@GetMapping("/login")
	public String login()
	  {
		  return "login";
	  }
	@GetMapping("/register")
	public String register()
	  {
		  return "register";
	  }
	@GetMapping("/addC")
	public String addC()
	{
		return "addCourse";
	}
	@GetMapping("/newLesson")
	public String newLesson()
	{
		return "newLesson";
	}
	@GetMapping("/studentHome")
	public String studentHome()
	{
		return "studentHome";
	}
	@GetMapping("/trainerHome")
	public String trainerHome()
	{
		return "trainerHome";
	}
	
}
