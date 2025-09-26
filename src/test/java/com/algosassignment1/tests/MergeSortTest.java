package com.algosassignment1.tests;

import com.algosassignment1.sorts.MergeSort;
import com.algosassignment1.metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {

    @Test
    void testSmallArrayCorrectness() {
        int[] arr = {5, 2, 8, 1, 3};
        Metrics metrics = new Metrics();
        MergeSort.sort(arr, metrics);
        assertArrayEquals(new int[]{1,2,3,5,8}, arr);
    }

    @Test
    void testRandomArrays() {
        for (int t = 0; t < 100; t++) {
            int n = 50 + (int)(Math.random() * 50);
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = (int)(Math.random() * 1000);
            int[] copy = arr.clone();

            Metrics metrics = new Metrics();
            MergeSort.sort(arr, metrics);

            Arrays.sort(copy);
            assertArrayEquals(copy, arr);

            assertTrue(metrics.getMaxDepth() <= 2*Math.floor(Math.log(n)/Math.log(2)) + 5);
        }
    }

    @Test
    void testAdversarialCases() {
        Metrics metrics;

        int[] desc = {5,4,3,2,1};
        metrics = new Metrics();
        MergeSort.sort(desc, metrics);
        assertArrayEquals(new int[]{1,2,3,4,5}, desc);

        int[] equal = {7,7,7,7,7};
        metrics = new Metrics();
        MergeSort.sort(equal, metrics);
        assertArrayEquals(new int[]{7,7,7,7,7}, equal);
    }
}
