package com.info6250.finalproject.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.info6250.finalproject.dao.UsersDAO;
import com.info6250.finalproject.pojo.Users;

@Controller
public class LoginController {
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	protected ModelAndView handleLogin( 
			HttpServletRequest request,
            HttpServletResponse response) 
	
	{
		System.out.println("LOGIN CONTROLLER");
		
		String userName= request.getParameter("username");
		String passWord = request.getParameter("password");
		System.out.println(userName);
		System.out.println(passWord);
		System.out.println("INSIDE login");
		ModelAndView mv = null;
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UsersDAO userdao=(UsersDAO)context.getBean("usersdao");
		Users user =userdao.getUserByUserNameAndPassword(userName, passWord);
		if (user!=null) {
			
			HttpSession session = request.getSession(true);
			session.setAttribute("UserName", user.getUsername());
			session.setAttribute("passWord", user.getPassword());
			String userRole = user.getUserrole();
			if(userRole.equals("ADMIN")) {
				mv = new ModelAndView("admin");
				mv.addObject("user", user);
				
				
			}
			else if (userRole.equals("USER")) {
				mv = new ModelAndView("homepage");
				mv.addObject("user", user);	
				
			}
			
		}
		else {
			//mv = new ModelAndView("error");
		
			try {
				response.sendRedirect("/GroceryDeliveryApplication/MyAccount");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		return mv;
		
		
	}
	
	@RequestMapping(value="/backtohome",method=RequestMethod.GET)
	protected ModelAndView handleAdminHome( 
			HttpServletRequest request,
            HttpServletResponse response) {
		
		ModelAndView mv=null;
		HttpSession sessionUserLoginCheck = request.getSession(false);
		String loginUserName =(String)sessionUserLoginCheck.getAttribute("UserName");
		String loginPassWord=(String)sessionUserLoginCheck.getAttribute("passWord");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UsersDAO userdao=(UsersDAO)context.getBean("usersdao");
		Users user =userdao.getUserByUserNameAndPassword(loginUserName, loginPassWord);
		if (user!=null) {
			String userRole = user.getUserrole();
			if(userRole.equals("ADMIN")) {
				mv = new ModelAndView("admin");
				mv.addObject("user", user);
				
				
			}
			else if (userRole.equals("USER")) {
				mv = new ModelAndView("homepage");
				mv.addObject("user", user);	
				
			}
			
			
		}else {
		
		
			try {
				response.sendRedirect("/GroceryDeliveryApplication/MyAccount");
			} catch (IOException e) {
				
				e.printStackTrace();
			}

		}
		return mv;
		
		
	}
	
	
	@RequestMapping(value="/Logout",method=RequestMethod.GET)
	protected String handleLogout( 
			HttpServletRequest request) {
		
		System.out.println("Logout");
		
		HttpSession session = request.getSession();
		session.invalidate();
        return "MyAccount";
		
		
		
	}
	

}
