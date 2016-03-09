package main;

import java.util.concurrent.BlockingQueue;

public class RangeWorker implements Runnable {

  private final ThreadWatcher threadWatcher;
  private final BlockingQueue<Range> queue;
  private final PlainCipherList plainCipherList;
  private final Encryptor encryptor;

  public RangeWorker(ThreadWatcher threadWatcher, BlockingQueue<Range> queue, Encryptor encryptor, PlainCipherList plainCipherList) {
    this.threadWatcher = threadWatcher;
    this.queue = queue;
    this.encryptor = encryptor;
    this.plainCipherList = plainCipherList;
  }

  public void run() {

    while(queue.size() > 0 && !Thread.currentThread().isInterrupted()) {
      try {
        consume(queue.take());
      } catch (InterruptedException ex) {
        System.out.println("Worker interrupted... exiting");
      }
    }

  }

  void consume(Range range) {

    System.out.println(Thread.currentThread().getName() + " is working on range: " + range.getFrom() + " - " + range.getTo());

    byte[] key = new byte[8];
    byte[] cipher;

    int i = range.getTo();

    while(i >= range.getFrom() && !Thread.currentThread().isInterrupted()) {

      key = encryptor.intToByteArray(key, i);

      for ( int j = 0 ; j < plainCipherList.getPlainCiphers().size() ; j ++ ) {

          cipher = encryptor.encryptByteArray( plainCipherList.getPlainCiphers().get( j ).getPlaintext(), key );
          if ( java.util.Arrays.equals( cipher, plainCipherList.getPlainCiphers().get( j ).getCiphertext() ) ) {

            if ( j == plainCipherList.getPlainCiphers().size() - 1 ) {
              System.out.println("Key was found:");
              encryptor.printByteArray( key );
              threadWatcher.keyDetected();
            }

          } else {

            break;

          }

      }

      i--;
    }


  }

}
