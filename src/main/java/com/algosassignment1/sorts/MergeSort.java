package com.algosassignment1.sorts;

import com.algosassignment1.metrics.Metrics;

public class MergeSort {
    private static final int INSERTION_CUTOFF = 16;

    private MergeSort() {}

    public static void sort(int[] arr, Metrics metrics) {
        if (arr == null || arr.length <= 1) return;
        int[] buffer = new int[arr.length];
        sort(arr, buffer, 0, arr.length - 1, metrics);
    }

    private static void sort(int[] arr, int[] buffer, int lo, int hi, Metrics metrics) {
        int n = hi - lo + 1;
        if (n <= INSERTION_CUTOFF) {
            insertionSort(arr, lo, hi, metrics);
            return;
        }

        metrics.enter();
        int mid = lo + (hi - lo) / 2;
        sort(arr, buffer, lo, mid, metrics);
        sort(arr, buffer, mid + 1, hi, metrics);
        merge(arr, buffer, lo, mid, hi, metrics);
        metrics.exit();
    }

    private static void merge(int[] arr, int[] buffer, int lo, int mid, int hi, Metrics metrics) {
        System.arraycopy(arr, lo, buffer, lo, hi - lo + 1);
        metrics.incAllocations();

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) arr[k] = buffer[j++];
            else if (j > hi) arr[k] = buffer[i++];
            else {
                metrics.incComparisons();
                if (buffer[i] <= buffer[j]) arr[k] = buffer[i++];
                else arr[k] = buffer[j++];
            }
        }
    }

    private static void insertionSort(int[] arr, int lo, int hi, Metrics metrics) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= lo) {
                metrics.incComparisons();
                if (arr[j] <= key) break;
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
