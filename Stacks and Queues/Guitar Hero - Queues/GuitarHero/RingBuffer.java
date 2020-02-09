package GuitarHero;

import java.util.Arrays;
import java.util.Random;

public class RingBuffer {

    private double[] ring;
    int first;
    int last;
    private int size;
    private static Random random = new Random();

    public RingBuffer(int capacity) {
        ring = new double[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isFull() {
        return size() == ring.length;
    }

    public void enqueue(double x) {
        //printDebug();
        if (isFull()) throw new IllegalStateException("Attempt to use method enqueue while ring buffer is full.");
        if (last == ring.length) last = 0;
        ring[last] = x;
        size++;
        last++;
    }

    public double dequeue() {
        //printDebug();
        if (isEmpty()) throw new IllegalStateException("Attempt to use method dequeue while ring buffer is empty.");
        double value = ring[first];
        first++;
        if (first == ring.length) first = 0;
        size--;
        return value;
    }

    public void randomise() {
        for (int i = 0; i < ring.length; i++) {
            ring[i] = random.nextDouble() - 0.5;
        }
    }

    public double peek() {
        return ring[first];
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        for (int i = first; i != last; i++) {
            if (i == ring.length) i = 0;
            builder.append(ring[i] + ", ");
        }
        builder.append("}");
        return builder.toString();
    }

    private String debugToString() {
        StringBuilder builder = new StringBuilder("{");
        for (int i = 0; i < ring.length; i++) {
            builder.append(ring[i] + ", ");
        }
        builder.append("}");
        return builder.toString();
    }

    public void printDebug() {
        System.out.println("Dequeue");
        System.out.println("To String: " + toString());
        System.out.println("Debug To String: " + debugToString());
        System.out.println("First: " + first + " - Last: " + last);
        System.out.println("Size: " + size);
    }

}
