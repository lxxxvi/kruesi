package main;

import java.util.ArrayList;
import java.util.Arrays;

public class PlainCipherList {

	private final ArrayList<PlainCipher> plainCiphers = new ArrayList<PlainCipher>();

	public PlainCipherList() {
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

		for(int i = 0; i < plaintexts.size(); i++) {
			plainCiphers.add(new PlainCipher(plaintexts.get(i), ciphertexts.get(i)));
		}
	}

	public ArrayList<PlainCipher> getPlainCiphers() {
		return plainCiphers;
	}
}
