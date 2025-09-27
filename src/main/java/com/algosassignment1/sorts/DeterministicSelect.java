package com.algosassignment1.sorts;

import com.algosassignment1.metrics.Metrics;
import com.algosassignment1.util.SortUtils;

public class DeterministicSelect {

    private static final int INSERTION_CUTOFF = 16;

    private DeterministicSelect() {}

    public static int select(int[] arr, int k, Metrics metrics) {
        if (arr == null || arr.length == 0 || k < 0 || k >= arr.length)
            throw new IllegalArgumentException("Invalid input");

        return selectRecursive(arr, 0, arr.length - 1, k, metrics);
    }

    private static int selectRecursive(int[] arr, int lo, int hi, int k, Metrics metrics) {
        int n = hi - lo + 1;

        if (n <= INSERTION_CUTOFF) {
            insertionSort(arr, lo, hi, metrics);
            return arr[k];
        }

        metrics.enter();
        int pivot = medianOfMedians(arr, lo, hi, metrics);
        int pivotIndex = SortUtils.partition(arr, lo, hi, pivot, metrics);
        metrics.exit();

        int leftSize = pivotIndex - lo;
        int rightSize = hi - pivotIndex;

        // Recurse into the smaller side first
        if (k < pivotIndex) {
            if (leftSize < rightSize) {
                return selectRecursive(arr, lo, pivotIndex - 1, k, metrics);
            } else {
                return selectRecursive(arr, lo, pivotIndex - 1, k, metrics);
            }
        } else if (k > pivotIndex) {
            if (rightSize < leftSize) {
                return selectRecursive(arr, pivotIndex + 1, hi, k, metrics);
            } else {
                return selectRecursive(arr, pivotIndex + 1, hi, k, metrics);
            }
        } else {
            return arr[pivotIndex];
        }
    }

    private static int medianOfMedians(int[] arr, int lo, int hi, Metrics metrics) {
        int n = hi - lo + 1;
        if (n <= 5) {
            insertionSort(arr, lo, hi, metrics);
            return arr[lo + n / 2];
        }

        int numMedians = 0;
        for (int i = lo; i <= hi; i += 5) {
            int subHi = Math.min(i + 4, hi);
            insertionSort(arr, i, subHi, metrics);
            int medianIdx = i + (subHi - i) / 2;
            SortUtils.swap(arr, lo + numMedians, medianIdx, metrics);
            numMedians++;
        }

        return selectRecursive(arr, lo, lo + numMedians - 1, lo + numMedians / 2, metrics);
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
