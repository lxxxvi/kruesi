package main;

public class Encryptor {

	final static byte x1 = 0x1; // 00000001
	final static byte x2 = 0x2; // 00000010
	final static byte x4 = 0x4; // 00000100
	final static byte x8 = 0x8; // 00001000

	Dict dict;

	public Encryptor () {

		// XOR - Beispiel
		byte x = 0xF;
		printByte(x);
		
		byte xNew = (byte) ( x ^ x4 );
		printByte(xNew);
		
		// Small tests
		
		byte[] input = new byte[] {0, 1, 2, 4}; 
		byte[] toXOR = new byte[] {0, 1, 4, 8};
		byte[] res = xOR( input, toXOR, 0 );
		System.out.println();
		printByteArray( res );

		// SETUP
		
		dict = new Dict();
		
	}
	/**
	 * 
	 * @param plainText Byte-Array length 4 (16-bit)
	 * @param key Byte-Array length 8 (32-bit)
	 * @return
	 */
	private byte[] encryptByteArray ( byte[] plainText, byte[] key ) {
		
		byte[] out = new byte[4];

		// XOR - initial round
		out = xOR( plainText, key, 0 );
		out = sBox( out );
		// TODO Bitpermutation
		out = xOR( out, key, 0 );
		
		
		return out;		
		
	}
		
	/**
	 * Eingabe: Byte-Array (4x4 Bits)
	 * Ausgabe: Byte-Array (4x4 Bits)
	 */
	private byte[] xOR ( byte[] in, byte[] toXOR, int round ) {
		
		byte[] out = new byte[4];

		for (int i = 0; i < out.length ; i++ ) {

			out[i] = (byte) (in[i] ^ toXOR[ i + round ]);
			
		}
		return out;
		
	}

	
	/**
	 * Eingabe: Byte-Array (4x4 Bits)
	 * Ausgabe: Byte-Array (4x4 Bits)
	 */
	private byte[] sBox ( byte[] in ) {
		
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
	private byte[] bitPerm (  byte[] in  ) {
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
