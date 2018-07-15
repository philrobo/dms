package com.dms.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.barcode.BarCodeUtility;
import com.dms.image.ImageUtility;
import com.dms.jsondatatype.Document;
import com.dms.pdf.PDFUtility;
import com.dms.test.JsonTest;
import com.example.beans.*;
import com.example.car.*;
import com.dms.test.QrCodeGeneratorDemo;


@Controller
public class DMSController {
private static final String welcomemsg = "Welcome Mr. %s!";
    
	
    
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<Document> update(@RequestBody String car) {

	    if (car != null) {
	        //car.setMiles(car.getMiles() + 100);
	    	//System.out.println(car);
	    	Document doc = JsonTest.readJson(car);
	    	BarCodeUtility.createBarCode128(doc.PatientUniqueID);
	    	String imageFile = doc.PatientUniqueID + ".jpg";
	    	String pdfFile = doc.PatientUniqueID + ".pdf";
	    	ImageUtility.resize(imageFile, 180, 30);
	    	PDFUtility.createPDF(pdfFile);
	    	com.dms.pdf.PDFUtility.InsertImage(pdfFile, imageFile,300,700);
		    //System.out.println(doc.sectionDataList.size());
	    	try {
				QrCodeGeneratorDemo.testQrCodeDemo();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	    	return new ResponseEntity<Document>(doc, HttpStatus.OK);
	    }

	    return null;
	}
    
}