package com.algosassignment1.sorts;

import com.algosassignment1.metrics.Metrics;
import java.util.Random;

public class QuickSort {
    private static final Random RANDOM = new Random();

    private QuickSort() {}

    public static void sort(int[] arr, Metrics metrics) {
        if (arr == null || arr.length <= 1) return;
        quicksort(arr, 0, arr.length - 1, metrics);
    }

    private static void quicksort(int[] arr, int lo, int hi, Metrics metrics) {
        if (lo >= hi) return;

        metrics.enter();
        int pivotIndex = partition(arr, lo, hi, metrics);

        // Recurse on smaller partition first
        if (pivotIndex - lo < hi - pivotIndex) {
            quicksort(arr, lo, pivotIndex - 1, metrics);
            quicksort(arr, pivotIndex + 1, hi, metrics);
        } else {
            quicksort(arr, pivotIndex + 1, hi, metrics);
            quicksort(arr, lo, pivotIndex - 1, metrics);
        }
        metrics.exit();
    }

    private static int partition(int[] arr, int lo, int hi, Metrics metrics) {
        int pivotIndex = lo + RANDOM.nextInt(hi - lo + 1);
        swap(arr, pivotIndex, hi, metrics);
        int pivot = arr[hi];

        int i = lo;
        for (int j = lo; j < hi; j++) {
            metrics.incComparisons();
            if (arr[j] <= pivot) {
                swap(arr, i, j, metrics);
                i++;
            }
        }
        swap(arr, i, hi, metrics);
        return i;
    }

    private static void swap(int[] arr, int i, int j, Metrics metrics) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}

