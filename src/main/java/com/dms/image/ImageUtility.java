package com.dms.image;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtility {
	
	public static void resize(String fileName, int length, int height)
	{
		BufferedImage originalImage;
		try {
			originalImage = ImageIO.read(new File(fileName));
		
			int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
			
	        BufferedImage resizeImageJpg = resizeImage(originalImage, type, length, height);
			ImageIO.write(resizeImageJpg, "jpg", new File(fileName)); 
			
			//BufferedImage resizeImagePng = resizeImage(originalImage, type, length, height);
			//ImageIO.write(resizeImagePng, "png", new File("one.png")); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static BufferedImage resizeImage(BufferedImage originalImage, int type, int x , int y){
		
		BufferedImage resizedImage = new BufferedImage(x, y, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, x, y, null);
		g.dispose();
			
		return resizedImage;
	}

}
