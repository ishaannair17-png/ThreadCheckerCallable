public class ThreadChecker {

    public static void main(String[] args) throws InterruptedException {

        long startTime = System.nanoTime();

        int numberOfThreads = 1000;
        Thread[] threads = new Thread[numberOfThreads];
        int[] results = new int[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {

            final int index = i;

            threads[i] = new Thread(new Runnable() {
                public void run() {
                    int count = 0;
                    for (int n = 1; n <= 1_000_000; n++) {
                        count++;
                    }
                    results[index] = count;
                }
            });

            threads[i].start();
        }

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i].join();
        }

        long total = 0;
        for (int i = 0; i < numberOfThreads; i++) {
            total = total + results[i];
        }

        System.out.println("The total is " + total);

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Duration: " + (duration / 1_000_000_000.0) + " seconds");

        startTime = System.nanoTime();
        int timer = 0;

        for (int i = 1; i <= 1_000_000_000; i++) {
            timer = timer + 1;
        }

        endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("Duration: " + (duration / 1_000_000_000.0) + " seconds");
        System.out.println("Timer total: " + timer);
    }
}
