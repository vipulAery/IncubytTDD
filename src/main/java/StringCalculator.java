import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

public class StringCalculator {
    private final String SPLITTER_FORMAT = "[,\n%s]+";
    private final Pattern NUMBERS_PATTERN = Pattern.compile("^//\\[(.+)\\]\n");
    public int add(String numbers) {
        if (isNull(numbers) || numbers.isEmpty()) {
            return 0;
        }


        String[] numbersArray = getNumbersArray(numbers);
        int sum = 0;
        int number;
        StringBuilder negativeNumbers = new StringBuilder();
        for (String s : numbersArray) {
            number = Integer.parseInt(s);
            if(number < 0) {
               negativeNumbers.append(' ').append(s);
            }
            if(negativeNumbers.length() == 0 && number < 1001) {
                sum = Math.addExact(sum, number);
            }
        }
        if (negativeNumbers.length() != 0) {
            throw new IllegalArgumentException(String.format("negatives not allowed :%s", negativeNumbers));
        }

        return sum;
    }

    private String[] getNumbersArray(String numbers) {
        Matcher matcher = NUMBERS_PATTERN.matcher(numbers);
        boolean customDelimiterAvailable = matcher.find();
        if(customDelimiterAvailable && numbers.length() > matcher.end()) {
            numbers = numbers.substring(matcher.end());
        }
        return numbers.split(getSplitter(matcher, customDelimiterAvailable));
    }

    private String getSplitter(Matcher matcher, boolean customDelimiterAvailable) {
        return String.format(SPLITTER_FORMAT, customDelimiterAvailable ?  "[".concat(matcher.group(1)).concat("]"): "");
    }


}
