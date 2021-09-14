import static java.util.Objects.isNull;

public class StringCalculator {
    private final String SPLITTER_FORMAT = "[,\n%s]+";
    private final int STARTING_INDEX_OF_NUMBERS = 4;

    public int add(String numbers) {
        if (isNull(numbers) || numbers.isEmpty()) {
            return 0;
        }


        String[] numbersArray = getNumbersArray(numbers);
        int sum = 0;
        int number;
        for (String s : numbersArray) {
            number = Integer.parseInt(s);
            sum = Math.addExact(sum, number);
        }

        return sum;
    }

    private String[] getNumbersArray(String numbers) {
        boolean customSplitterAvailable = isCustomSplitterAvailable(numbers);
        String splitAt = getSplitter(customSplitterAvailable, numbers);
        if (customSplitterAvailable) {
            numbers = numbers.substring(STARTING_INDEX_OF_NUMBERS);
        }
        return numbers.split(splitAt);
    }

    private String getSplitter(boolean customSplitterAvailable, String numbers) {
        return String.format(SPLITTER_FORMAT, customSplitterAvailable ? numbers.charAt(2) : "");
    }

    public boolean isCustomSplitterAvailable(String numbers) {
        return numbers.charAt(0) == '/' && numbers.charAt(1) == '/' && numbers.charAt(3) == '\n';
    }


}
