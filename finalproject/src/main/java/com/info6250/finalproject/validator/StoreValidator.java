package com.info6250.finalproject.validator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import com.info6250.finalproject.pojo.Store;

@Component
public class StoreValidator implements Validator{

	public boolean supports(Class aClass)
    {
        return aClass.equals(Store.class);
    }

    public void validate(Object obj, Errors errors)
    {
        Store store = (Store) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sName", "error.invalid.sName", "Store Name Cannot be Empty");
  
}
        
    
    
	
}
