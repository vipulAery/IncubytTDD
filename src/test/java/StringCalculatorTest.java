import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {
    StringCalculator stringCalculator = new StringCalculator();
    @Test
    void addEmptyString() {
        assertEquals(0, stringCalculator.add(""));
    }

    @Test
    void addSingleNumberString() {
        assertEquals(1, stringCalculator.add("1"));
    }

    @Test
    void addTwoDigitSingleNumberString() {
        assertEquals(12, stringCalculator.add("12"));
    }

    @Test
    void addTwoNumbersString() {
        assertEquals(4, stringCalculator.add("1,3"));
    }

    @Test
    void addTwoDigitTwoNumberString() {
        assertEquals(62, stringCalculator.add("12,50"));
    }

    @Test
    void addNegativeSingleNumberString() {
        assertEquals(-13, stringCalculator.add("-13"));
    }

    @Test
    void addBothNegativeTwoNumberString() {
        assertEquals(-25, stringCalculator.add("-13,-12"));
    }

    @Test
    void addFirstNegativeTwoNumberString() {
        assertEquals(-1, stringCalculator.add("-13,12"));
    }

    @Test
    void addSecondNegativeTwoNumberString() {
        assertEquals(-1, stringCalculator.add("12,-13"));
    }

    @Test
    void addSmallNegativeTwoNumberString() {
        assertEquals(1, stringCalculator.add("13,-12"));
    }
}
