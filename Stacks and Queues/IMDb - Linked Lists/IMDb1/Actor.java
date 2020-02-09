package IMDb1;

public class Actor {

    private Actor next;
    private String name;

    Actor() {
        next = null;
    }

    Actor(String name) {
        next = null;
        this.name = name;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    Actor getNextPtr() {
        return next;
    }

    void setNextPtr(Actor next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name = '" + name + '\'' +
                '}';
    }
}
