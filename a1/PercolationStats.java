import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    //Size of n in grid
    private final int size;
    private final int trials;
    //Fraction of open sites in computational experiment t (indexed at 0)
    private double[] fractionOpened;

    //Experiment results
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 | trials < 1) {
            throw new IllegalArgumentException();
        }
        // Class Variables
        this.trials = trials;
        size = n;
        fractionOpened = new double[trials];

        //Individual Experiment Variables
        Percolation perc;
        int row;
        int col;
        boolean percFlag;
        boolean openFlag;
        //Run the loop trials amount of times
        for (int i = 0; i < trials; i++) {
            perc = new Percolation(size);
            percFlag = true;
            while (percFlag) {
                // Open a random site
                openFlag = true;
                while (openFlag) {}
                    row = StdRandom.uniform(1, size + 1);
                    col = StdRandom.uniform(1, size + 1);
                    if (!perc.isOpen(row, col)) {
                        perc.open(row, col);
                        openFlag = false;
                    }
                }

                //Once percolates, log the fraction opened
                if (perc.percolates()) {
                    double opened = (double) perc.numberOfOpenSites();
                    fractionOpened[i] = opened / (size * size);
                    percFlag = false;
                }
        }
        //run mean, stdev, conf intteravls
        this.mean = mean();
        this.stddev = stddev();
        this.confidenceLo = confidenceLo();
        this.confidenceHi = confidenceHi();
    }


    // sample mean of percolation threshold
    public double mean() {
        //Use their library
        return StdStats.mean(fractionOpened);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        //Use their library
        return StdStats.stddev(fractionOpened);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return 0.0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        // double confidenceHi = 
        return 0.0;
    }

    // test client (described below)
    public static void main(String[] args) {
        // args0 =
    }
}
