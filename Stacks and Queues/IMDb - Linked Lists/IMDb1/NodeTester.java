package IMDb1;

public class NodeTester {

    public static void main(String[] args) {
        Actor head = new Actor("Pranitha");
        Actor next = new Actor(head.getName());
        head.setNextPtr(next);
        head.setName("Kaushik");
        next = head;
        head = new Actor("Ashley");
        head.setNextPtr(next);
        next = head;
        head = new Actor("Clover");
        head.setNextPtr(next);
        next = head;
        head = new Actor("Jess");
        head.setNextPtr(next);

        Actor current = head;
        while (current != null) {
            System.out.println(current);
            current = current.getNextPtr();
        }
    }

}
