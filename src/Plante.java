import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.*;

import javax.imageio.ImageIO;

class Plante {

	private ArrayList<Pixel> plante = new ArrayList<Pixel>();
	private int k;
	private double epsilon;
	private ArrayList<ArrayList<Pixel>> H = new ArrayList<ArrayList<Pixel>>(k);
	private ArrayList<Moyenne> H2 = new ArrayList<Moyenne>(k);
	private static BufferedImage temoinSain ;
	private static BufferedImage temoinMalade  ;
	private static BufferedImage temoinSainBis ;
	private static BufferedImage temoinMaladeBis ;
	private static BufferedImage mur1 ;
	private static BufferedImage mur2 ;
	private Plante plant3 ;
	private static ArrayList<String> cluster ;
	private int sep11 ;
	private int sep12 ;
	private int sep21 ;
	private int sep22 ;

	public Plante getPlant3(){
		return this.plant3;
	}
	
	
	public static void setTemoin() throws IOException {
		temoinSain = ImageIO.read(new File("data/init/sain1.png"));
		temoinSainBis = ImageIO.read(new File("data/init/sainbis.png"));
		temoinMalade = ImageIO.read(new File("data/init/maladebis.png"));
		temoinMaladeBis = ImageIO.read(new File("data/init/maladet2.png"));
		mur1 = ImageIO.read(new File("data/init/mur1.png"));
		mur2 = ImageIO.read(new File("data/init/mur2.png"));
	}
	
	public ArrayList<Pixel> photoToPlant2(BufferedImage photo){
		ArrayList<Pixel> tableau2 = new ArrayList<Pixel>();
		int m=0 ;
		for (int i = 0; i < 200; i++) {
			for (int j = 0; j < 200; j++) {
				Color c = new Color(photo.getRGB(i, j));
				tableau2.add(m,new Pixel(c.getRed(), c.getBlue(), c.getBlue(),
								Math.abs((c.getBlue()-c.getRed())/(c.getBlue()+c.getRed()))));
				m++;
			}
		}

		return tableau2;
	}
	
	public ArrayList<Pixel> photoToPlant(BufferedImage photo){
		
		ArrayList<Pixel> tableau2 = new ArrayList<Pixel>();
		int m=0 ;
		for (int i = 0; i < 200; i++) {
			for (int j = 0; j < 200; j++) {
				Color c = new Color(photo.getRGB(i, j));
				
				tableau2.add(m,new Pixel(c.getRed(), c.getBlue(), c.getRGB(), c.getBlue()));
				m++;
			}
		}

		return tableau2;
	}
	
