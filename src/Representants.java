import java.util.ArrayList;


public class Representants {
	private ArrayList<Pixel> centroides;
	private int k;
	
	public int getk2(){
		return this.k;
	}
	
	public Representants(ArrayList<Pixel> centroides, int k){
		this.centroides = centroides ;
		this.k = k;
		
	}
	
	public ArrayList<Pixel> getCentroides(){
		return centroides;
	}
	
	public void add(int i,Pixel p){
		this.centroides.add(i,p);
	}
	
	public void remove(int i){
		this.centroides.remove(i);
	}
	
	public void set(int i, Pixel p){
		this.set(i, p);
	}
	
	public ArrayList<Double> buildComparison(ArrayList<Pixel> A){
		ArrayList<Double> comp= new ArrayList<Double>();
		for(int i=0 ; i<this.k ; i++ ){
			comp.add(i, new Double(A.get(i).distance(this.centroides.get(i))));	
			System.out.println(A.get(i).distance(this.centroides.get(i)));
}
		
		return comp;
		
	}
	
	public boolean test(ArrayList<Pixel> A, double epsilon){
		int indic=-1 ;
		ArrayList<Double> comp = this.buildComparison(A);
		
		for(int i=0;i<this.centroides.size();i++){
			System.out.println(comp.get(i).doubleValue()>epsilon);
			if(comp.get(i).doubleValue()>epsilon){
				indic = indic + 1;
			}
		}
		

		if(indic>-1){
			return true;
		}
		
		else {return false;}
	}
	
	
	

}
