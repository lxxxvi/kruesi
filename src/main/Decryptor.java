package main;

/**
 * Created by trish on 13.03.16.
 */
public class Decryptor {

    /**
     * Implementing the decryptor based on the encryptor by using sInv()-function from class Dict
     * and bitPerm() from class Encryptor which should also work for the decryptor without changing anything.
     */

    Utilities utilies = new Utilities();

    //Konstruktor
    public Decryptor () { }

    public byte[] decryptByteArray( byte[] text, byte[] key) {
        byte[] out = text;

		// XOR - initial round
		out = utilies.xOR( out, utilies.roundKey( key, 4 ) );
		for (int i = 1 ; i < 4; i++ ) {
		
			out = utilies.sBoxInv( out );
			out = utilies.bitPerm( out );
			
			// Key needs to be bit-permutated when decrypting
	        byte[] bitPermKey = utilies.bitPerm( utilies.roundKey( key, 4 - i ) );
			
	        out = utilies.xOR( out, bitPermKey );
		
		}
		// Verkürzter letzter Aufruf ohne bitPerm()
		out = utilies.sBoxInv( out );
		out = utilies.xOR( out, utilies.roundKey( key, 0 ) );
		
		return out;		
    }
    
}