	public void rectangle(String nomImage) throws IOException{
		BufferedImage newImage = ImageIO.read(new File(nomImage));
		int h1 = (newImage.getWidth()/20) ; // côté d'un carré
		int h2 = (newImage.getWidth()/20) * 20  ;
		int h3= h1/10 ;
		
		
		
		ArrayList<Rectangle> R = new ArrayList<Rectangle>() ;
		ArrayList<ArrayList<Pixel>> A = new ArrayList<ArrayList<Pixel>>();
		
		int k=0 ;
		for(int i=0 ; i< h2 ; i=i+h1){
			for(int j=0 ; j< h2 ; j=j+h1){
				R.add(k, new Rectangle(i,j,h1,h1));
				k++ ;
			}
		}
		
		for(int u=0 ; u<R.size() ; u++){
			A.add(new ArrayList<Pixel>());
		}
		
	
		for(int u=0 ; u< R.size(); u++){
		for(int i=0 ; i<h2-1 ; i=i+h3 ){
			for(int j=0 ; j<h2-1 ; j=j+h3){
				 
				if(R.get(u).contains(i,j)){
					//System.out.println(i+"et"+j);
					Color c = new Color(newImage.getRGB(i, j));
					A.get(u).add(new Pixel(c.getRed(),c.getBlue(),  newImage.getRGB(i, j), c.getBlue() )) ;
					
				}
			}
			
		}
		}
		
		
		
		ObjectInputStream ois ; 
		ObjectOutputStream oos ;
		
		for (int j = 0; j < R.size(); j++) {
			System.out.println(A.get(j).size());
		}
		
		try{
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("donnees2.txt"))));
			for (int j = 0; j < R.size(); j++) {
			oos.writeObject(new Moyenne(A.get(j)));
		}
			oos.close();
		
		}
		
		catch (FileNotFoundException e) {
		      e.printStackTrace();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }     	
		
		try{
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("donnees2.txt")))) ;
			  try {
			        System.out.println("Affichage des moyennes :");
			        System.out.println("*************************\n");
			        for (int j = 0; j < R.size(); j++) {
			        System.out.println(((Moyenne)ois.readObject()).toString());
			        }
			      } catch (ClassNotFoundException e) {
			        e.printStackTrace();
			      }
				
			      ois.close();
		}
		
			
		catch (FileNotFoundException e) {
		      e.printStackTrace();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }     	
		
		//BufferedImage image= new BufferedImage((int)(newImage.getWidth()), (int)(newImage.getHeight()), BufferedImage.TYPE_INT_RGB);
		try {
			for(int i=0 ; i<newImage.getWidth();i++){
				for(int j=0 ; j<newImage.getHeight();j++){
				if(i%h1 == 0 | j%h1==0){
					newImage.setRGB(i, j, 255);
					
				}
				}
			}
		    File fic= new File("grille.png");
		    ImageIO.write(newImage, "png", fic);}
		catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
	
	public void setPlante(ArrayList<Pixel> newplante) {
		this.plante = newplante;
	}

	public ArrayList<Moyenne> getH2() {
		return this.H2;
	}

	public void setK(int newk) {
		k = newk;
	}

	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}

	public ArrayList<ArrayList<Pixel>> getH() {
		return this.H;
	}

	public ArrayList<Pixel> getPlante() {
		return this.plante;
	}

	public Plante(ArrayList<Pixel> plante) {
		this.plante = plante;
	}

	public void kmoyenne() {
		int nbPixel = plante.size();
		ArrayList<Integer> random = new ArrayList<Integer>();
		while (random.size() < k) {
			int m1 = (int) (Math.random() * (nbPixel));
			if (random.contains(m1) == false) {
				random.add(m1);
			}
		}

		ArrayList<Pixel> R1 = new ArrayList<Pixel>(k);
		for (int i = 0; i < k; i++) {
			R1.add(this.plante.get((random.get(i)).intValue()));
		}

		ArrayList<Pixel> R = new ArrayList<Pixel>(k);

		for (int i = 0; i < k; i++) {
			R.add(i, R1.get(i)); // initialisation des représentants des groupes
		}

		Representants R3 = new Representants(R, this.k);

		int lanceurAlgo = 0;

		for (int j = 0; j < plante.size(); j++) {
			plante.get(j).setGroupe(0);
			plante.get(j).setEloignement(plante.get(j).distance(R1.get(0)));

		}

		while ((lanceurAlgo == 0) | (R3.test(R1, epsilon) == true)) {
			System.out.println("boucle");
			for (int i = 0; i < k; i++) {
				R3.remove(i);
				R3.add(i, R1.get(i));
			}

			for (int j = 0; j < plante.size(); j++) {
				for (int u = 0; u < k; u++) {
					if (plante.get(j).distance(R1.get(u)) < plante.get(j)
							.getEloignement()) {
						plante.get(j).setEloignement(
								plante.get(j).distance(R1.get(u)));
						plante.get(j).setGroupe(u);
					}
				}
			}

			ArrayList<Pixel> a = new ArrayList<Pixel>(k);
			for (int u = 0; u < k; u++) {
				H.add(a);
			}

			for (int u = 0; u < k; u++) {
				for (int j = 0; j < this.plante.size(); j++) {
					if (this.plante.get(j).getnumGroupe() == u) {
						H.get(u).add(this.plante.get(j));
					}
				}
			}

			for (int u = 0; u < k; u++) {
				H2.add(u, new Moyenne(H.get(u)));
			}

			for (int u = 0; u < k; u++) {
				R1.add(H2.get(u).getCentroide());
			}

			lanceurAlgo++;

		}

		System.out.println("results");
		System.out.println(plante.size());
		for (int j = 0; j < plante.size(); j++) {
			System.out.println(plante.get(j).getnumGroupe());
		}

		System.out.println(R3.test(R1, epsilon));

	}
	
	public void kmoyenne3() {
		//int nbPixel = plante.size();
		ArrayList<Pixel> R1 = new ArrayList<Pixel>(k);
		R1.add(new Pixel(255,0,0,0));
		R1.add(new Pixel(67,200,58,0));
		R1.add(new Pixel(0,0,255,0));
		

		ArrayList<Pixel> R = new ArrayList<Pixel>(k);

		for (int i = 0; i < k; i++) {
			R.add(i, R1.get(i)); // initialisation des représentants des groupes
		}

		Representants R3 = new Representants(R, this.k);

		int lanceurAlgo = 0;

		for (int j = 0; j < plante.size(); j++) {
			plante.get(j).setGroupe(0);
			plante.get(j).setEloignement(plante.get(j).distance(R1.get(0)));

		}

		while ((lanceurAlgo == 0) | (R3.test(R1, epsilon) == true)) {
			System.out.println("boucle");
			for (int i = 0; i < k; i++) {
				R3.remove(i);
				R3.add(i, R1.get(i));
			}

			for (int j = 0; j < plante.size(); j++) {
				for (int u = 0; u < k; u++) {
					if (plante.get(j).distance(R1.get(u)) < plante.get(j)
							.getEloignement()) {
						plante.get(j).setEloignement(
								plante.get(j).distance(R1.get(u)));
						plante.get(j).setGroupe(u);
					}
				}
			}

			ArrayList<Pixel> a = new ArrayList<Pixel>(k);
			for (int u = 0; u < k; u++) {
				H.add(a);
			}

			for (int u = 0; u < k; u++) {
				for (int j = 0; j < this.plante.size(); j++) {
					if (this.plante.get(j).getnumGroupe() == u) {
						H.get(u).add(this.plante.get(j));
					}
				}
			}

			for (int u = 0; u < k; u++) {
				H2.add(u, new Moyenne(H.get(u)));
			}

			for (int u = 0; u < k; u++) {
				R1.add(H2.get(u).getCentroide());
			}

			lanceurAlgo++;

		}

		System.out.println("results");
		for (int j = 0; j < plante.size(); j++) {
			System.out.println(plante.get(j).getnumGroupe());
		}

		System.out.println(R3.test(R1, epsilon));

	}

	public Sante kmoyenne2(int entier) throws IOException {
		this.k = entier;
		int nbPixel = plante.size();
		ArrayList<Integer> random = new ArrayList<Integer>();
		while (random.size() < k) {
			int m1 = (int) (Math.random() * (nbPixel));
			if (random.contains(m1) == false) {
				random.add(m1);
			}
		}

		ArrayList<Pixel> R1 = new ArrayList<Pixel>(k);
		
		this.setTemoin();
		Moyenne repSain1 = new Moyenne(this.photoToPlant(temoinSain));
		Moyenne repMalade1 = new Moyenne(this.photoToPlant(temoinMalade));
		Moyenne repSainBis = new Moyenne(this.photoToPlant(temoinSainBis));
		Moyenne repMaladeBis = new Moyenne(this.photoToPlant(temoinMaladeBis));
		Moyenne repmur1 = new Moyenne(this.photoToPlant(mur1));
		Moyenne repmur2 = new Moyenne(this.photoToPlant(mur2));
		
		Pixel pixelSain1 = repSain1.getCentroide();
		Pixel pixelMalade1 = repMalade1.getCentroide();
		Pixel pixelMalade2 = repMaladeBis.getCentroide();
		Pixel pixelSain2 = repSainBis.getCentroide();
		Pixel pixelMur1 = repmur1.getCentroide();
		Pixel pixelMur2 = repmur2.getCentroide();
		
		R1.add(0, pixelSain1);
		R1.add(1, pixelMalade1);
		R1.add(2, pixelSain2);
		R1.add(3,pixelMalade2);
		R1.add(4, pixelMur1);
		R1.add(5, pixelMur2);
		
		
		for (int i = 6; i < k; i++) {
			R1.add(this.plante.get((random.get(i)).intValue()));
		}

		ArrayList<Pixel> R = new ArrayList<Pixel>(k);

		for (int i = 0; i < k; i++) {
			R.add(i, R1.get(i)); // initialisation des représentants des groupes
		}

		Representants R3 = new Representants(R, this.k);

		int lanceurAlgo = 0;

		for (int j = 0; j < plante.size(); j++) {
			plante.get(j).setGroupe(0);
			plante.get(j).setEloignement(plante.get(j).distance(R1.get(0)));

		}

		while ((lanceurAlgo == 0) | (R3.test(R1, epsilon) == true)) {
			System.out.println("boucle");
			for (int i = 0; i < k; i++) {
				R3.remove(i);
				R3.add(i, R1.get(i)); // sauvegarde des représentants de la
				// boucle précédente dans R3
				// on peut ensuite modifier R1 dans ce nouveau passage de la
				// boucle
			}

			for (int j = 0; j < plante.size(); j++) {
				for (int u = 0; u < k; u++) {
					if (plante.get(j).distance(R1.get(u)) < plante.get(j)
							.getEloignement()) {
						plante.get(j).setEloignement(
								plante.get(j).distance(R1.get(u)));
						plante.get(j).setGroupe(u);
					}
				}
			}

			ArrayList<Pixel> a = new ArrayList<Pixel>(k);
			for (int u = 0; u < k; u++) {
				H.add(a);
			}

			for (int u = 0; u < k; u++) {
				for (int j = 0; j < this.plante.size(); j++) {
					if (this.plante.get(j).getnumGroupe() == u) {
						H.get(u).add(this.plante.get(j));
					}
				}
			}

			for (int u = 0; u < k; u++) {
				H2.add(u, new Moyenne(H.get(u)));
			}

			for (int u = 0; u < k; u++) {
				R1.add(H2.get(u).getCentroide());
			}

			lanceurAlgo++;

		}

		int taille = 0;
		int indic = -1;
		for (int u = 0; u < k; u++) {
			if (H.get(u).size() > taille) {
				taille = H.get(u).size();
				indic = u;
			}
		}

		System.out.println("results");
		System.out.println(plante.size());
		
		ObjectInputStream ois ; 
		ObjectOutputStream oos ;
		
		try{
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("donnees2.txt"))));
			for (int j = 0; j < plante.size(); j++) {
			oos.writeObject(new Pixel(plante.get(j).getR(),plante.get(j).getB(),plante.get(j).getNDVI(), plante.get(j).getIR(), plante.get(j).getnumGroupe()));
		}
			oos.close();
		
		}
		
		catch (FileNotFoundException e) {
		      e.printStackTrace();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }     	
		
		try{
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("donnees2.txt")))) ;
			  try {
			        System.out.println("Affichage des pixels :");
			        System.out.println("*************************\n");
			       for (int j = 0; j < plante.size(); j++) {
			        System.out.println(((Pixel)ois.readObject()).toString());
			       }
			      } catch (ClassNotFoundException e) {
			        e.printStackTrace();
			      }
				
			      ois.close();
		}
		
			
		catch (FileNotFoundException e) {
		      e.printStackTrace();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }     	
		
		//for (int j = 0; j < plante.size(); j++) {
			//System.out.println(plante.get(j).getnumGroupe() +" "+ plante.get(j).getIR()+ " " + plante.get(j).getNDVI() );
		//}
		

		System.out.println(R3.test(R1, epsilon));
		
		this.setTemoin();

		Moyenne repSain = new Moyenne(this.photoToPlant(temoinSain));
		Moyenne repMalade = new Moyenne(this.photoToPlant(temoinMalade));
		
		Pixel pixelSain = repSain.getCentroide();
		//Pixel pixelSain = new Pixel(0, 0, -1.2369085E7,68);
		Pixel pixelMalade = repMalade.getCentroide();
		//Pixel pixelMalade = new Pixel(0,0,64,-1.17E7);
		double d1 = H2.get(indic).getCentroide().distance(pixelSain);
		double d2 = H2.get(indic).getCentroide().distance(pixelMalade);

		if (d1 <= d2) {
			System.out.println("Votre plante est en bonne santé");
			return Sante.SAIN;
		} else {
			System.out.println("Votre plante est malade");
			return Sante.MALADE;
		}

	}
	
	
	
	public void setPlante3(String NomPhotoRVB)
	throws IOException {
		BufferedImage newImage = ImageIO.read(new File(NomPhotoRVB));
		
		//int w = newImage.getWidth();
		//int h = newImage.getHeight();
		//BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		//AffineTransform at = new AffineTransform();
		//at.scale(w/200, h/200);
		//AffineTransformOp scaleOp = 
		//new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		//after = scaleOp.filter(newImage, after);
		
		
		int m = 0;
		ArrayList<Pixel> tableau2 = new ArrayList<Pixel>();
		for (int i = 0; i < newImage.getWidth(); i++) {
			for (int j = 0; j < newImage.getHeight(); j++) {
				Color c = new Color(newImage.getRGB(i, j));
				tableau2.add(m, new Pixel(c.getRed(), c.getBlue(), newImage.getRGB(i, j), c.getBlue()));
				//tableau2.add(m, new Pixel(c.getRed(), c.getGreen(),
								//((c.getRed()-c.getBlue())/(c.getRed()+c.getBlue()))*127+1,c.getBlue()));
				m++;
			}
		}
		Plante plant2 = new Plante(tableau2);
		this.plant3 = plant2;
		plant3.setK(4);
		plant3.setEpsilon(0.0001);
		
	}
	
	public void setPlante32(String NomPhotoRVB1, String NomPhotoRVB2)
			throws IOException {
				BufferedImage newImage1 = ImageIO.read(new File(NomPhotoRVB1));
				BufferedImage newImage2 = ImageIO.read(new File(NomPhotoRVB2));
				
				int m = 0;
				ArrayList<Pixel> tableau2 = new ArrayList<Pixel>();
				sep11 = newImage1.getWidth() ;
				sep12 = newImage1.getHeight() ;
				sep21 = newImage2.getWidth();
				sep22 = newImage2.getHeight() ;
				for (int i = 0; i < newImage1.getWidth(); i++) {
					for (int j = 0; j < newImage1.getHeight(); j++) {
						Color c = new Color(newImage1.getRGB(i, j));
						tableau2.add(m, new Pixel(c.getRed(), c.getBlue(), newImage1.getRGB(i, j), c.getBlue()));
						//tableau2.add(m, new Pixel(c.getRed(), c.getGreen(),
										//((c.getRed()-c.getBlue())/(c.getRed()+c.getBlue()))*127+1,c.getBlue()));
						m++;
					}
				}
				System.out.println(tableau2.size());
				
						
				for (int i = 0; i < newImage2.getWidth(); i++) {
					for (int j = 0; j < newImage2.getHeight(); j++) {
						Color c = new Color(newImage2.getRGB(i, j));
						tableau2.add(m, new Pixel(c.getRed(), c.getBlue(), newImage2.getRGB(i, j), c.getBlue()));
						//tableau2.add(m, new Pixel(c.getRed(), c.getGreen(),
										//((c.getRed()-c.getBlue())/(c.getRed()+c.getBlue()))*127+1,c.getBlue()));
						m++;
					}
				}
				
				System.out.println(tableau2.size());
				Plante plant2 = new Plante(tableau2);
				this.plant3 = plant2;
				plant3.setK(6);
				plant3.setEpsilon(0.0001);
				
			}
	
	public void imagedResult(String photoRVB) throws IOException{
			
		BufferedImage image= new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);

		
		for(int i=0;i<200*200; i++){
			if(plant3.getPlante().get(i).getnumGroupe()==0){
				image.setRGB(i/200, i%200, 255);
			}
			
			if(plant3.getPlante().get(i).getnumGroupe()==1){
				image.setRGB(i/200, i%200, (11 << 16) + (67 << 8) + 0);
			}
			
			if(plant3.getPlante().get(i).getnumGroupe()==2){
				image.setRGB(i/200, i%200, (200 << 16 + (0<<8) + 0));
			} 
			
			if(plant3.getPlante().get(i).getnumGroupe()==3){
				image.setRGB(i/200, i%200, (225<<16) + (195<<8)+ (5));
			}
			
			if(plant3.getPlante().get(i).getnumGroupe()==4){
				image.setRGB(i/200, i%200, (203<<16 + (225))); // violet
			}
			
			if(plant3.getPlante().get(i).getnumGroupe()==5){
				image.setRGB(i/200, i%200, (200 << 16) + (244 << 8) + 200);
			}
			
		}
		
		try {
			
		    File fic= new File("resultat.png");
		    ImageIO.write(image, "png", fic);}
		catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public void imagedResult2(String photoRVB) throws IOException{
		BufferedImage newImage = ImageIO.read(new File(photoRVB));
			
		BufferedImage image= new BufferedImage((int)(1.5*newImage.getWidth()), (int)(1.5*newImage.getHeight()), BufferedImage.TYPE_INT_RGB);

		
		for(int i=0;i<newImage.getWidth()*newImage.getHeight(); i++){
			if(plant3.getPlante().get(i).getnumGroupe()==0){
				image.setRGB(i/newImage.getWidth(), i%newImage.getHeight(), 255);
			}
			
			if(plant3.getPlante().get(i).getnumGroupe()==1){
				image.setRGB(i/newImage.getWidth(), i%newImage.getHeight(), (11 << 16) + (67 << 8) + 0);
			}
			
			if(plant3.getPlante().get(i).getnumGroupe()==2){
				image.setRGB(i/newImage.getWidth(), i%newImage.getHeight(), (200 << 16 + (0<<8) + 0));
			} 
			
			if(plant3.getPlante().get(i).getnumGroupe()==3){
				image.setRGB(i/newImage.getWidth(), i%newImage.getHeight(), (225<<16) + (195<<8)+ (5));
			}
			
			if(plant3.getPlante().get(i).getnumGroupe()==4){
				image.setRGB(i/newImage.getWidth(), i%newImage.getHeight(), (4<<16 + (5)));
			}
			
			if(plant3.getPlante().get(i).getnumGroupe()==5){
				image.setRGB(i/newImage.getWidth(), i%newImage.getHeight(), (200 << 16) + (244 << 8) + 200);
			}
			
		}
		
		try {
			
		    File fic= new File("resultat.png");
		    ImageIO.write(image, "png", fic);}
		catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public void imagedResult22(String photoRVB1, String photoRVB2) throws IOException{
		BufferedImage newImage1 = ImageIO.read(new File(photoRVB1));
		BufferedImage newImage2 = ImageIO.read(new File(photoRVB2));
			
		BufferedImage image1= new BufferedImage((int)(3*newImage1.getWidth()), (int)(3*newImage1.getHeight()), BufferedImage.TYPE_INT_RGB);
		BufferedImage image2= new BufferedImage((int)(3*newImage2.getWidth()), (int)(3*newImage2.getHeight()), BufferedImage.TYPE_INT_RGB);

		
		for(int i=0;i<sep11*sep12; i++){
			if(plant3.getPlante().get(i).getnumGroupe()==0){
				image1.setRGB(i/newImage1.getWidth(), i%newImage1.getHeight(), 255);
			}
			
			if(plant3.getPlante().get(i).getnumGroupe()==1){
				image1.setRGB(i/newImage1.getWidth(), i%newImage1.getHeight(), (11 << 16) + (67 << 8) + 0);
			}
			
			if(plant3.getPlante().get(i).getnumGroupe()==2){
				image1.setRGB(i/newImage1.getWidth(), i%newImage1.getHeight(), (200 << 16 + (0<<8) + 0));
			} 
			
			if(plant3.getPlante().get(i).getnumGroupe()==3){
				image1.setRGB(i/newImage1.getWidth(), i%newImage1.getHeight(), (225<<16) + (195<<8)+ (5));
			}
			
			if(plant3.getPlante().get(i).getnumGroupe()==4){
				image1.setRGB(i/newImage1.getWidth(), i%newImage1.getHeight(), (203<<16 + (225)));
			}
			
			if(plant3.getPlante().get(i).getnumGroupe()==5){
				image1.setRGB(i/newImage1.getWidth(), i%newImage1.getHeight(), (200 << 16) + (244 << 8) + 200);
			}
			
		}
		
		System.out.println(newImage1.getWidth()*newImage1.getHeight());
		System.out.println((newImage1.getWidth()*newImage1.getHeight()) + (newImage2.getWidth()*newImage2.getHeight()) );

		
		for(int i=sep11*sep12; i< sep11*sep12 + sep21*sep22 ;  i++){
			if(plant3.getPlante().get(i).getnumGroupe()==0){
				image2.setRGB((i-sep11*sep12)/sep21, (i-sep11*sep12)%sep22, 255);
			}
			
			if(plant3.getPlante().get(i).getnumGroupe()==1){
				image2.setRGB((i-sep11*sep12)/sep21, (i-sep11*sep12)%sep22, (11 << 16) + (67 << 8) + 0);
			}
			
			if(plant3.getPlante().get(i).getnumGroupe()==2){
				image2.setRGB((i-sep11*sep12)/sep21, (i-sep11*sep12)%sep22, (200 << 16 + (0<<8) + 0));
			} 
			
			if(plant3.getPlante().get(i).getnumGroupe()==3){
				image2.setRGB((i-sep11*sep12)/sep21, (i-sep11*sep12)%sep22, (225<<16) + (195<<8)+ (5));
			}
			
			if(plant3.getPlante().get(i).getnumGroupe()==4){
				image2.setRGB((i-sep11*sep12)/sep21, (i-sep11*sep12)%sep22, (203<<16 + (225)));
			}
			
			if(plant3.getPlante().get(i).getnumGroupe()==5){
				image2.setRGB((i-sep11*sep12)/sep21, (i-sep11*sep12)%sep22, (200 << 16) + (244 << 8) + 200);
			}
			
		}
		
		try {
			
		    File fic1= new File("Test3/resultat1.png");
		    File fic2= new File("Test3/resultat2.png");
		    ImageIO.write(image1, "png", fic1);
		    ImageIO.write(image2, "png", fic2);
		    }
		catch (IOException e) {
		    e.printStackTrace();
		}
	}

	public Sante getSante() throws IOException
		{
		
		plant3.setK(6);
		plant3.setEpsilon(0.01);
		Sante A = plant3.kmoyenne2(6);
		if (A == Sante.MALADE) {
			return Sante.MALADE;
		} else {
			return Sante.SAIN;
		}

	}
	
	public Sante getSante2() throws IOException
	{
		plant3.setK(6);
		plant3.setEpsilon(0.01);
		Sante A = plant3.kmoyenne2(6);
		if (A == Sante.MALADE) {
			return Sante.MALADE;
		} else {
			return Sante.SAIN;
		}

	
	

	

}

	public int kppvPlante(int k) throws IOException {
		
		ArrayList<Integer> A = new ArrayList<Integer>();
		
		for(int i=0 ; i<plante.size(); i++){
		A.add(0);
		}
		
	for (int i = 0; i < plante.size(); i=i+1000) {
			A.set(i,this.plante.get(i).kppvPixel(k)); // pk faire du cast ici ?
		}
		
		ArrayList<Integer> B = new ArrayList<Integer>(6) ;
		for(int i=0 ; i<6 ; i++){
			B.add(0);
		}
		
		for(int i=0 ; i<plante.size(); i++){
		if(A.get(i)==0){
			int h = B.get(0)+1;
			B.set(0, h);	
		}
		
		if(A.get(i)==1){
			int h = B.get(1)+1;
			B.set(1, h);	
		}
		
		if(A.get(i)==2){
			int h = B.get(2)+1;
			B.set(2, h);	
		}
		
		if(A.get(i)==3){
			int h = B.get(3)+1;
			B.set(3, h);	
		}
		
		if(A.get(i)==4){
			int h = B.get(4)+1;
			B.set(4, h);	
		}
		
		if(A.get(i)==5){
			int h = B.get(5)+1;
			B.set(5, h);	
		}
		
	
		}

		int m1= B.get(0) ;
		int m2=0 ;
		for(int u=1 ; u<6 ; u++){
			if(B.get(u)>m1){
				m1 = B.get(u);
				m2=u ; 
			}
		}
		if(m2==0){
			System.out.println("rsain1");
			//return "rsain1";
		}
		
		if(m2==1){
			System.out.println("rsain2");
			//return "rsain2";
		}
		
		if(m2==2){
			System.out.println("rmalade1");
			//return "rmalade1" ;
		}
		
		if(m2==3){
			System.out.println("rmalade2");
			//return "rmalade2";
		}
		
		if(m2==4){
			System.out.println("rmur1");
			//return "error";
		}
		
		if(m2==5){
			System.out.println("rmur2");
			//return "error";
			
		}
	return m2 ;	
	}
	
	
	public Sante utkppv(int k) throws IOException{
		plant3.setK(6);
		plant3.setEpsilon(0.01);
		int a = plant3.kppvPlante(k);
		if(a==0 | a==1){
			return Sante.SAIN;
		}
		
		
		else{
			return Sante.MALADE ;
		}
		
			
	}
}

// IL RESTE A DEFINIR UNE FONCTION COMMUNE AUX 2 ALGOS A PRIORI QUI DIT SI,
// APRES AVOIR TRIE LES PIXELS D UNE PLANTE
// ,SI ELLE EST MALADE OU NON (choisir un seuil)


