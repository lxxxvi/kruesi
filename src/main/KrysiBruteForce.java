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
	
	final static byte x1 = 0x1; // 00000001
	final static byte x2 = 0x2; // 00000010
	final static byte x4 = 0x4; // 00000100
	final static byte x8 = 0x8; // 00001000
	
	public static void main(String[] args) {
		

		printByte(x4);
		
	}
	
	/**
	 * Eingabe: 4x4 Bits rein
	 * Ausgabe: 4x4 Bits raus
	 */
	public static byte[] xOR ( byte[] in ) {
		
		return in;
		
	}

	
	/**
	 * Eingabe: 4x4 Bits rein
	 * Ausgabe: 4x4 Bits raus
	 */
	public static byte[] sBox ( byte[] in ) {
		
		return in;
		
	}

	/**
	 * Eingabe: 4x4 Bits rein
	 * Ausgabe: 4x4 Bits raus
	 */
	public static byte[] bitPerm (  byte[] in  ) {
		
		return in;
		
	}
	
	public static void printByte ( byte b ) {
		
		String s1 = String.format("%4s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
		System.out.println(s1); // 1001
		
	}
	
}
