import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    // Fraction of open sites in computational exp t (indexed at 0)
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    // perform trials independent exps on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException();
        }
        double[] fractionOpened = new double[trials];
        double numberOpened = 0;

        // Individual Experiment Variables
        Percolation perc;
        int row;
        int col;
        boolean percFlag;
        boolean openFlag;

        // Run the loop trials amount of times
        for (int i = 0; i < trials; i++) {
            numberOpened = 0;
            perc = new Percolation(n);
            percFlag = true;
            while (percFlag) {
                // Open a random site
                openFlag = true;
                while (openFlag) {
                    row = StdRandom.uniform(1, n + 1);
                    col = StdRandom.uniform(1, n + 1);
                    if (!perc.isOpen(row, col)) {
                        perc.open(row, col);
                        numberOpened++;
                        openFlag = false;
                    }
                }
                // Once percolates, log the fraction opened
                if (perc.percolates()) {
                    fractionOpened[i] = numberOpened / (n * n);
                    percFlag = false;
                }
            }
        }
        this.mean = StdStats.mean(fractionOpened);
        this.stddev = StdStats.stddev(fractionOpened);
        this.confidenceLo = this.mean - (1.96 * this.stddev) / Math.sqrt(trials);
        this.confidenceHi = this.mean + (1.96 * this.stddev) / Math.sqrt(trials);
    }

    // sample mean of percolation threshold
    public double mean() {
        return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return this.stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.confidenceHi;
    }

    // test client (described below)
    public static void main(String[] args) {
        // int n;
        // int t;
        if (args.length == 2) {
            try {
                int n = Integer.parseInt(args[0]);
                int t = Integer.parseInt(args[1]);
                PercolationStats exp = new PercolationStats(n, t);
                System.out.println("mean                    = " + exp.mean());
                System.out.println("stddev                    = " + exp.stddev());
                System.out.println("95% confidence interval = ["
                    + exp.confidenceLo() + ", " + exp.confidenceHi() + "]");
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[0] + " must be an integer.");
            }
        } else {
            throw new IllegalArgumentException();
        }

    }
}
