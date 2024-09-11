package org.seydaliev;

public class Statistics {
    private int count;
    private double sum;
    private double min;
    private double max;

    public void add(double value) {
        count++;
        sum+=value;
        if (count == 1 || value < min) {
            min = value;
        }
        if (count == 1 || value > max) {
            max = value;
        }
    }

    public int getCount() {
        return count;
    }

    public double getSum() {
        return sum;
    }

    public double getMin() {
        return min;
    }
    public double getMax() {
        return max;
    }

    public double getAverage() {
        return count > 0 ? sum / count : 0;
    }
}
