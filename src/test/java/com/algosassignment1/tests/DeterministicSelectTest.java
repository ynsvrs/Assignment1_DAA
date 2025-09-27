package com.algosassignment1.tests;

import com.algosassignment1.sorts.DeterministicSelect;
import com.algosassignment1.metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class DeterministicSelectTest {

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
            int sel = DeterministicSelect.select(arr, k, metrics);

            assertEquals(copy[k], sel, "Failed on trial " + t);
        }
    }

    @Test
    void testSmallArray() {
        int[] arr = {5, 2, 9, 1, 3};
        Metrics metrics = new Metrics();
        int sel = DeterministicSelect.select(arr, 2, metrics);
        assertEquals(3, sel);
    }

    @Test
    void testEdgeCases() {
        int[] arr = {42};
        Metrics metrics = new Metrics();
        assertEquals(42, DeterministicSelect.select(arr, 0, metrics));

        int[] empty = {};
        Metrics metrics2 = new Metrics();
        assertThrows(IllegalArgumentException.class, () -> DeterministicSelect.select(empty, 0, metrics2));
    }
}
