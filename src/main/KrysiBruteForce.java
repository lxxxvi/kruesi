package main;

/*
 * r = Runden = 4
 * 
 * k = Schlüssel bestehend aus s = 32 bits (GESUCHT)
 * Bsp. 1111 1110 1100 1000 0000 0001 0011 0111
 * 0ter Rundenschlüssel: 1111 1110 1100 1000
 * 1ter Rundenschlüssel: 1110 1100 1000 0000
 * 2ter Rundenschlüssel: 1100 1000 0000 0001
 * 3ter Rundenschlüssel: 1000 0000 0001 0011
 * 4ter Rundenschlüssel: 0000 0001 0011 0111
 * 
 * Eingabe besteht aus n * m Bits ( n = 4, m = 4 )
 * 
 * 
 */

public class KrysiBruteForce {
		
	public static void main( String[] args ) {

		Encryptor e = new Encryptor();

		// Verification
		byte[] plainText = new byte[] { 1, 2, 8, 15}; 
		byte[] key = new byte[] { 1, 1, 2, 8, 8, 12, 0, 0};
		byte[] cypher = e.encryptByteArray( plainText, key );

		System.out.println("\nPlaintext");
		e.printByteArray( plainText );
		System.out.println("\nKey");
		e.printByteArray( key );
		System.out.println("\nCypher");
		e.printByteArray( cypher );
	
	}

}
