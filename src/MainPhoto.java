import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class MainPhoto {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		//BufferedImage newImage = ImageIO.read(new File("data/malatest.png"));
		//Color c = new Color(newImage.getRGB(newImage.getHeight()/2, newImage.getWidth()/2));
		//System.out.println(newImage.getHeight());
		//System.out.println(newImage.getWidth());

		//System.out.println(c.getRGB());
		//System.out.println(c.getGreen());
		
		Plante p = new Plante(null);
		//p.setPlante3("data/big.png");  
		//p.getSante();
		//p.imagedResult2("data/big.png");
		p.rectangle("data/big.png");
		

	}

}
