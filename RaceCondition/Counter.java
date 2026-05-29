public class Counter implements Runnable {
    int value = 0;

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            value++;
        }
    }
}
