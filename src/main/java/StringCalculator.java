import static java.util.Objects.isNull;

public class StringCalculator {
    private final String SPLITTER = ",";
    public int add(String numbers) {
        if(isNull(numbers) || numbers.isEmpty()) {
            return 0;
        }

        String[] numbersArray = numbers.split(SPLITTER);
        int number1 = Integer.parseInt(numbersArray[0]);
        if(numbersArray.length == 1) {
            return number1;
        }
        int number2 = Integer.parseInt(numbersArray[1]);

        return Math.addExact(number1, number2);
    }

}
