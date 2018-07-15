package com.dms.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFUtility {
	
	
	public static void createPDF(String fileName) {
		//Loading an existing document
		
			PDDocument doc = new PDDocument();
	        try {
	            PDPage page = new PDPage();
	            doc.addPage(page);
	            
	            /*PDFont font = PDType1Font.HELVETICA_BOLD;
	 
	            PDPageContentStream contents = new PDPageContentStream(doc, page);
	            contents.beginText();
	            contents.setFont(font, 30);
	            contents.newLineAtOffset(50, 700);
	            contents.showText(message);
	            contents.endText();
	            contents.close();*/
	            doc.save(fileName);
	            doc.close();
	        }
	        catch(IOException ex) {
	        	
	        }
	        
	        
	}
		 
	public static void writePDF() throws IOException {
		 
		 
	
			 //Loading an existing document
		      File file = new File("my_doc.pdf");
		      PDDocument document = PDDocument.load(file);
		       
		      //Retrieving the pages of the document 
		      PDPage page = document.getPage(0);
		      PDPageContentStream contentStream = new PDPageContentStream(document, page);
		      
		      //Begin the Content stream 
		      contentStream.beginText(); 
		       
		      //Setting the font to the Content stream  
		      contentStream.setFont(PDType1Font.TIMES_ROMAN, 18);
	
		      //Setting the position for the line 
		      contentStream.newLineAtOffset(25, 500);
	
		      String text = "This is the sample document and we are adding content to it.";
	
		      //Adding text in the form of string 
		      contentStream.showText(text);      
	
		      //Ending the content stream
		      contentStream.endText();
	
		      System.out.println("Content added");
	
		      //Closing the content stream
		      contentStream.close();
			      
		      
		      //Saving the document
		      document.save("my_doc.pdf");
		         
		      System.out.println("PDF created");  
		    
		      //Closing the document  
		      document.close();
	
		}  
		 
	public static void readPDF() throws IOException {
		

		      //Loading an existing document
		      File file = new File("my_doc.pdf");
		      PDDocument document = PDDocument.load(file);

		      //Instantiate PDFTextStripper class
		      PDFTextStripper pdfStripper = new PDFTextStripper();

		      //Retrieving text from PDF document
		      String text = pdfStripper.getText(document);
		      System.out.println(text);

		      //Closing the document
		      document.close();

		   }
	 
	public static void addPassword() throws InvalidPasswordException, IOException {
		
		 //Loading an existing document
	      File file = new File("my_doc.pdf");
	      PDDocument document = PDDocument.load(file);
	   
	      //Creating access permission object
	      AccessPermission ap = new AccessPermission();         

	      //Creating StandardProtectionPolicy object
	      StandardProtectionPolicy spp = new StandardProtectionPolicy("1234", "1234", ap);

	      //Setting the length of the encryption key
	      spp.setEncryptionKeyLength(128);

	      //Setting the access permissions
	      spp.setPermissions(ap);

	      //Protecting the document
	      document.protect(spp);

	      System.out.println("Document encrypted");

	      //Saving the document
	      document.save("my_doc.pdf");
	      //Closing the document
	      document.close();
	}

	public static byte[] readfile(String filePath) throws IOException{
		
		byte[] bFile = Files.readAllBytes(new File(filePath).toPath());
		return bFile;
	}
	
	public static void writefile(byte[] byteArray){
		try { 
		    FileOutputStream fileOuputStream = new FileOutputStream("filename.pdf"); 
		    fileOuputStream.write(byteArray);
		 } 
		 catch(IOException ie){
			 
		 }
	}

	public static void InsertImage(String fileName, String imageFileName, int cordX, int cordY)  {

		File file = new File(fileName);
        PDDocument doc = null;
        try {
            doc = PDDocument.load(file);
            // Choose IMAGE File
            PDImageXObject pdImage = PDImageXObject.createFromFile(imageFileName, doc);
            PDPage page = doc.getPage(0);
            PDPageContentStream contentStream = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true);
            // Set Position of image on PDF
            contentStream.drawImage(pdImage, cordX, cordY);
            contentStream.close();          
            doc.save(fileName);
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
		
	}
		

}
