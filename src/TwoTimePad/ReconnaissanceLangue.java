package TwoTimePad;

import java.util.List;
import java.util.TreeMap;

public class ReconnaissanceLangue {

	public static int reconnaissance(List<String> C, String file) {
		
		TreeMap<String,Integer> map = new TreeMap<>();
		
		map = Possiblités.fileToMap(3, file);
		
		int somme = 0;
		for(int i = 0 ; i < C.size() ; i++) {
			if(map.containsKey(C.get(i))) {
				
				somme = somme + map.get(C.get(i));
			}
		}
		
		
		return somme;
	}
	
	public static void main(String[] args) {
		
		final String [] files = {
				"./DicoNgramsReconnaissance/Français.txt",
				"./DicoNgramsReconnaissance/Italien.txt",
		};
		
		String fileFr = "./DicoNgramsReconnaissance/Français.txt";
		//String fileAn = "./DicoNgramsReconnaissance/Anglais.txt";
		String fileEsp = "./DicoNgramsReconnaissance/Espagnol.txt";
		String fileIt = "./DicoNgramsReconnaissance/Italien.txt";
		String fileAl = "./DicoNgramsReconnaissance/Allemand.txt";
		String fileLt = "./DicoNgramsReconnaissance/Lituanien.txt";
		
		
		
		// Les chaines a chiffrés pour faire les tests !  
		
		String m1 = "a su sombra, aunque desnudo de aquel precioso";
		String m2 = "andar vestidas las obras que se componen en a";
		
		
		//String m1 = "";
		//String m2 = "";
				
		
		
		String C = Chiffrement.XOR(m1, m2);
		
		List<String> ls = Chiffrement.ngrams(3, C);
		
		int sommeFR = reconnaissance(ls, fileFr);
		System.out.println("Français :"+ sommeFR);
		
//		int sommeAn = reconnaissance(ls, fileAn);
//		System.out.println("Anglais :"+ sommeAn);
//		
		int sommeEsp = reconnaissance(ls, fileEsp);
		System.out.println("Espagnol :"+ sommeEsp);   
		
		int sommeIt = reconnaissance(ls, fileIt);
		System.out.println("Italien :"+ sommeIt); 
		
		int sommeAl = reconnaissance(ls, fileAl);
		System.out.println("Allemand :"+ sommeAl);
		
		int sommeLt = reconnaissance(ls,fileLt);
		System.out.println("Lituanien :"+sommeLt);  
		
		
		
		
		
	}
	
	
}
