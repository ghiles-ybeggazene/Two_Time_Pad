package TwoTimePad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Possiblités {
	
/*
 * 
 * 
 * 	
 */
	
	public static List<String> copie(List<String> list, String str){
		
		List<String> l = new ArrayList<>();
		
		list.forEach(e -> {
			if(e.startsWith(str)) l.add(e);
		});
		
		return l;
	}
	
/*
 * 
 * 
 */
	
	public static List<List<String>> transfert(TreeMap<String,Integer> map,String message1, String message2, 
			String gram){
		
		List<List<String>> result = new ArrayList<>();
	
		String debut = message1.substring(message1.length() - 2) + (char)0;
		String fin = message1.substring(message1.length() - 2) + (char)255;
		
		String debut2 = message2.substring(message2.length() - 2) + (char)0;
		String fin2 = message2.substring(message2.length() - 2) + (char)255;
		
		
		NavigableMap<String,Integer> nmap1 = map.subMap(debut,true,fin,true);
		NavigableMap<String,Integer> nmap2 = map.subMap(debut2,true,fin2,true);
		
		nmap1.forEach((k,v) -> {
			nmap2.forEach((key,value) -> {
				if(gram.equals(Chiffrement.chiffrer(k, key))) {
					result.add(Arrays.asList(k,key,Integer.toString(v+value)));
				}
			});
		});
		
		Collections.sort(result,Collections.reverseOrder( new Comparator<List<String>>() {
	  		  
	  		  public int compare(List<String> l1, List<String> l2) {
	  				
	  				if (Integer.parseInt(l1.get(2)) > Integer.parseInt(l2.get(2)))
	  					return 1; 
	  				else 
	  					{if (Integer.parseInt(l1.get(2)) == Integer.parseInt(l2.get(2)))
	  						return 0; 
	  					else 
	  						 return -1; }
	  			}

	  		  
			} ));
		
		
		return result;
	}
	
	
//==================================================================================================
	/*
	 * Lister -> prend en paramètres le nombre n de grammes, et un fichier contenant les Ngrammes du dictionnaire avec 
	 * 							le nombre d'apparition de chaque Ngramme
	 * 			cette méthode retourne une TreeMap<String,Integer> contenant les N-grams et leurs occurrences 
	 * 
	 */
		public static TreeMap<String,Integer> lister(int nbrGrammes,String file){
			
			TreeMap<String,Integer> map = new TreeMap<>();
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				
				String line;
				while((line = br.readLine()) != null) {
					
						int l = line.length();
						map.put(line.substring(0, nbrGrammes), Integer.parseInt(line.substring(nbrGrammes+1, l)));
					

				}
				
				br.close();
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return map;
			
		}
		
//===================================================================================================		
		
		public static TreeMap<String,Integer> fileToMap(int nbrGrammes,String file){
			
			TreeMap<String,Integer> map = new TreeMap<>();
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				
				String line;
				while((line = br.readLine()) != null) {
					if(line.length() > nbrGrammes + 2) {
						int l = line.length();
						map.put(line.substring(0, nbrGrammes), Integer.parseInt(line.substring(nbrGrammes+1, l)));
					}

				}
				
				br.close();
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return map;
			
		}


}
