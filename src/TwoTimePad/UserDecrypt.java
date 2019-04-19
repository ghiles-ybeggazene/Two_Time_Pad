package TwoTimePad;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;



public class UserDecrypt {
	
	public static TreeMap<String,Integer> map = Possiblités.lister(3, "./DicoNgrams/dicFR.txt");
	

	public static void user(List<String> ngrams, String message1, String message2) {
		
		List<List<String>> listCouples;
		
		int i = 1;
		boolean bool = false;
		int h = 0;
		int k = 0;
		Scanner sc = new Scanner(System.in);
		
		listCouples = Possiblités.transfert(map, message1, message2, ngrams.get(i));
		
		while(i <= ngrams.size()) {
			
			listCouples = Possiblités.transfert(map, message1, message2, ngrams.get(i));
			
			int a = 0;
			
			if (listCouples.isEmpty()) {
				
				System.out.println("==================== gestion du bloquage ! ======================");
				
				for (int e=0 ; e < message1.length();e++) {
					System.out.print(e+"	");
				}
				System.out.println();
				
				for (int e=0 ; e < message1.length();e++) {
					System.out.print(message1.charAt(e)+"	");
				}
				System.out.println();
				
				for (int e=0 ; e < message2.length();e++) {
					System.out.print(message2.charAt(e)+"	");
				}
				
				System.out.println();
				
				System.out.println("------------------------------position---------------------------------------");
				int position = sc.nextInt();
				i = position - 2;
				
				System.out.println("-------------------------------Couple-----------------------------------------");
				
				message1 = message1.substring(0, position);
				message2 = message2.substring(0, position);
				
				
				listCouples = Possiblités.transfert(map, message1, message2, ngrams.get(i));
				
				for(int z = 0 ; z < listCouples.size() ; z++) {
					System.out.println(z+" "+listCouples.get(z).toString());
				}
				
				System.out.println("---------------Choisissez le couple avec lequel vous voulez continuer !-------------");
				
				h = sc.nextInt();
				
			}
			
			
			if(listCouples.get(h).get(0).substring(0,2).equals(message1.substring(message1.length()-2))){
				
				message1 = message1.concat(""+listCouples.get(h).get(0).charAt(2));
				message2 = message2.concat(""+listCouples.get(h).get(1).charAt(2));
				
				System.out.println(message1+"							"+message2);
				
			}
			
			if(listCouples.get(h).get(1).substring(0,2).equals(message1.substring(message1.length()-2))){
				
				message1 = message1.concat(""+listCouples.get(h).get(1).charAt(2));
				message2 = message2.concat(""+listCouples.get(h).get(0).charAt(2));
				
				System.out.println(message1+"							"+message2);

				
			}
			
			h = 0;
			i++;
			
			
		}
				
				return;
	}
	
	
	
public static void main(String[] args) {
		
	 //	String message1 = "Nous avançons ceci avec la profonde conviction";
	//	String message2 = "Les réflexions abondent et se pressent devant ";
		
	String message1 = "je suis a la recherche d'un stage ";
	String message2 = "on va faire un examen demain matin";
	
	
		String dicoNgrams = "./DicoNgrams/dicFR.txt";
		
		String c = Chiffrement.chiffrer(message1, message2);
		List<String> ngrams = Chiffrement.ngrams(3,c);
		
	//	TreeMap<String,Integer> map = Possiblités.lister(3, dicoNgrams);
		
		String m1 = "nou";
		String m2 = "les";
		
		
		
		
		user(ngrams, m1, m2);
		
	}

	
													/* TEST OK !! */

}
