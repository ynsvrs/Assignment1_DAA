package com.algosassignment1.util;

import com.algosassignment1.metrics.Metrics;
import java.util.Random;

public final class SortUtils {

    private static final Random RANDOM = new Random();

    private SortUtils() {}

    // Swap two elements
    public static void swap(int[] arr, int i, int j, Metrics metrics) {
        if (i != j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    // Partition array using last element as pivot
    public static int partitionLastPivot(int[] arr, int lo, int hi, Metrics metrics) {
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

    // Shuffle array randomly
    public static void shuffle(int[] arr, Metrics metrics) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = RANDOM.nextInt(i + 1);
            swap(arr, i, j, metrics);
        }
    }

    // Partition by pivot value (for Deterministic Select)
    public static int partition(int[] arr, int lo, int hi, int pivotValue, Metrics metrics) {
        int pivotIndex = lo;
        for (int i = lo; i <= hi; i++) {
            metrics.incComparisons();
            if (arr[i] == pivotValue) {
                pivotIndex = i;
                break;
            }
        }
        swap(arr, pivotIndex, hi, metrics);

        int storeIndex = lo;
        for (int i = lo; i < hi; i++) {
            metrics.incComparisons();
            if (arr[i] < pivotValue) {
                swap(arr, i, storeIndex++, metrics);
            }
        }
        swap(arr, storeIndex, hi, metrics);
        return storeIndex;
    }
}

