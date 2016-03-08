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
		
		byte xNew = (byte) ( x & 0x8 );
		printByte(xNew);
		
		// Small tests
		
		byte[] input = new byte[] {0, 1, 2, 4}; 
		byte[] toXOR = new byte[] {0, 1, 4, 8};
		byte[] res = xOR( input, toXOR, 0 );
		System.out.println();
		printByteArray( res );
		
		byte[] testBitPermInput = new byte[] {0, 0, 0, 15}; 
		System.out.println();
		System.out.println("BitPermTest: input");
		printByteArray( testBitPermInput );
		byte[] bitPermTest = bitPerm( testBitPermInput );
		System.out.println();
		System.out.println("BitPermTest: output");
		printByteArray( bitPermTest );
		

		// SETUP
		
		dict = new Dict();
		
	}
	/**
	 * 
	 * @param plainText Byte-Array length 4 (16-bit)
	 * @param key Byte-Array length 8 (32-bit)
	 * @return
	 */
	public byte[] encryptByteArray ( byte[] plainText, byte[] key ) {
		
		byte[] out = plainText;

		// XOR - initial round
		out = xOR( out, key, 0 );
		for (int i = 1 ; i < 4; i++ ) {
		
			out = sBox( out );
			out = bitPerm( out );
			out = xOR( out, key, i );
		
		}
		// Verkürzter letzter Aufruf ohne bitPerm()
		out = sBox( out );
		out = xOR( out, key, 4 );
		
		return out;		
		
	}
		
	/**
	 * Eingabe: Byte-Array (4x4 Bits)
	 * Ausgabe: Byte-Array (4x4 Bits)
	 */
	private byte[] xOR ( byte[] in, byte[] toXOR, int round ) {
		
		byte[] out = new byte[4];

		// Iterate over byte-array with the corresponding subset from the key, which gets incremented with round.
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
		
		// http://stackoverflow.com/questions/8408918/extracting-bits-with-bitwise-operators
		
		byte[] out = new byte[4];

		// First 4 Bits  (in[0])
		
		// 0 => 0
		byte bit = (byte) ( in[0] & 0x8 );
		out[0] = (byte) (out[0] | bit);
		
		// 1 => 4
		bit = (byte) ( in[0] & 0x4);
		out[1] = (byte) (out[1] | bit<<1 );

		// 2 => 8
		bit = (byte) ( in[0] & 0x2);
		out[2] = (byte) (out[2] | bit<<2 );

		// 3 => 12
		bit = (byte) ( in[0] & 0x1);
		out[3] = (byte) (out[3] | bit<<3);

		// Second 4 bits (in[1])

		// 4 => 1
		bit = (byte) ( in[1] & 0x8 );
		out[0] = (byte) (out[0] | bit>>1);
		
		// 5 => 5
		bit = (byte) ( in[1] & 0x4);
		out[1] = (byte) (out[1] | bit );

		// 6 => 9
		bit = (byte) ( in[1] & 0x2);
		out[2] = (byte) (out[2] | bit<<1 );

		// 7 => 13
		bit = (byte) ( in[1] & 0x1);
		out[3] = (byte) (out[3] | bit<<2);
		
		// Third 4 bits (in[2])

		// 8 => 2
		bit = (byte) ( in[2] & 0x8 );
		out[0] = (byte) (out[0] | bit>>2);
		
		// 9 => 6
		bit = (byte) ( in[2] & 0x4);
		out[1] = (byte) (out[1] | bit>>1 );

		// 10 => 10
		bit = (byte) ( in[2] & 0x2);
		out[2] = (byte) (out[2] | bit );

		// 11 => 14
		bit = (byte) ( in[2] & 0x1);
		out[3] = (byte) (out[3] | bit<<1);
		
		// Fourth 4 bits (in[3])

		// 12 => 3
		bit = (byte) ( in[3] & 0x8 );
		out[0] = (byte) (out[0] | bit>>3);
		
		// 13 => 7
		bit = (byte) ( in[3] & 0x4);
		out[1] = (byte) (out[1] | bit>>2 );

		// 14 => 11
		bit = (byte) ( in[3] & 0x2);
		out[2] = (byte) (out[2] | bit>>1 );

		// 15 => 15
		bit = (byte) ( in[3] & 0x1);
		out[3] = (byte) (out[3] | bit);

		return out;
		
	}
	
	public void printByte ( byte b ) {
		
		String s1 = String.format("%4s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
		System.out.print(s1 + ","); // 1001
		
	}
	
	public void printByteArray ( byte b[] ) {
		
		for ( int i = 0; i < b.length ; i++) {
		
			printByte( b[i] );

		}
		
	}
	
}
