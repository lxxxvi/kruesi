package main;

import java.util.concurrent.BlockingQueue;

public class RangeProducer implements Runnable {

  BlockingQueue<Range> queue;
  final private int BLOCK_SIZE;

  public RangeProducer(BlockingQueue<Range> queue, int cpus) {
    this.queue        = queue;
    this.BLOCK_SIZE   = (Integer.MAX_VALUE / cpus) * 2;
    System.out.println("Block size is : " + BLOCK_SIZE);
  }

  public void run() {
    try {
      int i = Integer.MAX_VALUE;

      while (i > Integer.MIN_VALUE + BLOCK_SIZE) {
         int to = i;
        i = i - BLOCK_SIZE;
        int from = i + 1;
        queue.put( new Range(from, to) );
      }
    } catch (InterruptedException ex) {

    }
    finally {
      System.out.println("Stopping production");
    }
  }
}
