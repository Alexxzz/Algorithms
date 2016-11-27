package com.company;

import edu.princeton.cs.algs4.StdOut;

public class Main {
    public static void main(String[] args) {
        PercolationStats experiment = new PercolationStats(200, 100);
        StdOut.println("mean = " + experiment.mean());
        StdOut.println("stddev = " + experiment.stddev());
        StdOut.println("95% confidence interval = " + experiment.confidenceLo() + ", " + experiment.confidenceHi());
    }
}