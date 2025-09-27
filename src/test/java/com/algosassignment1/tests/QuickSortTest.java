package com.algosassignment1.tests;

import com.algosassignment1.sorts.QuickSort;
import com.algosassignment1.metrics.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {

    @Test
    void testRandomArray() {
        int n = 100;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = (int)(Math.random() * 1000);

        Metrics metrics = new Metrics();
        QuickSort.sort(arr, metrics);

        // Checks correctness
        for (int i = 1; i < n; i++) {
            assertTrue(arr[i-1] <= arr[i]);
        }

        // Checks recursion depth
        assertTrue(metrics.getMaxDepth() <= 2 * Math.floor(Math.log(n) / Math.log(2)) + 5);
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
}

