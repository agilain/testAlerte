import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Tricolore {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	/********Test sur une image tricolore ******/
		BufferedImage image= new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
		int couleur1 = (200 << 16) + (0 << 8) + 0;
		int violet = (67 << 16) + (200 << 8) + 58 ;

		
		for(int i=0 ; i<200 ; i++){
			for(int j=0 ; j<200 ; j++){
				if(i>j & (i>100) ){image.setRGB(i,j,couleur1);
			}
				else if(i<j & (i<100)) {image.setRGB(i,j,violet);}
				else {image.setRGB(i, j, 255);
			
		}
	}
			
			
			
}	
		try {
			    File fic= new File("trico.png");
			    ImageIO.write(image, "png", fic);
			} catch (IOException e) {
			    e.printStackTrace();
			}
		
		Plante p = new Plante(null);
		BufferedImage br = ImageIO.read(new File("trico.png"));
		ArrayList<Pixel> tab = p.photoToPlant(br);
		p.setPlante(tab);
		p.setEpsilon(1);
		p.setK(3);
		p.kmoyenne3();
	}

	}



