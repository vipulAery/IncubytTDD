import static java.util.Objects.isNull;

public class StringCalculator {
    private final String SPLITTER_FORMAT = "[,\n%s]+";

    public int add(String numbers) {
        if (isNull(numbers) || numbers.isEmpty()) {
            return 0;
        }

        boolean customSplitterAvailable = isCustomSplitterAvailable(numbers);

        String[] numbersArray = getNumbersArray(numbers, customSplitterAvailable);
        int sum = 0;
        int number;
        for (String s : numbersArray) {
            number = Integer.parseInt(s);
            sum = Math.addExact(sum, number);
        }

        return sum;
    }

    private String[] getNumbersArray(String numbers, boolean customSplitterAvailable) {
        String splitAt = getSplitter(customSplitterAvailable, numbers);
        if(customSplitterAvailable) {
            numbers = numbers.substring(4);
        }
        return numbers.split(splitAt);
    }

    private String getSplitter(boolean customSplitterAvailable, String numbers) {
        return String.format(SPLITTER_FORMAT, customSplitterAvailable?numbers.charAt(2):"");
    }

    public boolean isCustomSplitterAvailable(String numbers) {
        return numbers.charAt(0) == '/' && numbers.charAt(1) == '/' && numbers.charAt(3) == '\n';
    }


}
