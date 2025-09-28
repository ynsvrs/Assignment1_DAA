package com.algosassignment1.cli;

import com.algosassignment1.metrics.Metrics;
import com.algosassignment1.metrics.CsvWriter;
import com.algosassignment1.sorts.MergeSort;
import com.algosassignment1.sorts.QuickSort;
import com.algosassignment1.sorts.DeterministicSelect;
import com.algosassignment1.sorts.ClosestPair;
import com.algosassignment1.sorts.ClosestPair.Point;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class AlgoRunner {

    public static void main(String[] args) throws IOException {
        Metrics metrics = new Metrics();
        String file = "metrics.csv";
        File f = new File(file);
        boolean writeHeader = !f.exists();

        // Write header only if file doesn't exist
        if (writeHeader) {
            try (FileWriter fw = new FileWriter(f, true)) {
                fw.write("Algorithm,n,Comparisons,Allocations,MaxDepth,Time(ns)\n");
            }
        }

        int[] sizes = {1000, 5000, 10000};  // array sizes to test
        for (int n : sizes) {
            runAlgorithms(n, metrics, file);
        }

        System.out.println("Metrics written to " + file);
    }

    private static void runAlgorithms(int n, Metrics metrics, String file) throws IOException {
        Random rnd = new Random();

        // MergeSort
        int[] arr = rnd.ints(n, 0, 10000).toArray();
        metrics.reset();
        long t0 = System.nanoTime();
        MergeSort.sort(arr.clone(), metrics);
        long t1 = System.nanoTime();
        CsvWriter.writeLine(file, "MergeSort", String.valueOf(n),
                String.valueOf(metrics.getComparisons()),
                String.valueOf(metrics.getAllocations()),
                String.valueOf(metrics.getMaxDepth()),
                String.valueOf(t1 - t0));

        // QuickSort
        arr = rnd.ints(n, 0, 10000).toArray();
        metrics.reset();
        t0 = System.nanoTime();
        QuickSort.sort(arr.clone(), metrics);
        t1 = System.nanoTime();
        CsvWriter.writeLine(file, "QuickSort", String.valueOf(n),
                String.valueOf(metrics.getComparisons()),
                String.valueOf(metrics.getAllocations()),
                String.valueOf(metrics.getMaxDepth()),
                String.valueOf(t1 - t0));

        // Deterministic Select
        arr = rnd.ints(n, 0, 10000).toArray();
        metrics.reset();
        t0 = System.nanoTime();
        int median = DeterministicSelect.select(arr.clone(), n / 2, metrics);
        t1 = System.nanoTime();
        CsvWriter.writeLine(file, "DeterministicSelect", String.valueOf(n),
                String.valueOf(metrics.getComparisons()),
                String.valueOf(metrics.getAllocations()),
                String.valueOf(metrics.getMaxDepth()),
                String.valueOf(t1 - t0));

        // Closest Pair
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(rnd.nextDouble() * 10000, rnd.nextDouble() * 10000);
        }
        metrics.reset();
        t0 = System.nanoTime();
        double closest = ClosestPair.closestPairDistance(points, metrics);
        t1 = System.nanoTime();
        CsvWriter.writeLine(file, "ClosestPair", String.valueOf(n),
                String.valueOf(metrics.getComparisons()),
                String.valueOf(metrics.getAllocations()),
                String.valueOf(metrics.getMaxDepth()),
                String.valueOf(t1 - t0));
    }
}
