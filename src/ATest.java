import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;


public class ATest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Pixel p = new Pixel(0,0,0,5) ;
		Pixel p1 = new Pixel(0,0,0,100);
		Pixel p2 = new Pixel(0,0,0,5.5);
		Pixel p3 = new Pixel(0,0,0,6);
		Pixel p4 = new Pixel(0,0,0,30);
		Pixel p5 = new Pixel(0,0,0,35);
		Pixel p6 = new Pixel(0,0,0,8);
		Pixel p7 = new Pixel(0,0,0,200);
		Pixel p8 = new Pixel(0,0,0,20);
		Pixel p9 = new Pixel(0,0,0,15);
		Pixel p10 = new Pixel(0,0,0,4.9);
		
		ArrayList<Pixel> base = new ArrayList<Pixel>();
		
		base.add(p1);
		base.add(p2);
		base.add(p3);
		base.add(p4);
		base.add(p5);
		base.add(p6);
		base.add(p7);
		base.add(p8);
		base.add(p9);
		base.add(p10);
		
		for(int i=0;i<base.size();i++){
			base.get(i).setDistance(p.distance(base.get(i))); // calcul de la distance entre le pixel qu'on veut classer et chaque pixel de la base
			}
		
		ArrayList<Integer> indices = new ArrayList<Integer>(20) ; // on va mettre dans ce tableau qu'ont dans "base" les 50 plus proches voisins du pixel
		for(int i=0 ; i<20 ; i++){
			indices.add(0);
		}
		
		double n1=base.get(0).getDistance();
		double n =0 ;
		
		for(int i=0 ; i<base.size(); i++){
			n= base.get(i).getDistance();
			if(n<n1){
				n1 = n ;
				indices.remove(0);
				indices.set(0, i);
				}
		}
		
		//n1=base.get(0).getDistance();
		//for(int i=0 ; i<base.size(); i++){
			//n= base.get(i).getDistance();
			//if((n<n1) & (indices.contains(i))==false){
				//n1 = n ;
				//indices.remove(1);
				//indices.set(1, i);
			//}
		//}
		
		//n1=base.get(0).getDistance();
		//for(int i=0 ; i<base.size(); i++){
			//n= base.get(i).getDistance();
			//if((n<n1) & (indices.contains(i))==false){
				//n1 = n ;
				//indices.remove(2);
				//indices.set(2, i);
			//}
		//}
		
		//for(int i=0 ; i<base.size(); i++){
			//n= base.get(i).getDistance();
			//n1=base.get(0).getDistance();
			//if(n<n1 & indices.contains(i)==false){
				//n1 = n ;
				//indices.remove(2);
				//indices.set(2,i);
			//}
		//}
		
		for(int u=1 ; u<5 ; u++){
			n1=base.get(0).getDistance();
			for(int i=0 ; i<base.size(); i++){
				n= base.get(i).getDistance();
				if(n<n1 & indices.contains(i)==false){
					n1 = n ;
					indices.remove(u);
					indices.set(u, i);
				}
			}
			}
		
	//System.out.println(indices.get(0));
	//System.out.println(indices.get(1));
	//System.out.println(indices.get(2));
	//System.out.println(indices.get(3));
	//System.out.println(indices.get(4));
		
		Pixel p11= new Pixel(0,0,0,200);
		p11.kppvPixel(6);
	
	}
	
	
}
