package com.info6250.finalproject.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.info6250.finalproject.exception.ProductException;
import com.info6250.finalproject.pojo.Product;

public class ProductDAO extends DAO {
	
	public void saveProduct(Product p) throws ProductException {
		
		try {
			begin();
			getSession().save(p);
			commit();
		}
       catch (HibernateException e) {
    	   rollback();
    	   
    	   throw new ProductException("save Product issue" + e.getMessage());
    	 
       }
       	
	}
	
	public List<Product> availableProducts() {
		begin();
		Query q = getSession().createQuery("from Product");
		
		List<Product> availableProductsList = q.list();
		commit();
		return availableProductsList;
	}
	
	public void delete(Product p) throws ProductException {
		try {
			
			begin();
            getSession().delete(p);
            commit();
            close();
			
			
		}catch(HibernateException e) {
			rollback();
			
			throw new ProductException("delete Product issue" + e.getMessage());
			
		}
		
		}
	
	public Product get(int id)  throws ProductException {

        try {
            //save category to the database

            begin();
            Query q = getSession().createQuery("from Product where pID = :productID ");
            q.setParameter("productID", id);
            Product p=(Product) q.getSingleResult();
            commit();
            
            return p;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create the category", e);
            throw new ProductException("Exception while obtaining the id from the database: " + id + " " + e.getMessage());
        }
    }
	
	
	public Product getProductviaName(String pName) throws ProductException {
		
		try {
            //save category to the database

            begin();
            Query q = getSession().createQuery("from Product where productName = :pName ");
            q.setParameter("pName", pName);
            Product p=(Product) q.getSingleResult();
            commit();
            
            return p;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create the category", e);
            throw new ProductException("Exception while obtaining the Product from the database: " + pName + " " + e.getMessage());
        }
		
	}
	
	
	public void updateProduct(Product prd) throws ProductException {
		// TODO Auto-generated method stub
		try {
			begin();
			getSession().update(prd);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new ProductException("Exception while updating the product Id" + e.getMessage());
		}
	}

	

}
