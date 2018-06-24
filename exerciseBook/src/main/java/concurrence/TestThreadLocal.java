package concurrence;

public class TestThreadLocal {
    private static ThreadLocal<Order> t1 = new ThreadLocal<Order>();
    private static Order o2 = new Order(2);
    private static ThreadLocal t2 = new ThreadLocal<Order>() {
        @Override
        protected Order initialValue() {
            return o2;
        }
    };

    static class Order {
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
//            t1.set(new Order(1));
        }
    }


    private class myRun2 implements Runnable {

        public void run() {
//            Order order = t1.get();
        }
    }

    public static void main(String[] args) {
        Order o1 = new Order(1);
        t1.set(o1);
        System.out.println((t1.get() == o1) ? "it is order 1" : "it is order 2");
        System.out.println((t2.get() == o1) ? "it is order 1" : "it is order 2");

    }

}
