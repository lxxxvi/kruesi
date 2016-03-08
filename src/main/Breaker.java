package main;

public class Breaker {

	final static byte x1 = 0x1; // 00000001
	final static byte x2 = 0x2; // 00000010
	final static byte x4 = 0x4; // 00000100
	final static byte x8 = 0x8; // 00001000

	Dict dict;

	public Breaker () {

		// XOR - Beispiel
		byte x = 0xF;
		printByte(x);
		
		byte xNew = (byte) ( x ^ x4 );
		printByte(xNew);
		
		// Small tests
		
		byte[] input = new byte[] {0, 1, 2, 4}; 
		byte[] toXOR = new byte[] {0, 1, 4, 8};
		byte[] res = xOR( input, toXOR );
		System.out.println();
		printByteArray( res );

		// SETUP
		
		dict = new Dict();
		
	}
	
	/**
	 * Eingabe: Byte-Array (4x4 Bits)
	 * Ausgabe: Byte-Array (4x4 Bits)
	 */
	public byte[] xOR ( byte[] in, byte[]toXOR ) {
		
		byte[] out = new byte[4];

		for (int i = 0; i < out.length ; i++ ) {

			out[i] = (byte) (in[i] ^ toXOR[i]);
			
		}
		return out;
		
	}

	
	/**
	 * Eingabe: Byte-Array (4x4 Bits)
	 * Ausgabe: Byte-Array (4x4 Bits)
	 */
	public byte[] sBox ( byte[] in ) {
		
		byte[] out = new byte[4];
		
		for (int i = 0; i < out.length ; i++ ) {

			out[i] = dict.sBox( in[i] );
			
		}
		
		return out;
		
	}

	/**
	 * Eingabe: Byte-Array (4x4 Bits)
	 * Ausgabe: Byte-Array (4x4 Bits)
	 */
	public byte[] bitPerm (  byte[] in  ) {
		// TODO
		return in;
		
	}
	
	public void printByte ( byte b ) {
		
		String s1 = String.format("%4s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
		System.out.println(s1); // 1001
		
	}
	
	public void printByteArray ( byte b[] ) {
		
		for ( int i = 0; i < b.length ; i++) {
		
			printByte( b[i] );

		}
		
	}
	
}
