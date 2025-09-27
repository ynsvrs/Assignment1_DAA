package com.algosassignment1.tests;

import com.algosassignment1.metrics.Metrics;
import com.algosassignment1.sorts.DeterministicSelect;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class DeterministicSelectTest {

    @Test
    void testSmallArray() {
        int[] arr = {7, 2, 5, 3, 1};
        Metrics metrics = new Metrics();
        assertEquals(3, DeterministicSelect.select(arr, 2, metrics));
    }

    @Test
    void testRandomArrays() {
        for (int t = 0; t < 100; t++) {
            int n = 50 + (int)(Math.random() * 50);
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = (int)(Math.random() * 1000);

            int k = (int)(Math.random() * n);
            int[] copy = arr.clone();
            Arrays.sort(copy);

            Metrics metrics = new Metrics();
            int result = DeterministicSelect.select(arr, k, metrics);

            assertEquals(copy[k], result);
        }
    }

    @Test
    void testEmptyArrayThrows() {
        int[] arr = {};
        Metrics metrics = new Metrics();
        assertThrows(IllegalArgumentException.class, () -> DeterministicSelect.select(arr, 0, metrics));
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        Metrics metrics = new Metrics();
        assertEquals(42, DeterministicSelect.select(arr, 0, metrics));
    }
}