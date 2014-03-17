import java.util.ArrayList;


public class Moyenne {
	
	private Pixel centroide;
	private ArrayList<Pixel> groupe;
		
	public Moyenne(ArrayList<Pixel> groupe){
		this.groupe=groupe;
		double R= groupe.get(0).getB();
		double IR= groupe.get(0).getIR();
		double B= groupe.get(0).getB();
		double NDVI= groupe.get(0).getNDVI();
		
	for(int i=0;i<groupe.size();i++){
		R = R + groupe.get(i).getR();
		B = B + groupe.get(i).getB();
		IR= IR + groupe.get(i).getIR();
		NDVI= NDVI + groupe.get(i).getNDVI();
	}
	
	R = R / groupe.size();
	B = B / groupe.size();
	IR = IR / groupe.size();
	NDVI = NDVI / groupe.size();
	
	this.centroide = new Pixel(R,B,NDVI,IR);

	}
	
	public Pixel getCentroide(){
		return centroide;
	}
	
	public ArrayList<Pixel> getGroupe(){
		return this.groupe;
	}
	}

