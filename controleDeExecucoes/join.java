class join {
public static void main(String[] args) throws InterruptedException { 
   
    Thread t1 = new Thread(() -> {

        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread: " + i);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {}
        }

    });

    t1.start();

    // main espera t1 terminar
    t1.join();

    System.out.println("Main terminou");
}

}