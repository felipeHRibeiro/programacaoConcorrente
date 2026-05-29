public class Main {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 50; i++) { 
            Counter c = new Counter();

            Thread t1 = new Thread(c);
            Thread t2 = new Thread(c);
            
            t1.start(); t2.start();
            t1.join(); t2.join();

            System.out.println("Run " + (i + 1)
            + "\tFinal counter value: " + c.value);
        }
    }
}