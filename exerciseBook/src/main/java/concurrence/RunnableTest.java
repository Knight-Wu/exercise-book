package concurrence;

public class RunnableTest {
    static java.lang.Runnable r = new java.lang.Runnable() {
        public void run() {
            System.out.println(Thread.currentThread().getName() + " run");

        }
    };

    static java.lang.Runnable r1 = new java.lang.Runnable() {
        public void run() {
            System.out.println(Thread.currentThread().getName() + " run");

        }
    };

    static Thread thread = new Thread(r1);

    public static void main(String[] args) {
//        r.run();
        new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + " run");

            }
        }).start();
        thread.start();
//        thread.run();
    }
}
