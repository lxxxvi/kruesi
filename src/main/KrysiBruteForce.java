package main;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

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
		
		byte[] testPlainText = new byte[] { 1, 2, 8, 15 };
		byte[] testKey = new byte[] { 1, 1, 2, 8, 8, 12, 0, 0 };
		byte[] testCipher = e.encryptByteArray(testPlainText, testKey);

		byte[] testCorrectCipher = new byte[] { 10, 14, 11, 4 };
		if (java.util.Arrays.equals(testCipher, testCorrectCipher)) {

			System.out.println("Encryption works.");
		
		} else {
	
			System.out.println("Encryption FAILS");
			return;

		}
		
		// Decrypting the ciphertext
		
		byte[] workingKey = new byte[]{3, 10, 9, 4, 13, 6, 3, 15}; // Working Key: 0011 1010 1001 0100 1101 0110 0011 1111
		byte[] cipherText = new byte[] { 5, 10, 3, 15 };
		
		
		// Brute Force Attack

		ArrayList<byte[]> plaintexts = new ArrayList<byte[]>(Arrays.asList(
				new byte[] { 2, 6, 11, 7 }, 
				new byte[] { 0, 6, 11, 7 },
				new byte[] { 6, 1, 11, 7 }, 
				new byte[] { 6, 1, 0, 2 },
				new byte[] { 15, 1, 2, 12 }, 
				new byte[] { 15, 5, 15, 12 },
				new byte[] { 5, 15, 0, 12 }));

		ArrayList<byte[]> ciphertexts = new ArrayList<byte[]>(Arrays.asList(
				new byte[] { 11, 12, 13, 6 }, 
				new byte[] { 2, 5, 3, 5 },
				new byte[] { 9, 4, 7, 15 }, 
				new byte[] { 13, 7, 15, 12 },
				new byte[] { 15, 4, 3, 9 }, 
				new byte[] { 2, 7, 11, 3 },
				new byte[] { 9, 3, 1, 2 }));

		byte[] key = new byte[8];
		byte[] cipher;

		long startTime = System.nanoTime();

		for ( int i = Integer.MIN_VALUE; i <= Integer.MAX_VALUE; i++ ) {

			key = e.intToByteArray( key, i );
			//key = new byte[]{3, 10, 9, 4, 13, 6, 3, 15}; // Working Key: 0011 1010 1001 0100 1101 0110 0011 1111

			/*
			if ( ( i % 10000000 ) == 0 ) {
			
				e.printByteArray( key );
				System.out.println();
		
			}
			*/
			
			for ( int j = 0 ; j < plaintexts.size() ; j ++ ) {
				
				cipher = e.encryptByteArray( plaintexts.get( j ), key );
				if ( java.util.Arrays.equals( cipher, ciphertexts.get( j ) ) ) {

					if ( j == plaintexts.size() - 1 ) {
						
						System.out.println("Key was found:");
						e.printByteArray( key );

						long endTime = System.nanoTime();
						long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
						System.out.println("It took " + (duration)/1000000000/60 + " minutes to find the key.");
						System.out.println("Number of keys tried: " + Math.abs( Integer.MAX_VALUE - Integer.MIN_VALUE - i - Integer.MIN_VALUE ) );
						System.exit(1);
						
					}
					
				} else {
					
					break;
					
				}
				
			}

		}
	
	}

}
