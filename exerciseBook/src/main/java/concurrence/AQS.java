package concurrence;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class AQS {
    class Solver {
        final int N;
        final float[][] data;
        final CyclicBarrier barrier;
        Random random = new Random();

        class Worker implements Runnable {
            int myRow;

            Worker(int row) {
                myRow = row;
            }

            public void run() {
                try {
                    Thread.sleep(1000 * random.nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " sleep over");

                try {
                    barrier.await();
                } catch (InterruptedException ex) {
                    return;
                } catch (BrokenBarrierException ex) {
                    return;
                }
            }
        }

        public Solver(float[][] matrix) {
            data = matrix;
            N = matrix.length;
            Runnable barrierAction =
                    new Runnable() {
                        public void run() {
                            System.out.println("start to go next");
                        }
                    };
            barrier = new CyclicBarrier(N, barrierAction);

            List<Thread> threads = new ArrayList<Thread>(N);
            for (int i = 0; i < N; i++) {
                Thread thread = new Thread(new Worker(i));
                threads.add(thread);
                thread.start();
            }

            // wait until done
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
