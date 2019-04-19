package TwoTimePad;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;



public class AutoDecrypt {
	
	public static void AutoDecryption(List<String> Ngrams,String message1, String message2,
			TreeMap<String,Integer> map,List<List<String>> result){
		
		int position = 0; // a vérifié !!
		
		int size = Ngrams.size() + 2;
		
		int proba = (size)/3 ;
		
		if (message1.length() < size)  
			position = message1.length() - 2;
	
		String gram = Ngrams.get(position);
		
		List<List<String>> res = Possiblités.transfert(map, message1, message2, gram);
	
		String m1 ="",m2="";
		
		int nbr; 
		
		
		
		if(res.isEmpty()) {
			System.out.println("en execution ");
			System.out.println();
			
		}else {
			if(res.size() >= 3) nbr = 3; 
			else nbr = res.size();
			
			for(int i = 0 ; i < nbr ; i++) {
				
				if(res.get(i).get(0).substring(0,2).equals(message1.substring(message1.length()-2))){
					
					m1 = message1.concat(""+res.get(i).get(0).charAt(2));
					m2 = message2.concat(""+res.get(i).get(1).charAt(2));
			//		if(m1.length() >= proba)
					  result.add(Arrays.asList(m1,m2));
					
					AutoDecryption(Ngrams,m1,m2,map,result);
					
					
				}
				
				if(res.get(i).get(1).substring(0,2).equals(message1.substring(message1.length()-2))){
					
					m1 = message1.concat(""+res.get(i).get(1).charAt(2));
					m2 = message2.concat(""+res.get(i).get(0).charAt(2));
					
				//	if(m1.length() >= proba)
					  result.add(Arrays.asList(m1,m2));
					
					AutoDecryption(Ngrams,m1,m2,map,result);
					
					
				}
				
			}
			
		}
		
		
		
		
		
	}
	
	
	public static void SaveResult(List<List<String>> result) {
		
		try {
			
			BufferedWriter wr = new BufferedWriter(new FileWriter("./Résultats/possibilités.txt"));
			
			result.forEach(e -> {
				try {
					wr.write(e.get(0).toString()+" <--> "+ e.get(1).toString());
					wr.write("\n");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			wr.flush();
			wr.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		long debut = System.currentTimeMillis();
		
		String m1 = "je suis à la recherche";
		String m2 = "on va faire l'exercice";
		
	//	String m1 = "Ich suche ein Praktikum";
	//	String m2 = "Wir machen die Übung   ";
		
	//	String m1 = "Excusez-moi, je ne me sens pas bien, est-ce que je peux aller à l’infirmerie ?";
	//	String m2 = "Encore une fois Est-ce que c’est possible d’apporter mon devoir demain merci ?";
		
		
		
		List<List<String>> results = new ArrayList<>();
		
		TreeMap<String,Integer> map = Possiblités.lister(3, "./DicoNgrams/dicFR.txt");
		
		String c = Chiffrement.chiffrer(m1, m2);
		
		List<String> Ngrams = Chiffrement.ngrams(3, c);
		
		String message1 = "je "; 
		String message2 = "on ";
		
		AutoDecryption(Ngrams, message1, message2, map, results);
		
		SaveResult(results);
		
		
		  long fin = System.currentTimeMillis();
		  System.out.println("la taille du message  " + m1.length()+ " ");
		   System.out.println("Méthode exécutée est " + Long.toString(fin - debut) + " millisecondes");
		
	}
							
	

}
