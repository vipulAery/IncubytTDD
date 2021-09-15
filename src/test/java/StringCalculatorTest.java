import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void addNegativeSingleNumberStringThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> stringCalculator.add("-13"));
        assertEquals("negatives not allowed : -13", exception.getMessage());
    }

    @Test
    void addSingleLongNumberString() {
        assertThrows(NumberFormatException.class, () -> stringCalculator.add("123456789012345"));
    }

    @Test
    void addTwoNumbersStringContainingLongNumberThrowsException() {
        assertThrows(NumberFormatException.class, () -> stringCalculator.add("12345,2147483648"));
    }

    @Test
    void addTwoNumbersStringContainingIntgerMaxValue() {
        assertEquals(2147483647, stringCalculator.add("2147483647"));
    }

    @Test
    void addTwoNumbersStringContainingIntgerMaxValueWithIntgerOverFlowThrowsException() {
        assertThrows(Exception.class, () -> stringCalculator.add("3,2147483647"));

    }

    @Test
    void addMultipleNumbersString() {
        assertEquals(13, stringCalculator.add("1,3,4,5"));
    }

    @Test
    void addMultipleNumbersStringWithNegativeAndPositiveIntegersThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> stringCalculator.add("1,-3,4,-5"));
        assertEquals("negatives not allowed : -3 -5", exception.getMessage());
    }

    @Test
    void addMultipleNumbersStringWithTwoTypesOfDelimiters() {
        assertEquals(19, stringCalculator.add("1\n3\n4,5,6"));
    }

    @Test
    void addMultipleNumbersStringWithCustomDelimiter() {
        assertEquals(19, stringCalculator.add("//;\n1;3;4;5;6"));
    }

    @Test
    void addMultipleNumbersStringWithCustomAsWellDefaultDelimiters() {
        assertEquals(19, stringCalculator.add("//\t\n1\n3\n4,5\t6"));
    }

}
