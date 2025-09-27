package com.algosassignment1.tests;

import com.algosassignment1.sorts.ClosestPair;
import com.algosassignment1.metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairTest {

    @Test
    void testRandomSmallArrays() {
        Random rnd = new Random();
        for (int t = 0; t < 50; t++) {
            int n = 2 + rnd.nextInt(10);
            ClosestPair.Point[] points = new ClosestPair.Point[n];
            for (int i = 0; i < n; i++) {
                points[i] = new ClosestPair.Point(rnd.nextDouble() * 100, rnd.nextDouble() * 100);
            }

            Metrics metrics = new Metrics();
            double dFast = ClosestPair.closestPairDistance(points, metrics);

            // Compare with brute-force
            double dBrute = Double.POSITIVE_INFINITY;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    double dx = points[i].x - points[j].x;
                    double dy = points[i].y - points[j].y;
                    double dist = Math.sqrt(dx * dx + dy * dy);
                    if (dist < dBrute) dBrute = dist;
                }
            }

            assertEquals(dBrute, dFast, 1e-9);
        }
    }

    @Test
    void testLargeArray() {
        Random rnd = new Random();
        int n = 5000;  // large array
        ClosestPair.Point[] points = new ClosestPair.Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new ClosestPair.Point(rnd.nextDouble() * 1000, rnd.nextDouble() * 1000);
        }

        Metrics metrics = new Metrics();
        double d = ClosestPair.closestPairDistance(points, metrics);
        assertTrue(d >= 0);
    }
}
