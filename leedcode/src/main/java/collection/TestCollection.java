package collection;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestCollection {
    final static List<Integer> list = new Vector<Integer>();

    private static void testConcurrenceModException() {


        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    Iterator<Integer> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        Integer integer = iterator.next();
                        if (integer < 50) {
                            System.out.println(Thread.currentThread() + " begin to remove");
                            iterator.remove();
                            System.out.println(Thread.currentThread().getName() + " removed " + integer);
                            Thread.yield();
                        }
                    }
                }
            });
            t.start();
        }

        /*Executor executor = Executors.newFixedThreadPool(20);
        ((ExecutorService) executor).submit(
        });*/
    }

    private static void useExecutorService() {
        Executor executor = Executors.newFixedThreadPool(200);


        for (int i = 0; i < 10000; i++) {
            ((ExecutorService) executor).submit(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " run ");

                    Iterator<Integer> iterator = list.iterator();

                    while (iterator.hasNext()) {
                        Integer integer = iterator.next();
                        if (integer < 500000) {
                                iterator.remove();
                                System.out.println(Thread.currentThread().getName() + " removed " + integer);
                            try {
//                                Thread.sleep(100);
                                wait(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
//                            Thread.yield();

                        }
                    }

                }
            });
        }

    }

    public static class WaitAndSleepTask implements Runnable {
        int number = 10;

        public int getNumber() {
            return number;
        }

        public void firstMethod() {
            synchronized (this) {
                number += 100;
                System.out.println("first method : " + number);
            }
        }

        public void secondMethod() {
            synchronized (this) {
                /**
                 * (休息2S,阻塞线程)
                 * 以验证当前线程对象的机锁被占用时,
                 * 是否被可以访问其他同步代码块
                 */
                try {
//                    Thread.sleep(2000);
                    this.wait(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                number *= 200;
            }
        }

        public void run() {
            try {
                firstMethod();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        /*for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        useExecutorService();
        String []a = {"1","2"};
        List list = new ArrayList(Arrays.asList(a));*/
       /* WaitAndSleepTask threadTest = new WaitAndSleepTask();
        Thread thread = new Thread(threadTest);
        thread.start();
        threadTest.secondMethod();
        System.out.println("main : " + threadTest.getNumber());*/

//        waitMethodTest();

        System.out.println(mapInitThreshold(16));
    }

    public static void waitMethodTest() {
        final AtomicBoolean flag = new AtomicBoolean(false);
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    synchronized (this) {
                        this.wait(2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " finish wait");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " occupied ");
                }
            }
//                    System.out.println(Thread.currentThread().getName() + " sleep done");
        });

        t1.start();
        t2.notify();
        t2.start();
    }

    static  int mapInitThreshold(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }
}
