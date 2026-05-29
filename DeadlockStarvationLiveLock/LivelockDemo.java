public class LivelockDemo {
    static boolean aAtiva = true;
    static boolean bAtiva = true;
    static final Object lock = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            while (aAtiva) {
                synchronized (lock) {
                    if (bAtiva) {
                        System.out.println("A cede para B...");
                        try { Thread.sleep(100); } catch (InterruptedException e) {}
                        continue; // volta pro topo sem progredir
                    }
                    System.out.println("A passou!");
                    aAtiva = false;
                }
            }
        });

        Thread threadB = new Thread(() -> {
            while (bAtiva) {
                synchronized (lock) {
                    if (aAtiva) {
                        System.out.println("B cede para A...");
                        try { Thread.sleep(100); } catch (InterruptedException e) {}
                        continue;
                    }
                    System.out.println("B passou!");
                    bAtiva = false;
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}