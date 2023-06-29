package com.info6250.finalproject.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.info6250.finalproject.exception.UserException;
import com.info6250.finalproject.pojo.Users;

public class UsersDAO extends DAO {
	
	 public void saveUser(Users user) throws UserException{
		 try {
			 begin();
		        getSession().save(user);
		        commit(); 
			 
		 }
        catch(HibernateException e) {
        	rollback();
            throw new UserException("Exception while creating user: " + e.getMessage());
        	
        }
        
    }
	 
	 
	 public Users getUserByUserNameAndPassword(String userName, String password) {
	        Session session = getSession();
	        Query<Users> query = session.createQuery("from Users where username = :userName and password = :password");
	        query.setParameter("userName", userName);
	        query.setParameter("password", password);
	        Users user = query.uniqueResult();
	        return user;
	    }
	

}
