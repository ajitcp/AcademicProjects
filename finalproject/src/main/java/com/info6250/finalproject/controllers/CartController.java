package com.info6250.finalproject.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.info6250.finalproject.dao.ProductDAO;
import com.info6250.finalproject.dao.StoreDAO;
import com.info6250.finalproject.exception.ProductException;
import com.info6250.finalproject.exception.StoreException;
import com.info6250.finalproject.pojo.Cart;
import com.info6250.finalproject.pojo.Item;
import com.info6250.finalproject.pojo.MyPdfView;
import com.info6250.finalproject.pojo.Product;
import com.info6250.finalproject.pojo.Store;

@Controller
public class CartController {

	@RequestMapping(value = "/addCart", method = RequestMethod.POST)
	public ModelAndView handleCart(HttpServletRequest request, StoreDAO storedao, ProductDAO productdao) {
		ModelAndView mv = null;
		HttpSession session = request.getSession(false);
		List<String> items = new ArrayList<String>();
		Cart cart = (Cart) session.getAttribute("cart");
		String select = request.getParameter("select");
		System.out.println(select);
		if (select == null) {
			System.out.println("Inside checkbox issue");
			mv = new ModelAndView("checkboxissuewhileaddingproductstostore");

		} else {
			String page = request.getParameter("page");
			String storeName = request.getParameter("storeName");

			System.out.println("INSIDE ELSE");
			System.out.println(page);

			switch (page) {
			case "add":

				System.out.println(page);
				String[] selectedProducts = request.getParameterValues("select");

				for (String productId : selectedProducts) {
					System.out.println(productId);
					try {
						Product productSelectforCart = productdao.get(Integer.parseInt(productId));
						String prdName = productSelectforCart.getProductName();
						System.out.println(prdName);
						items.add(prdName);

					} catch (ProductException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				if (cart == null) {
					System.out.println("Inside Cart is null");
					cart = new Cart();
				}

				if (items == null) {
					session.setAttribute("items", items);
					session.setAttribute("cart", cart);

					mv = new ModelAndView("AddSuccessfull");
				}

				for (String name : items) {
					System.out.println("Inside items loop");
					Item item = cart.findItem(name);
					if (item != null) {
						int amount = item.getCount();
						cart.modifyItemCount(name, ++amount);
					} else {
						System.out.println("Add item");
						cart.addItem(new Item(name, storeName, 1));
					}
				}
				session.setAttribute("items", items);
				session.setAttribute("cart", cart);

				mv = new ModelAndView("AddSuccessfull");

				break;

			case "remove":

				Enumeration<String> cartitems = request.getParameterNames();

				while (cartitems.hasMoreElements()) {
					String name = cartitems.nextElement();
					System.out.println(name);
					if (name.equals("Submit"))
						continue;
					String[] itemvalues = request.getParameterValues(name);
					for (String ivalue : itemvalues) {
						System.out.println(ivalue);
						Item itm = cart.findItem(ivalue);
						if (itm != null) {
							int amt = itm.getCount();
							if (amt > 1) {
								cart.modifyItemCount(ivalue, amt - 1);
							} else {
								cart.deleteItem(ivalue);
							}
						}

					}

				}
				mv = new ModelAndView("ViewCart");
				break;

			}
		}

		return mv;

	}

	@RequestMapping(value = "/ViewCart", method = RequestMethod.GET)
	public String handleViewCart(HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession session = request.getSession(false);
		String userName =(String)session.getAttribute("UserName");
		
		if (userName!= null)
		{
			return "ViewCart";
		}
		else {
			
			return "MyAccount";
		}
		

	}

	@RequestMapping(value = "/Cart", method = RequestMethod.POST)
	public ModelAndView handleCartItemDelete(HttpServletRequest request, StoreDAO storedao, ProductDAO productdao) {

		ModelAndView mv = null;
		HttpSession session = request.getSession(false);
		List<String> items = new ArrayList<String>();
		Cart cart = (Cart) session.getAttribute("cart");
		String page = request.getParameter("remove");
		

		if (page == null) {
			System.out.println("Select to remove the Item");

		} else {
			
			String storeName = request.getParameter("storeName");

			System.out.println("INSIDE ELSE");
			System.out.println(page);
			Enumeration<String> cartitems = request.getParameterNames();

			while (cartitems.hasMoreElements()) {
				String name = cartitems.nextElement();
				System.out.println(name);
				if (name.equals("Submit"))
					continue;
				String[] itemvalues = request.getParameterValues(name);
				for (String ivalue : itemvalues) {
					System.out.println(ivalue);
					Item itm = cart.findItem(ivalue);
					if (itm != null) {
						int amt = itm.getCount();
						if (amt > 1) {
							cart.modifyItemCount(ivalue, amt - 1);
						} else {
							cart.deleteItem(ivalue);
						}
					}

				}

			}
			mv = new ModelAndView("ViewCart");

		}

		return mv;

	}
	
	
	@RequestMapping(value = "/checkoutReport.pdf", method = RequestMethod.POST)
	public View handlePlaceOrder(HttpServletRequest request,ProductDAO productdao) {
	   
		double total_Price=0;
		
		System.out.println("INSIDE VIew");
		
		HttpSession session = request.getSession(false);
		Cart cart = (Cart) session.getAttribute("cart");
		for (Item item : cart.getCart()) {
		    
			String pName= item.getName();
		try {
			Product prdinCart=productdao.getProductviaName(pName);
			double pPrice=prdinCart.getpPrice();
		
			System.out.println(pPrice);
			int pQty=item.getCount();	
			double lineTotal=(pPrice*pQty);
			total_Price = total_Price+lineTotal;
			
		} catch (ProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
		}
		
		
	    session.setAttribute("totalPrice",total_Price );
		
		
		
		View view = new MyPdfView();
		return view;
		
				
       // return new ModelAndView(view);
	}
	

}
