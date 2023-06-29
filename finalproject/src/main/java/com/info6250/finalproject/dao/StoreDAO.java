package com.info6250.finalproject.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.info6250.finalproject.exception.ProductException;
import com.info6250.finalproject.exception.StoreException;
import com.info6250.finalproject.pojo.Product;
import com.info6250.finalproject.pojo.Store;

public class StoreDAO extends DAO {
	
public void saveStore(Store s) throws StoreException {
		
		try {
			begin();
			getSession().save(s);
			commit();
		}
       catch (HibernateException e) {
    	   rollback();
    	   throw new StoreException("save Store issue" + e.getMessage());
    	 
       }
       	
	}
	
public List<Store> availableStores() {
	begin();
	Query q = getSession().createQuery("from Store");
	
	List<Store> availableStoresList = q.list();
	commit();
	return availableStoresList;
}

public Store get(int id)  throws StoreException {

    try {
        //save category to the database

        begin();
        Query q = getSession().createQuery("from Store where sId = :StoreID ");
        q.setParameter("StoreID", id);
        Store s=(Store) q.getSingleResult();
        commit();
        
        return s;
    } catch (HibernateException e) {
        rollback();
        //throw new AdException("Could not create the category", e);
        throw new StoreException("Exception while obtaining the id from the database: " + id + " " + e.getMessage());
    }
}

public Boolean getStorefromAssociation(int sID,int pId) throws StoreException {
	try {
        //save category to the database
        boolean flag=false;
        begin();
        Store s=getSession().get(Store.class, sID);
        commit();
        
        for (Product p : s.getPrdList()) {
        	if (p.getpID()== pId) {
               flag=true;
            }
        }
        
        return flag;
    }
	catch (HibernateException e) {
        rollback();
       
        throw new StoreException("Exception while obtaining the store from the database: " + sID + " " + e.getMessage());
    }
	
}

public List<Product> getProductsfromStore(int sId) throws StoreException {
	try {
        List<Product> prdListofStore= null;
        begin();
        Store s=getSession().get(Store.class, sId);
        commit();
        
        prdListofStore= s.getPrdList();
        
        return prdListofStore;
       
    }
	catch (HibernateException e) {
        rollback();
       
        throw new StoreException("Exception while obtaining the productList from the database: " + sId + " " + e.getMessage());
    }
	
	
}


}
