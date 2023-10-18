package com.learnSphere.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learnSphere.entities.Users;
import com.learnSphere.repositories.UsersRepository;
import com.learnSphere.services.EmailService;


import jakarta.servlet.http.HttpSession;


@Controller
public class ForgotController {
	Random random=new Random(1000);
	
	@Autowired
	private EmailService eService;
	@Autowired
	private UsersRepository urepository;
	
	
	
	
	@RequestMapping("/forgot")
	public String openEmailForm()
	{
		return "forgot_email_form";
	}
	
	@PostMapping("/send-otp")
	public String sendOTP(@RequestParam("email") String email,HttpSession session)
	{
		System.out.println("EMAIL "+email);
		//generating OTP of 4 digit
		int otp=random.nextInt(999999);
		System.out.println("OTP "+otp);
		
		//write code for send OTP to email
		String subject="OTP From LearnInspire";
		String message=""
				+"<div style='border:1px solid #e2e2e2; padding:20px'>"
				+"<h1>"
				+"OTP is "
				+"<b>"+otp
				+"</n>"
				+"</h1> "
				+"</div>";
		String to=email;
		
		boolean flag=this.eService.sendEMail(subject, message, to);
		
		if(flag)
		{
			session.setAttribute("myotp", otp);
			session.setAttribute("email", email);
			session.setAttribute("info", "We have sent OTP to your email");
			
			 session.setAttribute("infoDisplayTimestamp", System.currentTimeMillis());	
		
			
			return "verify_otp";
				}
		else
		{
			session.setAttribute("message", "Check your email id !!");
			return "forgot_email_form";
		}
	
	}
	//Verify OTP
	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam("otp") int otp,HttpSession session)
	{
		
		int myOtp=(int)session.getAttribute("myotp");
		System.out.println("User OTP "+otp);
		System.out.println("Our OTP "+myOtp);
		
		String email=(String)session.getAttribute("email");
		if(myOtp==otp)
		{
			//password_change_form
			Users user=this.urepository.getUserByUserName(email);
			if(user==null)
			{
				 String message = (String) session.getAttribute("message");
				 session.setAttribute("message", "User does not exist !!");
			        // Remove the message attribute from the session
			        session.removeAttribute("message");

				
				return "forgot_email_form";
			}
			else
			{
				return "password_change_form";
			}
			
		}
			
		else
		{
			
			session.setAttribute("danger", "OTP is invalid");
			return "verify_otp";
		}
		
	}
	//change password
	@PostMapping("/change-password")
	public String changePassword(@RequestParam("newpassword") String newpassword, HttpSession session)
	{
		String email=(String)session.getAttribute("email");
		Users user=urepository.getUserByUserName(email);
		user.setPassword(newpassword);
		this.urepository.save(user);
		return "login";

		
	}
    
	
	

}