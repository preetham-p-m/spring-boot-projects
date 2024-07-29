package program;

import java.util.List;

public class LamdaExpression {
    public static void main(String[] args) {
        printSquareOfNumebrs(List.of(2, 4, 5, 12, 34, 2, 7, 5));
    }

    // #region Structured

    public static void printAllNumbersInListStructured(List<Integer> numbers) {
        for (var number : numbers) {
            System.out.println(number);
        }
    }

    // #endregion

    // #region Functional Method Reference

    public static void printAllNumbersInListFunctional(List<Integer> numbers) {
        numbers.stream().forEach(LamdaExpression::print);
    }

    private static void print(int number) {
        System.out.println(number);
    }

    // #endregion

    // #region Functional With System Out

    public void printAllNumbersInListFunctionalWithSystemOut(List<Integer> numbers) {
        numbers.stream().forEach(System.out::println);
    }

    // #endregion

    // #region Functional Even numbers

    public static void printEvenNumbersInListFunctionalWithSystemOut(List<Integer> numbers) {
        numbers
                .stream()
                .filter(number -> number % 2 == 0)
                .forEach(System.out::println);
    }

    // #endregion

    public static void printStringIfItContainsSpring(List<String> strings) {
        strings.stream().filter(str -> str.contains("Spring")).forEach(System.out::println);
    }

    public static void printStringsHaveAtleastFourLetters(List<String> strings) {
        strings.stream().filter(str -> str.length() >= 4).forEach(System.out::println);
    }

    public static void printSquareOfNumebrs(List<Integer> numbers) {
        numbers.stream().map(num -> num * num).forEach(System.out::println);
    }
}
