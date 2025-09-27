package com.algosassignment1.sorts;

import com.algosassignment1.metrics.Metrics;
import com.algosassignment1.util.SortUtils;

public class QuickSort {

    private QuickSort() {}

    public static void sort(int[] arr, Metrics metrics) {
        if (arr == null || arr.length <= 1) return;
        SortUtils.shuffle(arr, metrics); // randomize input
        quicksort(arr, 0, arr.length - 1, metrics);
    }

    private static void quicksort(int[] arr, int lo, int hi, Metrics metrics) {
        if (lo >= hi) return;
        metrics.enter();

        int pivotIndex = SortUtils.partitionLastPivot(arr, lo, hi, metrics);

        if (pivotIndex - lo < hi - pivotIndex) {
            quicksort(arr, lo, pivotIndex - 1, metrics);
            quicksort(arr, pivotIndex + 1, hi, metrics);
        } else {
            quicksort(arr, pivotIndex + 1, hi, metrics);
            quicksort(arr, lo, pivotIndex - 1, metrics);
        }

        metrics.exit();
    }
}


