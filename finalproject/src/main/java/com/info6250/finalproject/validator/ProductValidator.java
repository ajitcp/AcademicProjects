package com.info6250.finalproject.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.info6250.finalproject.pojo.Product;

@Component
public class ProductValidator implements Validator {
	
	public boolean supports(Class aClass)
    {
        return aClass.equals(Product.class);
    }

    public void validate(Object obj, Errors errors)
    {
        Product product = (Product) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName", "error.invalid.productName", "Product Name Cannot be Empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pPrice", "error.invalid.pPrice", "Price Cannot be Empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pImage", "error.invalid.pImage", "Image Cannot be Empty");
        if (product.getpPrice() <=0.0) {
        	errors.rejectValue("pPrice","error.zeronegative.pPrice" ,"price cannot be zero or negative value");
        	
        }
        else if (product.getpPrice() >50.0) {
        	errors.rejectValue("pPrice","error.high.pPrice","product price is high");
        }
        
        //additional validations could be done below
    }
	

}
