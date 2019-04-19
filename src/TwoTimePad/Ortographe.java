package TwoTimePad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Ortographe {
	
	public static void ortographe(String possibilités, String dicMots) {
		
		
		
		try {
			
			TreeMap<String,Integer> dicfr = new TreeMap<>();
			String line;
			
			
			BufferedReader brMots = new BufferedReader(new FileReader(dicMots));
			
			while((line = brMots.readLine()) != null) {
				
				dicfr.put(line,1);
			}
			
			brMots.close();
			
			System.out.println("done !");
			
//=======================================================================================
			
			BufferedReader brPossibiltés = new BufferedReader(new FileReader(possibilités));
			String strLine;
			String[] messages;
			
			List<List<String>> clair = new ArrayList<>();
			
			while((strLine = brPossibiltés.readLine()) != null) {
				
				messages = strLine.split(" <--> ");
				
				clair.add(Arrays.asList(messages[0],messages[1]));
				
			}
			
			brPossibiltés.close();
			System.out.println("Terminé !");
			
//=======================================================================================	
			BufferedWriter wr = new BufferedWriter(new FileWriter("./Résultats/final.txt"));
			
			clair.forEach(e -> {
				
				boolean correct = false;
				int nbrmsg1 = 0,nbrmsg2 = 0;
				
				
				String[] msg1 = e.get(0).toString().split(" ");
				String[] msg2 = e.get(1).toString().split(" ");
				
				for(int i = 0 ; i< msg1.length; i++) 
					if(dicfr.containsKey(msg1[i]) ) 
						nbrmsg1++;
				
				
				if((nbrmsg1 == msg1.length ) )
					correct = true;
				
				if(correct) {
					try {
						wr.write(e.toString());
						wr.write("\n");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
		});	
			wr.close();
			wr.flush();
			System.out.println("Terminé !");
			
			
		}catch(Exception e) {
			
		}
		
	}
	
	
	
	
	public static void main(String[] args) {
		
		ortographe("./Résultats/possibilités.txt", "./DictionnairesMots/dicFr.txt");
		
	}

}
