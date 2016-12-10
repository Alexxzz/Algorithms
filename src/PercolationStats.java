import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] experiments;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        experiments = new double[trials];

        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);

            double unions = 0;
            while (!p.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                    unions++;
                }
            }

            experiments[i] = unions / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(experiments);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(experiments);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(experiments.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(experiments.length));
    }

    // test client (described below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats experiment = new PercolationStats(n, trials);
        StdOut.println("mean = " + experiment.mean());
        StdOut.println("stddev = " + experiment.stddev());
        StdOut.println("95% confidence interval = " + experiment.confidenceLo() + ", " + experiment.confidenceHi());
    }
}
