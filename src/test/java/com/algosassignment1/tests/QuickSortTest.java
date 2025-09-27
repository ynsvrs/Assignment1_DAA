package com.algosassignment1.tests;

import com.algosassignment1.sorts.QuickSort;
import com.algosassignment1.metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {

    @Test
    void testRandomArray() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        Metrics metrics = new Metrics();
        QuickSort.sort(arr, metrics);
        assertTrue(isSorted(arr));
        assertTrue(metrics.getMaxDepth() > 0);
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        Metrics metrics = new Metrics();
        QuickSort.sort(arr, metrics);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        Metrics metrics = new Metrics();
        QuickSort.sort(arr, metrics);
        assertArrayEquals(new int[]{42}, arr);
    }

    private boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++)
            if (arr[i-1] > arr[i]) return false;
        return true;
    }
}
