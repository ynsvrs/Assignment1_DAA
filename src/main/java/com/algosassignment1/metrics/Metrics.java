package com.algosassignment1.metrics;

public class Metrics {
    private long comparisons = 0;
    private long allocations = 0;
    private int currentDepth = 0;
    private int maxDepth = 0;

    public synchronized void incComparisons() { comparisons++; }
    public synchronized void incAllocations() { allocations++; }

    public void enter() {
        currentDepth++;
        if (currentDepth > maxDepth) maxDepth = currentDepth;
    }

    public void exit() {
        currentDepth = Math.max(0, currentDepth - 1);
    }

    public long getComparisons() { return comparisons; }
    public long getAllocations() { return allocations; }
    public int getMaxDepth() { return maxDepth; }

    public void reset() {
        comparisons = 0;
        allocations = 0;
        currentDepth = 0;
        maxDepth = 0;
    }
}



