package main;

public class PlainCipher {

	final byte[] plaintext;
	final byte[] ciphertext;

	public PlainCipher(byte[] plaintext, byte[] ciphertext ) {
		this.plaintext = plaintext;
		this.ciphertext = ciphertext;
	}

	public byte[] getPlaintext() {
		return plaintext;
	}

	public byte[] getCiphertext() {
		return ciphertext;
	}

}
