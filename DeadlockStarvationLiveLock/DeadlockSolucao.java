public class DeadlockSolucao {
    static final Object lockA = new Object();
    static final Object lockB = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lockA) {              // ambas pegam lockA primeiro
                System.out.println("T1 pegou lockA...");
                try { Thread.sleep(50); } catch (InterruptedException e) {}
                synchronized (lockB) {
                    System.out.println("T1 concluiu!");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lockA) {              // mesma ordem: lockA antes de lockB
                System.out.println("T2 pegou lockA...");
                try { Thread.sleep(50); } catch (InterruptedException e) {}
                synchronized (lockB) {
                    System.out.println("T2 concluiu!");
                }
            }
        });

        t1.start();
        t2.start();
    }
}