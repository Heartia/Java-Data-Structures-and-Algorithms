import java.util.NoSuchElementException;

public class QueueStructure<E> {

    private E[] queue;
    private int logicalSize;

    public static void main(String[] args) {
        QueueStructure queue = new QueueStructure(2);
        System.out.println(queue.poll());
        System.out.println(queue.peek());
        System.out.println(queue.add(5));
        System.out.println(queue.add(8));
        System.out.println(queue.add(11));
        System.out.println(queue.peek());
        System.out.println(queue.remove());
        System.out.println(queue);
        for (int i = 0; i < 10; i++) {
            queue.add(i * 5);
        }
        System.out.println(queue);
        for (int i = 0; i < 5; i++) {
            queue.remove();
        }
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());
        System.out.println(queue);
        while (!queue.isEmpty()) {
            queue.remove();
        }
        System.out.println(queue);
    }

    public QueueStructure(int size) {
        logicalSize = 0;
        queue = (E[])new Object[size];
    }

    public boolean add(E element) {
        if (logicalSize >= Integer.MAX_VALUE - 1) {
            return false;
        }
        else {
            if (logicalSize < queue.length) {
                queue[logicalSize] = element;
            }
            else {
                setPhysicalSize(logicalSize * 2);
                queue[logicalSize] = element;
            }
            logicalSize++;
        }
        return true;
    }

    public E peek() {
        if(logicalSize > 0) {
            return queue[0];
        }
        else {
            return null;
        }
    }

    public E remove() {
        E element = peek();
        if (logicalSize > 0) {
            for (int i = 1; i < logicalSize; i++) {
                queue[i - 1] = queue[i];
            }
            logicalSize--;
            return element;
        }
        else {
            throw new NoSuchElementException();
        }
    }

    public E poll() {
        E element = peek();
        if (logicalSize > 0) {
            for (int i = 1; i < logicalSize; i++) {
                queue[i - 1] = queue[i];
            }
            logicalSize--;
            return element;
        }
        else {
            return null;
        }
    }

    public E element() {
        if(logicalSize > 0) {
            return queue[0];
        }
        else {
            throw new NoSuchElementException("Queue is empty");
        }
    }

    public boolean isEmpty() {
        return logicalSize == 0;
    }

    public int size() {
        return logicalSize;
    }

    public String toString() {
        StringBuilder list = new StringBuilder("{");
        for (int i = 0; i < logicalSize; i++) {
            if (i + 1 == logicalSize) {
                list.append(queue[i]);
            }
            else {
                list.append(queue[i]).append(", ");
            }
        }
        list.append("}");
        return list.toString();
    }

    public boolean setPhysicalSize(int size) {
        if (size >= 0) {
            if (queue != null) {
                E[] newQueue = (E[])new Object[size];
                for (int i = 0; i < logicalSize; i++) {
                    newQueue[i] = queue[i];
                }
                queue = newQueue;
            } else {
                queue = (E[])new Object[size];
            }
            return true;
        }
        return false;
    }

}
