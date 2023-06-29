package com.info6250.finalproject.pojo;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


import org.springframework.stereotype.Component;

@Component
@Entity
public class Product {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pID;
	
		
	
	private String productName;
	private double pPrice;
	
	@Lob
	private byte[] pImage;
	@Transient
	private String base64;
	
	
	 @ManyToMany(mappedBy = "products",cascade = CascadeType.PERSIST)
	    private List<Store> stores = new ArrayList<>();
	 
	
	
	public String getBase64() {
		 if (pImage != null && pImage.length > 0) {
			 base64=Base64.getEncoder().encodeToString(pImage);
		    } else {
		    	base64=null;
		    }
		return base64;
	}
	public void setBase64(String base64) {
		this.base64 = base64;
	}
	public int getpID() {
		return pID;
	}
	public void setpID(int pID) {
		this.pID = pID;
	}
	
	
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	
	
	public double getpPrice() {
		return pPrice;
	}
	public void setpPrice(double pPrice) {
		this.pPrice = pPrice;
	}
	
	
	public byte[] getpImage() {
		return pImage;
	}
	public void setpImage(byte[] pImage) {
		this.pImage = pImage;
	}
	
	
	public String getBase64Image() {
	    if (pImage != null && pImage.length > 0) {
	        return Base64.getEncoder().encodeToString(pImage);
	    } else {
	        return null;
	    }
	}
	public List<Store> getStores() {
		return stores;
	}
	public void setStores(List<Store> stores) {
		this.stores = stores;
	}
	

		
	
}
