import java.util.concurrent.locks.ReentrantLock;

public class StarvacaoSolucao {
    static final ReentrantLock lock = new ReentrantLock(true); // true = fair

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 5; i++) {
            Thread gulosa = new Thread(() -> {
                while (true) {
                    lock.lock();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                    } finally {
                        lock.unlock();
                    }
                }
            }, "Gulosa-" + i);
            gulosa.setDaemon(true);
            gulosa.start();
        }

        Thread.sleep(500);

        Thread faminta = new Thread(() -> {
            System.out.println("Faminta tentando...");
            lock.lock();
            try {
                System.out.println("Faminta conseguiu!");
            } finally {
                lock.unlock();
            }
        }, "Faminta");

        faminta.start();
    }
}