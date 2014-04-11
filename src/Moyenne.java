import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;


public class Moyenne implements Externalizable {
	
	private Pixel centroide;
	private ArrayList<Pixel> groupe;
	private double meansNDVI ;
	private double meansIR ;
	private double vNDVI;
	private double vIR ;
		
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
	
	this.meansNDVI = NDVI ;
	this.meansIR = IR ;
	this.centroide = new Pixel(R,B,NDVI,IR);
	
	double IR2= Math.pow((groupe.get(0).getIR()-meansIR),2);
	double NDVI2= Math.pow(groupe.get(0).getNDVI()-meansNDVI,2);
	
	for(int i=0;i<groupe.size();i++){
		IR2= IR2 + Math.pow((groupe.get(i).getIR()-meansIR),2);
		NDVI2= NDVI2 + Math.pow((groupe.get(i).getIR()-meansIR),2);
	}
	
	vIR = IR2/groupe.size();
	vNDVI = NDVI2/groupe.size();

	}
	
	public Moyenne(){
		
	}
	
	public Pixel getCentroide(){
		return centroide;
	}
	
	public ArrayList<Pixel> getGroupe(){
		return this.groupe;
	}

	public String toString(){
		return "NDVI="+this.meansNDVI+"\n"+"IR="+this.meansIR+"\n"+"variance NDVI="+this.vNDVI+"\n"+"varianceIR="+this.vIR+"\n";
	}
	
	@Override
	public void readExternal(ObjectInput arg0) throws IOException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		this.meansNDVI = arg0.readDouble();
		this.meansIR = arg0.readDouble();
		this.vIR = arg0.readDouble();
		this.vNDVI = arg0.readDouble();
		
	}

	@Override
	public void writeExternal(ObjectOutput arg0) throws IOException {
		// TODO Auto-generated method stub
	
		arg0.writeDouble(this.meansNDVI);
		arg0.writeDouble(this.meansIR);
		arg0.writeDouble(this.vIR);
		arg0.writeDouble(this.vNDVI);
		
	}
	}

