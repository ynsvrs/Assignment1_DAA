package com.algosassignment1.bench;

import com.algosassignment1.metrics.Metrics;
import com.algosassignment1.sorts.DeterministicSelect;
import com.algosassignment1.sorts.MergeSort;
import com.algosassignment1.sorts.QuickSort;
import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class SelectSortBenchmark {

    @Param({"1000", "5000", "10000"})
    private int n;

    private int[] array;
    private Random rnd;

    @Setup(Level.Iteration)
    public void setup() {
        rnd = new Random();
        array = rnd.ints(n, 0, 100000).toArray();
    }

    @Benchmark
    public int benchmarkMergeSort() {
        Metrics m = new Metrics();
        int[] copy = array.clone();
        MergeSort.sort(copy, m);
        return copy[n / 2];
    }

    @Benchmark
    public int benchmarkQuickSort() {
        Metrics m = new Metrics();
        int[] copy = array.clone();
        QuickSort.sort(copy, m);
        return copy[n / 2];
    }

    @Benchmark
    public int benchmarkDeterministicSelect() {
        Metrics m = new Metrics();
        int[] copy = array.clone();
        return DeterministicSelect.select(copy, n / 2, m);
    }
}

