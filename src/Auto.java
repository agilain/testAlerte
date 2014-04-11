import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Auto {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedImage newImage = ImageIO.read(new File("data/NDVI.png"));
		Color c = new Color(newImage.getRGB(newImage.getHeight()/2, newImage.getWidth()/2));
		System.out.println(newImage.getHeight());
		System.out.println(newImage.getWidth());

		System.out.println(c.getRGB());
		System.out.println(c.getGreen());
		Plante p = new Plante(null);
		p.setPlante3("data/reduite2.png"); // en réalité, la première photo doit être celle de la plante à étudier en RGB, l'autre, celle en NDVI 
		p.getSante2();
		p.imagedResult("data/reduite2.png");
		

	}

}
