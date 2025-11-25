import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class ThreadCheckerCallable {

    public static void main(String[] args) throws Exception {

        long startTime = System.nanoTime();

        int numberOfThreads = 1000;

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        Future<Integer>[] futures = new Future[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {

            final int index = i;

            futures[i] = executor.submit(new Callable<Integer>() {
                public Integer call() {

                    int count = 0;
                    for (int n = 1; n <= 1_000_000; n++) {
                        count++;
                    }
                    return count;
                }
            });
        }

        long total = 0;
        for (int i = 0; i < numberOfThreads; i++) {
            total = total + futures[i].get();
        }

        executor.shutdown();

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
