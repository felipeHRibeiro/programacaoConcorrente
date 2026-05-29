public class StarvationDemo {
    static final Object lock = new Object();

    public static void main(String[] args) {
        // 5 threads gulosas que ficam em loop
        for (int i = 1; i <= 5; i++) {
            Thread gulosa = new Thread(() -> {
                while (true) {
                    synchronized (lock) {
                        // segura o lock e solta imediatamente — mas volta rápido
                    }
                }
            }, "Gulosa-" + i);
            gulosa.setPriority(Thread.MAX_PRIORITY);
            gulosa.setDaemon(true);
            gulosa.start();
        }

        // Thread que tenta entrar mas raramente consegue
        Thread faminta = new Thread(() -> {
            System.out.println("Faminta tentando...");
            synchronized (lock) {
                System.out.println("Faminta conseguiu! (pode demorar muito)");
            }
        }, "Faminta");
        faminta.setPriority(Thread.MIN_PRIORITY);
        faminta.start();
    }
}