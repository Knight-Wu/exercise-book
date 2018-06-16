package concurrence;

import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;

public class TASLock {
    static AtomicBoolean state = new AtomicBoolean(false);

    public static void lock() {
        while (state.getAndSet(true)) {

        }
    }

    public static void unlock() {
        state.set(false);
    }

    public static void main(String a[]) {
        waitAndNotify();
    }

    private static void interruptThread(){
        try {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println("running , thread state : " + Thread.currentThread().getState());
                        try {
                            sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println("thread is going to die.");

                }
            });
            thread.start();

//            System.out.println("thread notify ");
            sleep(2000);
            System.out.println("thread state : " + thread.getState());
            thread.interrupt();
            thread.join();// 等待子线程结束
            System.out.println("thread terminated");
            sleep(1000);
            System.out.println("main thread terminated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void waitAndNotify(){
        Thread t = new Thread(new Runnable() {
            public void run() {
                System.out.println("begin to wait");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread awake");
                while (true){

                }

            }
        });

        t.start();
        try {
            System.out.println("main thread begin to sleep");
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("notify child thread ");
        t.notify();

    }


}