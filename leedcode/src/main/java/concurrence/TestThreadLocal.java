package concurrence;

public class TestThreadLocal {
    static ThreadLocal<Order> tl = new ThreadLocal();

    class Order {
        Order(Integer num) {
            this.num = num;
        }

        private Integer num;

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }
    }

    private class myRun1 implements Runnable {

        public void run() {
            tl.set(new Order(1));
        }
    }


    private class myRun2 implements Runnable {

        public void run() {
            Order order = tl.get();
        }
    }

    public static void main(String[] args) {
        System.out.println(0x61c88647
        );
    }
}
