import static java.util.Objects.isNull;

public class StringCalculator {
    private final String SPLITTER = "[,\n]+";

    public int add(String numbers) {
        if (isNull(numbers) || numbers.isEmpty()) {
            return 0;
        }

        String[] numbersArray = numbers.split(SPLITTER);
        int sum = 0;
        int number;
        for (String s : numbersArray) {
            number = Integer.parseInt(s);
            sum = Math.addExact(sum, number);
        }

        return sum;
    }

}
