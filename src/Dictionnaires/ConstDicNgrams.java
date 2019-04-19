package Dictionnaires;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

public class ConstDicNgrams {
	
	
	
	 public static List<String> ngrams(int n, String str){
			
		 List<String> ngrams = new ArrayList<>();
			
		 for (int i =0;i< str.length() - n +1;i++)
				
			 ngrams.add(str.substring(i, i+n));
			
		 return ngrams;
		}
	
	/*
	 * NgramDictionnaire -> prend en paramètre : un entier N, un fichier source (Texte dictionnaire),
	 * 											 un fichier destination.
	 * 
	 *  construit le dictionnaire de N-grams à partir du fichier source.
	 */
	
	public static void NgramDictionnaire(String fileSource, String fileDest, int nombreNgram) {
		
		
		Map<String,Integer> map = new TreeMap<>();
		String line;
		List<String> listNgram = new ArrayList<>();
		
		try {
			
			BufferedWriter wr = new BufferedWriter(new FileWriter(fileDest));
			BufferedReader br = new BufferedReader(new FileReader(fileSource));
			
			while((line = br.readLine()) != null) {
				listNgram = ngrams(nombreNgram,line);
				Iterator<String> i = listNgram.iterator();
				
				while(i.hasNext()) {
					String val = i.next();
					
					if(map.isEmpty()) {
						map.put(val, 1);
					}else{
						
						if(map.containsKey(val)) 
							map.put(val, map.get(val)+1);
						else 
							map.put(val, 1);
						}
					
				}
				
			}
			
			
			
			Set<Entry<String,Integer>> set = map.entrySet();

			for(Entry<String,Integer> e : set) {
				wr.write(e.getKey()+" "+e.getValue());
				wr.write("\n");
			}
			
			wr.flush();
			br.close();
			wr.close();
					
					
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args) {
		long debut = System.currentTimeMillis();
		
	//	NgramDictionnaire("./Dictionnaires/Allemand.txt", "./DicoNgrams/dicAl.txt", 3);
	//	NgramDictionnaire("./Dictionnaires/Bosniaque.txt", "./DicoNgrams/dicBs.txt", 3);
	//	NgramDictionnaire("./Dictionnaires/espagnol.txt", "./DicoNgrams/dicEsp.txt", 3);
		NgramDictionnaire("./Dictionnaires/Francais.txt", "./DicoNgrams/dicFR.txt", 3);
	//	NgramDictionnaire("./Dictionnaires/italien.txt", "./DicoNgrams/dicIt.txt", 3);
	//	NgramDictionnaire("./Dictionnaires/Lituanien.txt", "./DicoNgrams/dicLt.txt", 3); 
	//	NgramDictionnaire("./Dictionnaires/polonais.txt", "./DicoNgrams/dicPl.txt", 3);
	//	NgramDictionnaire("./Dictionnaires/portugais.txt", "./DicoNgrams/dicPg.txt", 3);
		//NgramDictionnaire("./Dictionnaires/Néerlandais.txt", "./DicoNgrams/dicNr.txt", 3);
		
		
		    long fin = System.currentTimeMillis();
		   System.out.println("Méthode exécutée est " + Long.toString(fin - debut) + " millisecondes");
	}
	
	
	

}
