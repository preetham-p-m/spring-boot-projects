import java.util.List;
import java.util.Optional;

public class OptionalGuide {

    public static void main(String[] args) throws Exception {
        List<String> fruits = List.of("apple", "banana", "cherry");

        Optional<String> first = fruits.stream().filter(fruit -> fruit.startsWith("b")).findFirst();

        first.isPresent();
        first.isEmpty();
        first.ifPresent(System.out::println);
        first.ifPresentOrElse(System.out::println, () -> System.out.println("Empty Method"));
    }

}
