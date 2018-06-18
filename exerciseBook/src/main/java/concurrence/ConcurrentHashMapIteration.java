package concurrence;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapIteration {

    private static Map<Long, String> conMap = new ConcurrentHashMap<Long, String>();

    public static void main(String[] args) throws InterruptedException {
        /*for (long i = 0; i < 5; i++) {
            conMap.put(i, i + "");
        }*/
        final Random random = new Random();
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    int a = random.nextInt(10);
                    conMap.put((long) a, String.valueOf(a));
                    System.out.println(Thread.currentThread().getName() + " ADD: " + a);
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                Iterator<Map.Entry<Long, String>> iterator = conMap.entrySet().iterator();
                while (true) {
                    try {
                        Map.Entry<Long, String> entry = iterator.next();
                        System.out.println(Thread.currentThread().getName() + " , get key: " + entry.getKey() + " , val: " + entry.getValue());

                        Thread.sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

        Thread.sleep(3000);
        thread2.start();

        System.out.println("--------");
       /* for (Map.Entry<Long, String> entry : conMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }*/

    }
}
