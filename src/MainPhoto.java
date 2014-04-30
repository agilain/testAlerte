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
				
		Plante p = new Plante(null);
		if(args.length>0)
			p.setPlante3(args[0]);
		else
			p.setPlante3("pourcen/test/27.03m.jpg");
		p.utkppv(6);
		
	}

}
