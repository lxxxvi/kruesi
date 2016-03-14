package main;

public class Encryptor {

	Dict dict = new Dict();
    Utilities utilies = new Utilities();

	// Konstruktor
	public Encryptor () {}

	/**
	 * 
	 * @param plainText Byte-Array length 4 (16-bit)
	 * @param key Byte-Array length 8 (32-bit)
	 * @return
	 */
	public byte[] encryptByteArray ( byte[] plainText, byte[] key ) {
		
		byte[] out = plainText;

		// XOR - initial round
		out = utilies.xOR( out, utilies.roundKey( key, 0 ) );
		for (int i = 1 ; i < 4; i++ ) {
		
			out = utilies.sBox( out );
			out = utilies.bitPerm( out );
			out = utilies.xOR( out, utilies.roundKey( key, i ) );
		
		}
		// Verkürzter letzter Aufruf ohne bitPerm()
		out = utilies.sBox( out );
		out = utilies.xOR( out, utilies.roundKey( key, 4 ) );
		
		return out;		
		
	}
		
}
