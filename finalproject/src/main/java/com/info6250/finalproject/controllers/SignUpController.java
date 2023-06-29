package com.info6250.finalproject.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.info6250.finalproject.dao.AddressDAO;
import com.info6250.finalproject.dao.UsersDAO;
import com.info6250.finalproject.pojo.Address;
import com.info6250.finalproject.pojo.Users;

@Controller
public class SignUpController {
	
	
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
           String action = request.getParameter("action");
           String uname=request.getParameter("username");
           String fname=request.getParameter("firstname");
           String lname=request.getParameter("lastname");
           String password=request.getParameter("password");
            String email=request.getParameter("email");
            String userrole=request.getParameter("role");
            String aptNum= request.getParameter("apt");
            String city =request.getParameter("city");
            String state=request.getParameter("state");
            String street=request.getParameter("street");
            String zipCd=request.getParameter("zipCode");
             int apt=Integer.parseInt(aptNum);
             int zip=Integer.parseInt(zipCd);
            
            System.out.println(action);
            System.out.println(uname);
            System.out.println(fname);
            System.out.println(lname);
            System.out.println(password);
            System.out.println(email);
            System.out.println(userrole);
            System.out.println(aptNum);
            System.out.println(city);
            System.out.println(state);
            System.out.println(street);
            System.out.println(zipCd);
            	
            
            
            ModelAndView mv = null;
            
             
             
             
            switch(action)
            {
                case "add":
                    
             ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
              Users user = (Users)context.getBean("users");
              Address address = (Address)context.getBean("address");
              AddressDAO adddao=(AddressDAO)context.getBean("addressdao");
              UsersDAO userdao=(UsersDAO)context.getBean("usersdao");
                     
           
              address.setStreet(street);
             address.setCity(city);
             address.setState(state);
             address.setAptNo(apt);
             address.setZipCode(zip);
             
             adddao.saveAddress(address);
                     
            user.setUsername(uname);
            user.setFirstname(fname);
            user.setLastname(lname);
            user.setUserrole(userrole);
            user.setPassword(password);
            user.setEmail(email);
            user.setAddress(address);
            userdao.saveUser(user);
       
      
            
        mv= new ModelAndView("registered-user","user",user);
            break;
            
            
            }
            
      return mv;              
    }
    

}
