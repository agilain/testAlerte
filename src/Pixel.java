import java.util.ArrayList;
import java.util.Collections;
import javax.media.jai.JAI;
import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.Externalizable;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.Math.*;


public class Pixel implements Comparable, Externalizable {

	private double R;
	private double B;
	private double NDVI;
	private double IR;
	private int numgroupe;
	private double eloignement;
	private String cluster ;
	private static ArrayList<Pixel> base = new ArrayList<Pixel>() ;
	private static ArrayList<String> names = new ArrayList<String>();
	
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
	
public void photoToPlant2(BufferedImage photo, String type){
		
		
	
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Color c = new Color(photo.getRGB(i, j));
				
				base.add(new Pixel(c.getRed(), c.getBlue(), c.getRGB(), c.getBlue()));
				names.add(type);
		
			}
		}

		
	}

public void setbase2() throws IOException{
	BufferedImage temoinSain = ImageIO.read(new File("data/init/fsain1.png"));
	BufferedImage temoinSainBis = ImageIO.read(new File("data/init/sainbis.png"));
	BufferedImage temoinMalade = ImageIO.read(new File("data/init/maladebis.png"));
	BufferedImage temoinMaladeBis = ImageIO.read(new File("data/init/maladet2.png"));
	BufferedImage mur1 = ImageIO.read(new File("data/init/fmur1.png"));
	BufferedImage mur2 = ImageIO.read(new File("data/init/fmur2.png"));	
	this.photoToPlant2(temoinSain, "sain1");
	this.photoToPlant2(temoinSainBis, "sain2");
	this.photoToPlant2(temoinMalade, "malade1");
	this.photoToPlant2(temoinMaladeBis, "malade2");
	this.photoToPlant2(mur1, "mur1");
	this.photoToPlant2(mur2, "mur2");
	
	}
	
	public void setbase() throws IOException{
		BufferedImage temoinSain = ImageIO.read(new File("data/init/sain1.png"));
		BufferedImage temoinSainBis = ImageIO.read(new File("data/init/sainbis.png"));
		BufferedImage temoinMalade = ImageIO.read(new File("data/init/maladebis.png"));
		BufferedImage temoinMaladeBis = ImageIO.read(new File("data/init/maladet2.png"));
		BufferedImage mur1 = ImageIO.read(new File("data/init/mur1.png"));
		BufferedImage mur2 = ImageIO.read(new File("data/init/mur2.png"));	
		Pixel A1 = (new Moyenne(this.photoToPlant(temoinSain))).getCentroide();
		Pixel A2 = (new Moyenne(this.photoToPlant(temoinMalade))).getCentroide();
		Pixel A3 = (new Moyenne(this.photoToPlant(temoinMaladeBis))).getCentroide();
		Pixel A4 = (new Moyenne(this.photoToPlant(temoinSainBis))).getCentroide();
		Pixel A5 = (new Moyenne(this.photoToPlant(mur1))).getCentroide();
		Pixel A6 = (new Moyenne(this.photoToPlant(mur2))).getCentroide();
		base.add(A1);
		names.add(0,"sain1");
		base.add(A2);
		names.add(1, "malade1");
		base.add(A3);
		names.add(2, "malade2");
		base.add(A4);
		names.add(3, "sain2");
		base.add(A5);
		names.add(4, "mur1");
		base.add(A6);
		names.add(5, "mur2");
	}
	
	public double getEloignement(){
		return this.eloignement;
	}
	
	public void setEloignement(double d){
		this.eloignement =d;
	}

	private int numgroupe2;
	private double distance = 0;
	
	public Pixel(){
		
	}
	
	
	public Pixel(double R, double B, double NDVI, double IR){
		this.R=R;
		this.B=B;
		this.NDVI=NDVI;
		this.IR=IR;
	}
	
	public Pixel(double R, double B, double NDVI, double IR, int numGroupe){
		this.R=R;
		this.B=B;
		this.NDVI=NDVI;
		this.IR=IR;
		this.numgroupe= numGroupe ;
	}
	
	public Pixel(double R, double B, double NDVI, double IR, int numGroupe, String cluster){
		this.R=R;
		this.B=B;
		this.NDVI=NDVI;
		this.IR=IR;
		this.numgroupe= numGroupe ;
		this.cluster = cluster ;
	}
	
	
	public double distance(Pixel pixel){
		return  Math.abs(this.IR-pixel.getIR()); //Math.abs(this.NDVI-pixel.getNDVI()) ;
	}
	
	public void setGroupe(int newnum){
		this.numgroupe=newnum;
	}
	
	public void setGroupe2(int newnum){
			this.numgroupe2=newnum;
			
	}
	public void setDistance(double newdistance){
			this.distance=newdistance;
	}
	
	public int getnumGroupe(){
		return this.numgroupe;
	}
		
	public int getnumGroupe2(){
		return this.numgroupe2;
	}
	
	public double getDistance(){
		return distance;
	}
	
	public double getR(){
		return this.R;
	}
	
	public double getB(){
		return this.B;
	}
	
	public double getNDVI(){
		return this.NDVI;
	}
	
	public double getIR(){
		return this.IR;
	}
	
	public int compareTo(Object p){	
		Pixel m = (Pixel) p;
		if (this.distance<m.getDistance()) return -1; 
		else if(this.distance == m.getDistance()) return 0; 
		else return 1;
	}
	
	public double distance2(Pixel m){
		return Math.sqrt((this.NDVI-m.getNDVI())*(this.NDVI-m.getNDVI())+(this.B-m.getB())*(this.B-m.getB())+(this.IR-m.getIR())*(this.IR-m.getIR())+ (this.R-m.getR())*(this.R-m.getR()));  // EXEMPLE DE DISTANCE
	}
	
	public int kppvPixel(int k) throws IOException{
		
		this.setbase2();
		
		double n=0 ;

		for(int i=0;i<base.size();i++){
			base.get(i).setDistance(this.distance(base.get(i))); 
			// calcul de la distance entre le pixel qu'on veut classer et chaque pixel de la base
		}
		
			ArrayList<Integer> indices = new ArrayList<Integer>(10) ; 
			// on va mettre dans ce tableau qu'ont dans "base" les 20 plus proches voisins du pixel
			for(int i=0 ; i<10 ; i++){
			indices.add(0);
		}
		
		/***Pixel le plus proche***/
		double n1=base.get(0).getDistance();
		for(int i=0 ; i<base.size(); i++){
			n= base.get(i).getDistance();
			if(n<n1){
				n1 = n ;
				indices.set(0, i);
				}
		}
		
		
		/****deuxième pixel le plus proche****/
		n1=base.get(0).getDistance();
		for(int i=0 ; i<base.size(); i++){
				n= base.get(i).getDistance();
				
				if(n<n1 & (indices.get(0)!=i)){
					n1 = n ;
					indices.set(1, i);
				}
			}
		
		/***autres pixels les plus proches*****/
		for(int u=2 ; u<10 ; u++){
		n1=base.get(0).getDistance();
			for(int i=0 ; i<base.size(); i++){
			
			n= base.get(i).getDistance();
			if(n<n1 & indices.contains(i)==false){
				n1 = n ;
				indices.set(u, i);
			}
		}
		}
		
		for(int i=0 ; i< indices.size(); i++){
			System.out.println(names.get(indices.get(i)));
		}
		
		
		ArrayList<Integer> B = new ArrayList<Integer>(6) ; // pour chaque étiquette, B compte combien de fois elle apparaît dans les 20 plus proches
		
		for(int i=0 ; i<6 ; i++){
			B.add(0);
		}
		
		int h=0 ;
		
		for(int i=0 ; i< indices.size(); i++){
			if(names.get(indices.get(i))=="sain1"){
				h = B.get(0)+1; 
				B.set(0, h);	
			}
			
			if(names.get(indices.get(i))=="sain2"){
				B.set(1, B.get(1)+1);	
			}
			
			if(names.get(indices.get(i))=="malade1"){
				h = B.get(2)+1;
				B.set(2, h);	
			}
			
			if(names.get(indices.get(i))=="malade2"){
				h = B.get(3)+1;
				B.set(3, h);	
			}
			
			if(names.get(indices.get(i))=="mur1"){
				h = B.get(4)+1;
				B.set(4, h);	
			}
			
			if(names.get(indices.get(i))=="mur2"){
				h = B.get(5)+1;
				B.set(5, h);	
			}		
		}
		
		for(int i=0 ; i<B.size(); i++){
			System.out.println(B.get(i));
		}
		
		/**Recherche du max de B**/
		int v1=0 ;
			int v2=0 ;
			for(int u=0 ; u<B.size(); u++){
				if(B.get(u)>v1){
					v1 = B.get(u);
					v2 = u ;
				}
				
			}
			
			if(v2==0){
				System.out.println("sain1");
			}
			
			if(v2==1){
				System.out.println("sain2");
			}
			
			if(v2==2){
				System.out.println("malade1");
			}
			
			if(v2==3){
				System.out.println("malade2");
			}
			
			if(v2==4){
				System.out.println("mur1");
			}
			
			if(v2==5){
				System.out.println("mur2");
			}
			
			return v2 ;
		
		//int p=0;
		//for(int i=0;i<k-1;i++){
			//p=((Pixel) baseDApprentissageTest.get(i)).getnumGroupe2()+p;   //pk du cast ici ?
		//}
		
	//	if(p>0) this.setGroupe2(1);
		//if(p<0) this.setGroupe2(-1);
		//if(p==0) this.kppvPixel(k+1);
	}
	
