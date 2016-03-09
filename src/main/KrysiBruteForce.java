package main;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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

		Utilities u = new Utilities();
		Encryptor e = new Encryptor();
		Decryptor d = new Decryptor();

		// Verify Encryption

		byte[] testPlainText = new byte[] { 1, 2, 8, 15 };
		byte[] testKey = new byte[] { 1, 1, 2, 8, 8, 12, 0, 0 };
		byte[] testCipher = e.encryptByteArray(testPlainText, testKey);

		byte[] testCorrectCipher = new byte[] { 10, 14, 11, 4 };
		if (java.util.Arrays.equals(testCipher, testCorrectCipher)) {

			System.out.println("Encryption works.");

		} else {

			System.out.println("Encryption FAILS");

		}

		// Verify Decryption

		byte[] decryptedCipher = d.decryptByteArray(testCorrectCipher, testKey);
		if ( java.util.Arrays.equals( decryptedCipher, testPlainText ) ) {

			System.out.println("Decryption works.");

		} else {

			System.out.println("Decryption FAILS");

		}

		// Decrypting the ciphertext

		byte[] workingKey = new byte[]{3, 10, 9, 4, 13, 6, 3, 15}; // Working Key: 0011 1010 1001 0100 1101 0110 0011 1111
		byte[] cipherText = new byte[] { 5, 10, 3, 15 };
		byte[] plainText = d.decryptByteArray(cipherText, workingKey);
		System.out.println("Decrypted Text:");
		u.printByteArray(plainText);


		// Brute Force Attack


    // Put the Plain/Cipher pairs into an object
    PlainCipherList plainCipherList = new PlainCipherList();

    // work on all CPUs available(Threads)
    int cpus = Runtime.getRuntime().availableProcessors();
    System.out.println("Running on " + cpus + " CPUs");

    // Using BlockingQueue
    BlockingQueue<Range> queue = new LinkedBlockingQueue<Range>();

    // RangeProducer Splits the Integer MIN - MAX into equal slices (each CPU gets one)
    RangeProducer rangeProducer = new RangeProducer(queue, cpus);
    Thread rangeProducerThread = new Thread(rangeProducer);
    rangeProducerThread.start();

    // We need to handle the worker threads
    ThreadWatcher threadWatcher = new ThreadWatcher(cpus, queue, e, plainCipherList);
    threadWatcher.call();

    // stop the producer, if still running
    rangeProducerThread.interrupt();

    System.out.println("DONE!");


  }

}
