package com.info6250.finalproject.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.info6250.finalproject.pojo.Address;
import com.info6250.finalproject.pojo.Users;

public class AddressDAO extends DAO {
	
	public void saveAddress(Address add){
		
		try {
			begin();
			getSession().save(add);
			commit();
		}
       catch (Exception e) {
    	   System.out.println("save address issue" + e.getMessage());
    	
       }
       
   }
	
	
	 public Address getUserAddressByID(int Id) {
	        Session session = getSession();
	        Query<Address> query = session.createQuery("from Address where addrId = :Id");
	        query.setParameter("Id", Id);
	        Address address = query.uniqueResult();
	        return address;
	    }

}
