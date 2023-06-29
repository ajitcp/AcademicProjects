package com.info6250.finalproject.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.info6250.finalproject.dao.ProductDAO;
import com.info6250.finalproject.exception.ProductException;
import com.info6250.finalproject.pojo.Cart;
import com.info6250.finalproject.pojo.Item;
import com.info6250.finalproject.pojo.Product;

@Controller
public class AjaxController {
	
	@GetMapping("/getCartPrice")
	@ResponseBody
	public double handleCartPrice(@RequestParam("user") String user,HttpServletRequest request, ProductDAO productdao) {
		double total_Price=0;
		HttpSession session = request.getSession(false);
		Cart cart = (Cart) session.getAttribute("cart");
		for (Item item : cart.getCart()) {
		    
			String pName= item.getName();
		try {
			Product prdinCart=productdao.getProductviaName(pName);
			double pPrice=prdinCart.getpPrice();
			System.out.println("INSIDE GET PRICE");
			System.out.println(pPrice);
			int pQty=item.getCount();	
			double lineTotal=(pPrice*pQty);
			total_Price = total_Price+lineTotal;
			
		} catch (ProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
		}
		
		
		//System.out.println("AJAX Request from user: " + user);
		
		return total_Price;
	}
	
	

}
