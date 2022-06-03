package utils;

import java.util.function.Consumer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

public class Parallel {

  /**
   * Execute a task in parallel
   * 
   * @param N length of the data
   * @param callback the task to be carried out in parallel
  */
  static public void runParallel(int N, Consumer<ParallelParameters> callback) {
    final int P = Runtime.getRuntime().availableProcessors();

    Function<Integer, Runnable> func = rank -> {
      return () -> {
        final int my_rank = rank;
        final int local_n = (int) Math.max(Math.round((double) N/P), 1);
        int my_start = local_n * my_rank;
        int my_last = my_start + local_n;
        if (my_last >= N || rank == P-1) my_last = N;

        callback.accept(new ParallelParameters(my_start, my_last, my_rank));
      };
    };

    ExecutorService executorService = Executors.newFixedThreadPool(P);
    for (int i = 0; i < P; i++) executorService.execute(func.apply(i));
    executorService.shutdown();
    while (!executorService.isTerminated());
  }
}