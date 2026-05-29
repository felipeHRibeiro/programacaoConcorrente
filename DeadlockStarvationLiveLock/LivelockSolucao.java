import java.util.Random;

public class LivelockSolucao {
    static volatile boolean aAtiva = true;
    static volatile boolean bAtiva = true;
    static final Random random = new Random();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            while (aAtiva) {
                if (bAtiva) {
                    int espera = random.nextInt(200) + 50; // entre 50 e 250ms
                    System.out.println("A cede, aguarda " + espera + "ms...");
                    try { Thread.sleep(espera); } catch (InterruptedException e) {}
                    continue;
                }
                System.out.println("A passou!");
                aAtiva = false;
            }
        });

        Thread threadB = new Thread(() -> {
            try { Thread.sleep(100); } catch (InterruptedException e) {} // pequeno delay inicial
            while (bAtiva) {
                if (aAtiva) {
                    int espera = random.nextInt(200) + 50;
                    System.out.println("B cede, aguarda " + espera + "ms...");
                    try { Thread.sleep(espera); } catch (InterruptedException e) {}
                    continue;
                }
                System.out.println("B passou!");
                bAtiva = false;
            }
        });

        threadA.start();
        threadB.start();
    }
}