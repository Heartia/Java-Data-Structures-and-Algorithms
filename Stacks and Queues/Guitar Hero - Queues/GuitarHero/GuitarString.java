package GuitarHero;

import java.util.Random;

public class GuitarString {

    private RingBuffer ringBuffer;
    private Random random;
    private int ticCalled;
    private int capacity;

    public GuitarString(double frequency) {
        capacity = (int)Math.ceil(44100 / frequency);
        ringBuffer = new RingBuffer(capacity);
        for (int i = 0; i < capacity; i++)
            ringBuffer.enqueue(0);
        random = new Random();
    }

    public GuitarString(double[] init) {
        ringBuffer = new RingBuffer(init.length);
        for (int i = 0; i < init.length; i++)
            ringBuffer.enqueue(init[i]);
        random = new Random();
    }

    public void pluck() {
        //System.out.println("Pluck is being called");
        //ringBuffer.printDebug();
        while (ringBuffer.size() > 1) {
            ringBuffer.dequeue();
        }
        //System.out.println(ringBuffer.first + " " + ringBuffer.last);
        for (int i = ringBuffer.first; i != ringBuffer.last; i++) {
            if (i == capacity) i = 0;
            if (!ringBuffer.isFull()) ringBuffer.enqueue(random.nextDouble() - .5);
        }
    }

    public void tic() {
        double first = ringBuffer.dequeue();
        double second = ringBuffer.peek();
        ringBuffer.enqueue(0.994 * ((first + second) / 2));
        ticCalled++;
    }

    public double sample() {
        return ringBuffer.peek();
    }

    public int time() {
        return ticCalled;
    }

}
