package com.info6250.finalproject.pojo;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.info6250.finalproject.dao.ProductDAO;
import com.info6250.finalproject.exception.ProductException;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class MyPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		Cart cart = (Cart) session.getAttribute("cart");
		  double   total_price = (double)session.getAttribute("totalPrice");
		int line_no=0;
		 Font boldFont = new Font(Font.HELVETICA, 18, Font.BOLD);

	     Font normalBold = new Font(Font.HELVETICA, 10, Font.BOLD);

		 Font normal = new Font(Font.HELVETICA, 10, Font.NORMAL);
		 
		doc.add(Image.getInstance("https://seeklogo.com/images/N/northeastern-university-logo-CD40BD15B7-seeklogo.com.png"));
		Paragraph p1 = new Paragraph ("Order Details",boldFont);
		doc.add(p1);
		Paragraph title= new Paragraph("LINE NO"   + "Product Name" +   "Product Quantity" + "StoreName",normalBold);
		doc.add(title);
            for (Item item : cart.getCart()) {
            	line_no=line_no+1;
            Paragraph line_details= new Paragraph(line_no + " " + " " + " " + " " + item.getName() + " " + " " +  " " + " " + item.getCount()  + " " + " " + " " + " " + item.getStoreName(),normalBold);
			String pName= item.getName();
			doc.add(line_details);
				}
            
            String Total_Price = String.valueOf(total_price);
            Paragraph OrderTotalPrice = new Paragraph("Order Total Price" + " " + " " +  Total_Price,normalBold);
		    doc.add(OrderTotalPrice);
		
	
		
		
		
	}
	

}
