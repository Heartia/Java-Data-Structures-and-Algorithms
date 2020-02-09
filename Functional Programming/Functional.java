import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Functional {

    public static void main(String[] args) {
        Random random = new Random();
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(random.nextInt(10), random.nextInt(10), random.nextInt(10)));

        // 1. Print each element in the list on a separate line.
        nums.forEach(System.out::println);

        // 2. Remove all even numbers from the list.
        nums.removeIf(a -> a % 2 == 0 && a != 0);
        System.out.println();
        nums.forEach(n -> System.out.print(n + ", "));
        for (int i = 0; i < 20; i++) {
            nums.add(random.nextInt(10));
        }
        System.out.println();
        nums.forEach(n -> System.out.print(n + ", "));

        // 3. Print the number of odd numbers in the list.
        System.out.println();
        System.out.println("# of odd numbers: " + nums.stream().filter(a -> a % 2 == 1).count());

        // 4. Print all elements in a list of strings that begin with a.
        ArrayList<String> names = new ArrayList<String>(Arrays.asList("Emma", "Alice", "Shruthi", "Nathan", "Neha", "Autumn", "Saraswati", "Mira", "Itsuki"));
        System.out.println(Arrays.toString(names.stream().filter(n -> n.charAt(0) == 'A' || n.charAt(0) == 'a').toArray()));

        // 5. Double the value of all numbers in an integer list.
        List<Integer> newNums = nums.stream().map(n -> n * 2).collect(Collectors.toList());
        newNums.forEach(n -> System.out.print(n + ", "));
        System.out.println();

        // 6. CodingBat problem
        System.out.println(square56(new ArrayList<>(Arrays.asList(3, 1, 4))));
    }

    // 6. CodingBat problem
    private static List<Integer> square56(List<Integer> nums) {
        return nums.stream().map(n -> (n * n) + 10).filter(n -> n % 10 != 5 && n % 10 != 6).collect(Collectors.toList());
    }
}