public void kppvPixel1(int k) throws IOException{
		
		//CREATION DUN ARRAYLIST baseDApprentissage QUI SERVIRA D EXEMPLE
		
		ArrayList<Pixel> baseDApprentissage = new ArrayList<Pixel>();
			
		//pour chaque pixel i appartenant � la base d'apprentissage (=bc de photos jor 100, 200)
		//on calcule la distance au pixel fixe et on change l'attribut distance du pixel i
		//base d'apprentissage = arraylist contenant tous les pixels de la base d'apprentissage
					
	for(int i=0;i<baseDApprentissage.size()-1;i++)
		baseDApprentissage.get(i).setDistance(baseDApprentissage.get(i).distance(this));
			
		//on doit copier la base d'apprentissage d'abord (car on va trier le tableau)
			
		ArrayList<Pixel> baseDApprentissageTest = new ArrayList<Pixel>(baseDApprentissage);
			
		//on trie les pixels de la base d'apprentissage test avec Collections.sort
			
		Collections.sort(baseDApprentissageTest);		
		
		int p=0;
		for(int i=0;i<k-1;i++){
			p=((Pixel) baseDApprentissageTest.get(i)).getnumGroupe2()+p;   //pk du cast ici ?
		}
		
		if(p>0) this.setGroupe2(1);
		if(p<0) this.setGroupe2(-1);
		if(p==0) this.kppvPixel(k+1);
	}
	
	
	
	
	
	public String toString(){
		return "IR="+this.IR+"\n"+"NDVI="+this.NDVI+"\n"+"R="+this.R+"\n"+"B="+this.B+"\n"+"Groupe="+this.numgroupe+"\n";
	}

	@Override
	public void readExternal(ObjectInput arg0) throws IOException,
			ClassNotFoundException {
		this.R = arg0.readDouble();
		this.B = arg0.readDouble();
		this.NDVI = arg0.readDouble();
		this.IR = arg0.readDouble();
		this.numgroupe = arg0.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput arg0) throws IOException {
		arg0.writeDouble(this.R);
		arg0.writeDouble(this.B);
		arg0.writeDouble(this.NDVI);
		arg0.writeDouble(this.IR);
		arg0.writeInt(this.numgroupe);
	}

}
