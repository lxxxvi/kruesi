package main;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class ThreadWatcher {

  private int cpus;
  BlockingQueue<Range> queue;
  Encryptor encryptor;
  PlainCipherList plainCipherList;
  ArrayList<Thread> workerThreads;
  Utilities utilities;

  long startTime;


  public ThreadWatcher(int cpus, BlockingQueue<Range> queue, Encryptor encryptor, PlainCipherList plainCipherList, Utilities utilities) {
    this.cpus = cpus;
    this.queue = queue;
    this.encryptor = encryptor;
    this.plainCipherList = plainCipherList;
    this.utilities = utilities;
  }

  public void call() {

    workerThreads = new ArrayList<Thread>();

    startTime = System.currentTimeMillis();

    // start multiple threads, each uses the queue for work units (work unit is a 25Mio range in Integer)
    for(int c = 0; c < cpus; c++) {
      RangeWorker worker = new RangeWorker(this, queue, encryptor, plainCipherList, utilities);
      Thread t = new Thread(worker);
      workerThreads.add(t);
      t.start();
    }

    System.out.println("Waiting for threads to complete");
    for(int w = 0; w < workerThreads.size(); w++) {
      try {
        workerThreads.get(w).join();
      } catch (InterruptedException ex) {}
    }

    System.out.println("Workers are done ...");



  }

  /*
  * Interrupt/Stop the threads if one worker has found the key.
  * */
  public void keyDetected() {

    long endTime   = System.currentTimeMillis();
    long totalTime = endTime - startTime;
    System.out.println(totalTime/1000 + " seconds until the key was found");

    for(int w = 0; w < workerThreads.size(); w++) {
      workerThreads.get(w).interrupt();
    }
  }

}
