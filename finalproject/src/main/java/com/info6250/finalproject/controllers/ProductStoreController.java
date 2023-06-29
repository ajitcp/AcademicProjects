package com.info6250.finalproject.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.info6250.finalproject.dao.ProductDAO;
import com.info6250.finalproject.dao.StoreDAO;
import com.info6250.finalproject.exception.ProductException;
import com.info6250.finalproject.exception.StoreException;
import com.info6250.finalproject.pojo.Product;
import com.info6250.finalproject.pojo.Store;
import com.info6250.finalproject.validator.ProductValidator;
import com.info6250.finalproject.validator.StoreValidator;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;


@Controller
public class ProductStoreController {
	
	@Autowired
	ProductValidator pVal;
	
	@Autowired
	StoreValidator sVal;
	
	//private final CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	
	
	@RequestMapping(value="/product",method=RequestMethod.GET)
	public String showproductForm(HttpServletRequest req,ModelMap model, Product product) {
		
		HttpSession session = req.getSession(false); 
		 byte[] defaultContent = "Default photo content".getBytes(); 
		 /*
		 MockMultipartFile defaultPhoto = new MockMultipartFile("defaultPhoto.jpg",defaultContent);
		 CommonsMultipartFile defaultPhotoWrapper = null;
		 defaultPhotoWrapper = new CommonsMultipartFile(defaultPhoto);*/
		product.setpImage(defaultContent);
		product.setProductName("Product Name");
		product.setpPrice(0.00);
		product.setBase64("default");
		//product.setpPhoto(pic.jpg);
		model.addAttribute("product",product);
		session.setAttribute("managePrdAction", "ADD");
		return "Products";
	}
	
	
	@RequestMapping(value="/products",method=RequestMethod.GET)
	public ModelAndView handleAvailableProducts(HttpServletRequest req,HttpServletResponse res,ProductDAO productdao) {
		
		System.out.println("Inside Manage Products");
	
		ModelAndView mv=null;
		List<Product> availableProducts= productdao.availableProducts();
		mv= new ModelAndView("view-products", "availableProducts", availableProducts);
	
		return mv;
		
	}
	
	
	@RequestMapping(value="/manageproducts",method=RequestMethod.GET)
	public ModelAndView handleManageProducts(HttpServletRequest req,HttpServletResponse res,ProductDAO productdao,ModelMap model,Product prdUpdateForm,StoreDAO storedao) {
		
		
		
		
		HttpSession session = req.getSession(false);
		System.out.println("HELLO");
		String action= req.getParameter("actionValue");
		System.out.println(action);
		ModelAndView mv = null;
		 String prdId = req.getParameter("select");
		// int pID = Integer.parseInt(prdId);
		 System.out.println("Inside Manage");

		 if (prdId== null) {
				
				mv= new ModelAndView("checkbox");
				
			}
		 
		 else {
			 switch(action) {
			 case "DELETE":
				 
				System.out.println("INSIDE DELETE PRODUCT");
				 //System.out.println(prdId);
				 try {
					 session.setAttribute("managePrdAction", "DELETE");
					Product product = productdao.get(Integer.parseInt(prdId));
				List<Store> storeshavingproduct	= product.getStores();
					//productdao.delete(product);
				for (Store s:storeshavingproduct ) {
					System.out.println(s.getsName());
					s.getPrdList().remove(product);
					try {
						storedao.saveStore(s);
					} catch (StoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				    productdao.delete(product);
					List<Product> availableProducts= productdao.availableProducts();
						
					mv=new ModelAndView("view-products", "availableProducts", availableProducts);
					
				} catch (ProductException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				 
				 break;
				 
			 case "UPDATE":
				 try {
					 session.setAttribute("managePrdAction", "UPDATE");
					 System.out.println("Inside Update");
					Product productUpdate = productdao.get(Integer.parseInt(prdId));
					String pName=productUpdate.getProductName();
					System.out.println(prdId);
					System.out.println(pName);
					double PPrice=productUpdate.getpPrice();
					byte[] defaultContent = "Default photo content".getBytes();
					prdUpdateForm.setpID(Integer.parseInt(prdId));
					prdUpdateForm.setProductName(pName);
					prdUpdateForm.setpPrice(PPrice);
					prdUpdateForm.setpImage(defaultContent);
					prdUpdateForm.setBase64("default");
					model.addAttribute("product", prdUpdateForm);
					mv=new ModelAndView("updateProduct");
					
				} catch (ProductException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				 
			 
			 break;
			 }
			 
		 }
		 
		 

		 return mv;
		
	}
	
	
	
	@RequestMapping(value="/store",method=RequestMethod.GET)
	public String showstoreForm(ModelMap model, Store store) {
		
		store.setsName("Store Name");
		model.addAttribute("store",store);
		return "store";
	}
	
	
	@RequestMapping(value="/addproduct",method=RequestMethod.POST)
	public ModelAndView addProductForm(HttpServletRequest request, @RequestParam("file") MultipartFile file,@ModelAttribute("product") Product product,BindingResult res, SessionStatus status,ProductDAO productdao) throws Exception
	{

		//String name =request.getParameter("productname");
		//System.out.println(name);
		HttpSession sessionProduct = request.getSession(false);
		String prdAction = (String)sessionProduct.getAttribute("managePrdAction");
		System.out.println(prdAction);
		ModelAndView mv = null;
		System.out.println("INSIDE PRODUCTSTORE CONTROLLER");
		
		String baseDir = "/Users/ajitpatil/Documents/workspace-SpringMVCboot/finalproject/src/main/resources/static/FileUpload//";
		
		file.transferTo(new File(baseDir + "product.jpg"));
			
	  pVal.validate(product, res);
	  
	  if(prdAction.equals("ADD")) {
		  
	  

	        if (res.hasErrors()) {
	            mv = new ModelAndView("Products");
	        }
	        else {
	        	  
	        	  String pName= product.getProductName();
	               double dpPrice=product.getpPrice();
	               File pImage = new File("/Users/ajitpatil/Documents/workspace-SpringMVCboot/finalproject/src/main/resources/static/FileUpload/product.jpg");
	        	  //System.out.println("NO ERROR");
	        	  InputStream ip = new FileInputStream(pImage);
	        	  byte[] photo = new byte[(int)pImage.length()];
	        	  ip.read(photo);
	        	 ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	      		 Product newproduct =(Product)ac.getBean("product");
	      		newproduct.setProductName(pName);
	      		newproduct.setpPrice(dpPrice);
	      		newproduct.setpImage(photo);
	        	 productdao.saveProduct(newproduct);
	        	 
	        	// request.setAttribute("addprd", newproduct);
	        	 mv = new ModelAndView("NewProduct","addprd", newproduct);
	        	
	        }
		
		
	
		
		
		
	}
	  else if(prdAction.equals("UPDATE")) {
		  
		  System.out.println("Inside update product");
		  if (res.hasErrors()) {
	            mv = new ModelAndView("updateProduct");
	        }
	        else {
	        	int toUpdatePrdId=product.getpID();
	            System.out.println(toUpdatePrdId);
	        	String toUpdatePrdName=product.getProductName();
	        	System.out.println(toUpdatePrdName);
	        	double toUpdatePrdPrice=product.getpPrice();
	        	File pImg = new File("/Users/ajitpatil/Documents/workspace-SpringMVCboot/finalproject/src/main/resources/static/FileUpload/product.jpg");
	        	 InputStream ipF = new FileInputStream(pImg);
	        	  byte[] prdPhoto = new byte[(int)pImg.length()];
	        	  ipF.read(prdPhoto);
	        	  ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	        	  Product updatePrd =(Product)appContext.getBean("product");
	        	  updatePrd.setpID(toUpdatePrdId);
	        	  updatePrd.setProductName(toUpdatePrdName);
	        	  updatePrd.setpPrice(toUpdatePrdPrice);
	        	  updatePrd.setpImage(prdPhoto);
	        	  productdao.updateProduct(updatePrd);
	        	  
	        	  mv= new ModelAndView("productUpdated","updatePrd",updatePrd);
	        	  
	        	  
	        }
		  
		  
	  }
	  return mv;
	}
	
	
	
	
	
	@RequestMapping(value="/addstore",method=RequestMethod.POST)
	public ModelAndView addStoreForm(HttpServletRequest request, @ModelAttribute("store") Store store,BindingResult res,StoreDAO storedao) throws IOException
	{

		ModelAndView mv = null;
		sVal.validate(store, res);
		
		if (res.hasErrors()) {
			mv= new ModelAndView("store");
		}
		else {
			
			String sName= store.getsName();
			 ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
     		  Store newStore =(Store)ac.getBean("store");
     		 newStore.setsName(sName);
     		 try {
				storedao.saveStore(newStore);
				
	     		 mv= new ModelAndView("NewStore","addstore", newStore);
				
			} catch (StoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     		
			
		}
		
		
		return mv;
		
	}
	
	@RequestMapping(value="/productstore",method=RequestMethod.GET)
   public ModelAndView handleStoreAssociatetoProductsShow(HttpServletRequest request,StoreDAO storedao) {
		ModelAndView mv=null;
		List<Store> availableStores= storedao.availableStores();
		mv= new ModelAndView("associate-store-product", "availableStores", availableStores);
		return mv;
		
	}
	
	@RequestMapping(value="/assciateStoretoProducts",method=RequestMethod.POST)
	public ModelAndView handleStoreAssociatetoProductsSubmit(HttpServletRequest request,StoreDAO storedao,ProductDAO productdao) {
		ModelAndView mv=null;
		String action = request.getParameter("actionValue");
		String selectedStoreid = request.getParameter("select");
		System.out.println(selectedStoreid);
		
		if (selectedStoreid== null) {
			System.out.println("Inside checkbox issue");
			mv= new ModelAndView("checkboxselecterror");
			
		}
		else {
				
		 switch(action) {
		 case "AssociateProducts":
			try {
				Store store = storedao.get(Integer.parseInt(selectedStoreid));
				String storeName=store.getsName();
				List<Product> availableProducts= productdao.availableProducts();
				mv= new ModelAndView("addProducttoStore","availableProducts",availableProducts);
				mv.addObject("store", store);
				
			}  catch (StoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		 
		 break;
		 }
		
		}
		return mv;
	}
	
	
	@RequestMapping(value="/addProductstoStore",method=RequestMethod.POST)
	public ModelAndView addProductstoStore(HttpServletRequest request,StoreDAO storedao,ProductDAO productdao) {
		ModelAndView mv=null;
		String select = request.getParameter("select");
        System.out.println(select);
		if (select== null) {
			System.out.println("Inside checkbox issue");
			mv= new ModelAndView("checkboxissuewhileaddingproductstostore");
			
		}
		else {
			boolean noproductstoAdd = false;
			int count=0;
			String storeID = request.getParameter("storeid");
			int storeIDInt= Integer.parseInt(storeID);
			try {
				System.out.println("INSIDE ADD PRODUCT TO STORE");
				Store store = storedao.get(storeIDInt);
				
				String[] selectedProducts = request.getParameterValues("select");
				mv = new ModelAndView("ProductExists");
				 
			        for (String productId : selectedProducts) {
			            
			        
					try {
						 Product tobeAddedproduct = productdao.get(Integer.parseInt(productId));
						 
						 Boolean existsCheck=storedao.getStorefromAssociation(storeIDInt, Integer.parseInt(productId));
						 
						 if(existsCheck==true) {
							 noproductstoAdd=true;
							 count++;
							 //mv = new ModelAndView("ProductExists");
							 continue;
						 }
						 else {
							 noproductstoAdd=false;
							 store.getPrdList().add(tobeAddedproduct);
								storedao.saveStore(store);
								mv=new ModelAndView("AssociationSuccess");
								
							 }
						
					} 
					 catch (ProductException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
			           
			        }
			     
			        
			} catch (StoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
			
		}
		
		return mv;
	}
	
	
	
	@RequestMapping(value="/removeproductfromStore",method=RequestMethod.POST)
	public ModelAndView handleManageProductsinStore(HttpServletRequest request,StoreDAO storedao,ProductDAO productdao) throws StoreException, NumberFormatException, ProductException {
		ModelAndView mv=null;
		String select = request.getParameter("select");
        System.out.println(select);
		if (select== null) {
			System.out.println("Inside checkbox issue");
			mv= new ModelAndView("checkboxissuewhileaddingproductstostore");
			
		}
		else {
			String storeID = request.getParameter("storeId");
			int storeIDInt= Integer.parseInt(storeID);
			
				System.out.println("INSIDE REMOVE PRODUCT TO STORE");
				Store store = storedao.get(storeIDInt);
				List<Product> productList = store.getPrdList();
				
				String[] selectedProducts = request.getParameterValues("select");
				 
			        for (String productId : selectedProducts) {
			        	
			        	Product productToRemove = productdao.get(Integer.parseInt(productId));
			        	 int index = productList.indexOf(productToRemove);
			        	 if (index >= 0) {
			        	        productList.remove(index);
			        	        storedao.saveStore(store);
			        	    }
			} 
			        List<Product> productsUnderStore;
			        productsUnderStore = storedao.getProductsfromStore(storeIDInt);
					
					
					mv= new ModelAndView("showproductsunderStore","productsUnderStore",productsUnderStore);
					mv.addObject("store", store);
			        
		}
			        
			        
			        return mv;	
	}
	
	
	@RequestMapping(value="/viewproductstore",method=RequestMethod.GET)
	public ModelAndView handleViewProductsAssociatedtoStore(HttpServletRequest request,StoreDAO storedao) {
		ModelAndView mv=null;
		List<Store> availableStores= storedao.availableStores();
		mv= new ModelAndView("showStorestoDisplayProducts", "availableStores", availableStores);
		
		return mv;
		
	}
	
	
	@RequestMapping(value="/showproductsinstore",method=RequestMethod.POST)
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
			
			
			mv= new ModelAndView("showproductsunderStore","productsUnderStore",productsUnderStore);
			mv.addObject("store", s);
		} catch (StoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
		}
		
		
		
		return mv;
		
		
	}
	
	
	
}
