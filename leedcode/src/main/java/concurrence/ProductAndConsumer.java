package concurrence;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class ProductAndConsumer {
    private static final int size = 10;
    private static final Queue queue = new LinkedBlockingQueue(size);

    private static final Random random = new Random();

    static class Consumer extends Thread {
        Consumer() {
            super(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            sleep((long) (Math.random() * 100));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized (queue) {
                            while (queue.size() == 0) {
                                try {
                                    System.out.println("queue is empty");
                                    queue.wait();
                                    System.out.println("queue is not  empty");
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            System.out.println("get ele : " + queue.remove());
                            queue.notifyAll();
                        }

                    }
                }
            });
            this.start();
        }
    }

    static class Producer extends Thread {
        Producer() {
            super(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            sleep((long) (Math.random() * 100));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized (queue) {
                            while (queue.size() == size) {
                                try {
                                    System.out.println("queue is full");
                                    queue.wait();
                                    System.out.println("queue is not  full");
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            int ele = random.nextInt() % 100;
                            System.out.println("push ele " + ele + " to queue ");
                            queue.add(ele);
                            queue.notifyAll();

                        }

                    }
                }
            });

            this.start();
        }
    }

    public static void main(String[] args) {
        Producer p = new Producer();
        Consumer c = new Consumer();
    }

}
