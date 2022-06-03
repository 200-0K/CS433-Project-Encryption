package utils;

public class ParallelParameters {
  public final int my_start;
  public final int my_last;
  public final int my_rank;

  public ParallelParameters(int my_start, int my_last, int my_rank) {
    this.my_start = my_start;
    this.my_last = my_last;
    this.my_rank = my_rank;
  }
}
