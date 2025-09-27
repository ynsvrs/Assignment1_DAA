package com.algosassignment1.sorts;

import com.algosassignment1.metrics.Metrics;
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static class Point {
        public final double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
    }

    private ClosestPair() {}

    public static double closestPairDistance(Point[] points, Metrics metrics) {
        if (points == null || points.length < 2) return Double.POSITIVE_INFINITY;

        metrics.reset();
        Point[] Px = points.clone();
        Point[] Py = points.clone();

        Arrays.sort(Px, Comparator.comparingDouble(p -> p.x));
        Arrays.sort(Py, Comparator.comparingDouble(p -> p.y));

        return closestPairRecursive(Px, Py, 0, Px.length - 1, metrics);
    }

    private static double closestPairRecursive(Point[] Px, Point[] Py, int lo, int hi, Metrics metrics) {
        int n = hi - lo + 1;
        if (n <= 3) {
            metrics.enter();
            double minDist = bruteForce(Px, lo, hi, metrics);
            metrics.exit();
            return minDist;
        }

        metrics.enter();
        int mid = lo + (hi - lo) / 2;
        Point midPoint = Px[mid];

        Point[] PyLeft = new Point[mid - lo + 1];
        Point[] PyRight = new Point[hi - mid];
        int li = 0, ri = 0;
        for (Point p : Py) {
            if (p.x <= midPoint.x) PyLeft[li++] = p;
            else PyRight[ri++] = p;
        }

        double dl = closestPairRecursive(Px, PyLeft, lo, mid, metrics);
        double dr = closestPairRecursive(Px, PyRight, mid + 1, hi, metrics);
        double d = Math.min(dl, dr);


        Point[] strip = new Point[n];
        int stripCount = 0;
        for (Point p : Py) {
            if (Math.abs(p.x - midPoint.x) < d) strip[stripCount++] = p;
        }

        // Check strip points (classic 7 neighbor scan)
        for (int i = 0; i < stripCount; i++) {
            for (int j = i + 1; j < stripCount && (strip[j].y - strip[i].y) < d; j++) {
                metrics.incComparisons();
                double dist = distance(strip[i], strip[j], metrics);
                if (dist < d) d = dist;
            }
        }

        metrics.exit();
        return d;
    }

    private static double distance(Point a, Point b, Metrics metrics) {
        metrics.incAllocations();
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private static double bruteForce(Point[] Px, int lo, int hi, Metrics metrics) {
        double minDist = Double.POSITIVE_INFINITY;
        for (int i = lo; i <= hi; i++) {
            for (int j = i + 1; j <= hi; j++) {
                metrics.incComparisons();
                double d = distance(Px[i], Px[j], metrics);
                if (d < minDist) minDist = d;
            }
        }
        return minDist;
    }
}

