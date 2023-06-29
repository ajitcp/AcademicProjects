package com.info6250.finalproject.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Component

@Entity
public class Store {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sId;
	private String sName;
	
	 @ManyToMany
	    @JoinTable(name = "product_store",
	            joinColumns = @JoinColumn(name = "store_id"),
	            inverseJoinColumns = @JoinColumn(name = "product_id"))
	    private List<Product> products = new ArrayList<>();
	
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}	
	public List<Product> getPrdList() {
		return products;
	}
	public void setPrdList(List<Product> prdList) {
		this.products = prdList;
	}
	
	

}
