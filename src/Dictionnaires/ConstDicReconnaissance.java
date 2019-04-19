package Dictionnaires;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import TwoTimePad.Chiffrement;
import TwoTimePad.Possiblités;

public class ConstDicReconnaissance {

	public static void ConstDicR(TreeMap<String,Integer> NgramsScoreMap,String fileDest){
		
		
		TreeMap<String,Integer> NgramsProduitMap  = new TreeMap<>();
		TreeMap<String,Integer> map = new TreeMap<>();
		
		NgramsScoreMap.forEach((key,value) -> {
			if(value > 5) {
				map.put(key,value);
			}
		});
		
		String[] ngrams = new String[map.size()];
		int[] score = new int[map.size()];
		
		int k = 0;
		
		Set<Entry<String,Integer>> set = map.entrySet();

		for(Entry<String,Integer> e : set) {
				
					ngrams[k] = e.getKey();
					score[k] = e.getValue();
					k++;
			
		}
		
		String xor;
		int produit;
		
		for(int i = 0; i < ngrams.length; i++) {
			for(int j = i ; j < ngrams.length ; j++) {
				
				xor = Chiffrement.XOR(ngrams[i], ngrams[j]);
				produit = score[i]*score[j];
				if(xor.length() == 3)
					NgramsProduitMap.put(xor,produit);
				
			}
		}
		
		try {
			
			BufferedWriter wr = new BufferedWriter(new FileWriter(fileDest));
			NgramsProduitMap.forEach((key,value) -> {
				try {
					
					wr.write(key+" "+value);
					wr.write("\n");
					
				} catch (IOException e1) {
					
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
		
	
/*		TreeMap<String,Integer> map1 = Possiblités.lister(3, "./DicoNgrams/dicAl.txt");
		ConstDicR(map1,"./DicoNgramsReconnaissance/Allemand.txt");
		
		TreeMap<String,Integer> map2 = Possiblités.lister(3, "./DicoNgrams/dicBs.txt");
		ConstDicR(map2,"./DicoNgramsReconnaissance/Bosniaque.txt");
		
		TreeMap<String,Integer> map3 = Possiblités.lister(3, "./DicoNgrams/dicEsp.txt");
		ConstDicR(map3,"./DicoNgramsReconnaissance/Espagnol.txt");
		
		TreeMap<String,Integer> map4 = Possiblités.lister(3, "./DicoNgrams/dicFR.txt");
		ConstDicR(map4,"./DicoNgramsReconnaissance/Français.txt");
   
		TreeMap<String,Integer> map5 = Possiblités.lister(3, "./DicoNgrams/dicIt.txt");
		ConstDicR(map5,"./DicoNgramsReconnaissance/Italien.txt");
		
		TreeMap<String,Integer> map6 = Possiblités.lister(3, "./DicoNgrams/dicLt.txt");
		ConstDicR(map6,"./DicoNgramsReconnaissance/Lituanien.txt");

		TreeMap<String,Integer> map7 = Possiblités.lister(3, "./DicoNgrams/dicPL.txt");
		ConstDicR(map7,"./DicoNgramsReconnaissance/Polonais.txt");
		*/
		TreeMap<String,Integer> map8 = Possiblités.lister(3, "./DicoNgrams/dicPg.txt");
		ConstDicR(map8,"./DicoNgramsReconnaissance/Portugais.txt");
		

		  long fin = System.currentTimeMillis();
		   System.out.println("Méthode exécutée est " + Long.toString(fin - debut) + " millisecondes");
		
		
	}
	
}
