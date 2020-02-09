public class StackStructure<E> {

    private E[] stack;
    private int logicalSize;

    public static void main(String[] args) {
        StackStructure stacky = new StackStructure(2);
        System.out.println("Is Empty: " + stacky.isEmpty());
        stacky.push(5);
        stacky.push(64);
        stacky.push(645);
        stacky.push(7);
        stacky.push(512);
        stacky.push("Henlo Wurld");
        stacky.push(512);
        System.out.println("Peek: " + stacky.peek());
        System.out.println(stacky);
        System.out.println("Popped: " + stacky.pop());
        System.out.println("Popped: " + stacky.pop());
        System.out.println("Size: " + stacky.size());
        System.out.println(stacky);
        while(!stacky.isEmpty()) {
            System.out.println("Popped: " + stacky.pop());
        }
        System.out.println(stacky);
    }

    public StackStructure(int size) {
        logicalSize = 0;
        stack = (E[])new Object[size];
    }

    public E peek() {
        if(logicalSize > 0) {
            return stack[logicalSize - 1];
        }
        else {
            return null;
        }
    }

    public boolean push(E element) {
        if (logicalSize >= Integer.MAX_VALUE - 1) {
            return false;
        }
        else {
            if (logicalSize < stack.length) {
                stack[logicalSize] = element;
            }
            else {
                setPhysicalSize(logicalSize * 2);
                stack[logicalSize] = element;
            }
            logicalSize++;
        }
        return true;
    }

    public E pop() {
        E element = stack[logicalSize - 1];
        logicalSize--;
        if(stack.length / 3 > logicalSize) {
            setPhysicalSize(stack.length / 2);
        }
        return element;
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
                list.append(stack[i]);
            }
            else {
                list.append(stack[i]).append(", ");
            }
        }
        list.append("}");
        return list.toString();
    }

    public boolean setPhysicalSize(int size) {
        if (size >= 0) {
            if (stack != null) {
                E[] newStack = (E[])new Object[size];
                for (int i = 0; i < logicalSize; i++) {
                    newStack[i] = stack[i];
                }
                stack = newStack;
            } else {
                stack = (E[])new Object[size];
            }
            return true;
        }
        return false;
    }

}
