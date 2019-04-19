package TwoTimePad;

import java.security.SecureRandom;
import java.util.ArrayList;

public class Chiffrement {
	
	/*
	 * 
	 * 
	 */
	
	public static ArrayList<String> ngrams(int n, String str){
		ArrayList<String> ngrams = new ArrayList<>();
		for (int i =0;i< str.length() - n +1;i++)
			ngrams.add(str.substring(i, i+n));
		return ngrams;
	}
	
	/*
	 * XOR -> méthode qui prend en entrée deux chaines de caractères et effectue le XOR entre elles.
	 * 		  retourne le résultat.	
	 */
	
	public static String XOR(String m1,String m2) {
		
		String result = "";
		for(int i=0;i<m1.length();i++) {
			result += (char)(m1.charAt(i) ^ m2.charAt(i));
		}
		
		return result;
	}
	
	/* chiffKey -> méthode qui prend en entrée une chaine de caractères et un tableau de byte (la clé de chiffrement)
	 * 
	 *  et retourne le chiffrement du message avec la clé.
	 *  	
	 */
		private static String chiffKey(String m1,byte[] key) {
			
			int len = m1.length();
			
			final int[] message = new int[len];
			
			for(int i = 0 ; i<len; i++) {
				message[i] = m1.charAt(i);
			}
			
			for (int i = 0; i<key.length;i++) { 
				if (key[i] < 0) {
					key[i] = (byte) ((-1)*key[i]);
				}
				
				
			}
			
			final int[] code = new int[len];
			
			for (int i = 0; i<len;i++) {
				code[i] = (int)(message[i]^key[i]);
				
			}
			
			String result = "";
			
			for(int i=0;i<len;i++) {
				result = result + (char)code[i];
			}
			
			return result;
			
		}
		
		/*
		 * chiffrer -> méthode qui prend en entrée deux chaines de caractères de même taille
		 * 			   Elle crée une clé de chiffrement de même taille que les deux chaines
		 * 			   chiffre les deux messages avec la clé 
		 * 			   effectue le XOR entre les deux messages chiffrés 
		 * 			   retourne le XOR obtenue sous forme de chaine de caractères. 	
		 */
			
			public static String chiffrer(String message1, String message2) {
				
				int len = message1.length();
				byte[] key = new byte[len];
				new SecureRandom().nextBytes(key);
				
				String msg1 = chiffKey(message1,key);
				
				String msg2 = chiffKey(message2,key);
				
				
				String xor = "";
				
				for (int i=0;i<len;i++) {
					xor += (char)(msg1.charAt(i)^msg2.charAt(i));
				}
				
				return xor;
			}

}
