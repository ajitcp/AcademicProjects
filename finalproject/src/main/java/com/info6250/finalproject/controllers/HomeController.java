package com.info6250.finalproject.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.info6250.finalproject.dao.AddressDAO;
import com.info6250.finalproject.dao.ProductDAO;
import com.info6250.finalproject.dao.StoreDAO;
import com.info6250.finalproject.dao.UsersDAO;
import com.info6250.finalproject.exception.StoreException;
import com.info6250.finalproject.pojo.Address;
import com.info6250.finalproject.pojo.Product;
import com.info6250.finalproject.pojo.Store;
import com.info6250.finalproject.pojo.Users;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
		
		
	}
	
	
	@RequestMapping("/MyAccount")
	public String myAccount() {
		return "MyAccount";
		
	}
	
	/*	 
	@GetMapping("/product")
	public ModelAndView createProduct() {
		return new ModelAndView("Products", "product", new Product() );
	}*/
	/*
	@GetMapping("/productStore")
	public String addProductStore() {
		return "ProductStore";
	}
	*/
	
	
	@RequestMapping(value="/homestores",method=RequestMethod.GET)
	public ModelAndView handleCustomerStores(HttpServletRequest request,StoreDAO storedao) {
		ModelAndView mv=null;
		List<Store> availableStores= storedao.availableStores();
		mv= new ModelAndView("showStorestoCustomer", "availableStores", availableStores);
		
		return mv;
		
	}
	
	

	@RequestMapping(value="/MyProfile",method=RequestMethod.GET)
	public ModelAndView handleMyProfile(HttpServletRequest request,UsersDAO userdao,ModelMap model,Users users, AddressDAO addressdao) {
		ModelAndView mv=null;
		HttpSession session = request.getSession();
		            String userName =(String)session.getAttribute("UserName");
		             String password =(String)session.getAttribute("passWord");
		           Users  user = userdao.getUserByUserNameAndPassword(userName, password);
		           //System.out.println(user.getUserid());
		           users.setUsername(user.getUsername());
		           users.setFirstname(user.getFirstname());
		           users.setLastname(user.getLastname());
		           users.setPassword(user.getPassword());
		           users.setEmail(user.getEmail());
		           users.setUserrole(user.getUserrole());
		           Address address =user.getAddress();
		          users.setAddress(address);
		           model.addAttribute(users);
		             
		mv= new ModelAndView("MyProfile", "users", user);
		
		return mv;
		
	}
	
	@RequestMapping(value="/homeproducts",method=RequestMethod.GET)
	public ModelAndView handleCustomerProducts(HttpServletRequest request,ProductDAO productdao) {
		ModelAndView mv=null;
		List<Product> availableProducts= productdao.availableProducts();
		mv= new ModelAndView("view-products-customer", "availableProducts", availableProducts);
		return mv;
		
	}
	
	@RequestMapping(value="/homestorepurchase",method=RequestMethod.GET)
	public ModelAndView handleCustomerChooseStoreProducts(HttpServletRequest request,StoreDAO storedao) {
		ModelAndView mv=null;
		
		List<Store> availableStores= storedao.availableStores();
		mv= new ModelAndView("showCustomerStorestoDisplayProducts", "availableStores", availableStores);
		
		
		return mv;
		
	}
	
	
	@RequestMapping(value="/showcustomerproductsinstore",method=RequestMethod.POST)
	public ModelAndView handleshowProductsinStore(HttpServletRequest request,StoreDAO storedao){
		ModelAndView mv=null;
		String action = request.getParameter("actionValue");
		String select = request.getParameter("select");
		if (select== null) {
			System.out.println("Inside checkbox issue");
			mv= new ModelAndView("checkboxissuebacktoviewproductstore");
		}
		else {
			String storeID= request.getParameter("select");
			int sID=Integer.parseInt(storeID);
		List<Product> productsUnderStore;
		try {
			Store s = storedao.get(sID);
			productsUnderStore = storedao.getProductsfromStore(sID);
			mv= new ModelAndView("showcustomerproductsunderStore","productsUnderStore",productsUnderStore);
			mv.addObject("store", s);
		} catch (StoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
		}
		
		
		
		return mv;
		
		
	}
	
	@RequestMapping(value="/productsview",method=RequestMethod.GET)
	public ModelAndView handleAvailableProducts(HttpServletRequest req,HttpServletResponse res,ProductDAO productdao) {
		
		//System.out.println("Inside Manage Products");
	
		ModelAndView mv=null;
		List<Product> availableProducts= productdao.availableProducts();
		mv= new ModelAndView("justviewProducts", "availableProducts", availableProducts);
	
		return mv;
		
	}
	
	@RequestMapping(value="/justviewhomestores",method=RequestMethod.GET)
	public ModelAndView handleViewCustomerStores(HttpServletRequest request,StoreDAO storedao) {
		ModelAndView mv=null;
		List<Store> availableStores= storedao.availableStores();
		mv= new ModelAndView("justviewStores", "availableStores", availableStores);
		
		return mv;
		
	}
	
	
}
