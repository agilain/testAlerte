import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Main3 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedImage image= new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
		int rouge = (200 << 16) + (0 << 8) + 0;
		int vert = (11 << 16) + (10 << 8) + 0 ;
		int blanc = (200 << 16) + (244 << 8) + 200 ;
		
		for(int i=0 ; i<200 ; i++){
			for(int j=0 ; j<200 ; j++){
				if(((i+j)%2)==0){image.setRGB(i,j,rouge);
			}
				else {image.setRGB(i, j, 255);
			
		}
				
						
	}
			
		
			
		
			
}	
		
	for(int m=0 ; m<100; m++){
	int m1 = (int) (Math.random() * (50));
	int m2 = (int) (Math.random() * (50));
	
	System.out.println(m1);
	System.out.println(m2);
	image.setRGB(m1, m2, vert);}
	
		try {
				
			    File fic= new File("im100.png");
			    ImageIO.write(image, "png", fic);}
			catch (IOException e) {
			    e.printStackTrace();
			}
		Plante p = new Plante(null);
		BufferedImage br = ImageIO.read(new File("im100.png"));
		ArrayList<Pixel> tab = p.photoToPlant2(br);
		p.setPlante(tab);
		p.setEpsilon(0.0001);
		p.setK(3);
		p.kmoyenne3();
		System.out.println("fin");

	}
	

}

