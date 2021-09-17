import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class StringCalculatorTest {
    StringCalculator stringCalculator = new StringCalculator();

    @Nested
    @DisplayName("When the numbers are valid")
    class ValidNumbers {
        @ParameterizedTest(name = "ExpectedSum = {0}, numbers = {1}")
        @CsvSource({
                "0, ''",
                "1, 1",
                "12, 12",
                "4, '1,3'",
                "62, '12,50'",
                "13, '1,3,4,5'",
                "0, '2147483647'"
        }
        )
        @DisplayName("add numbers having default delimiters")
        void addNumbers(int expectedSum, String numbers) {
            assertEquals(expectedSum, stringCalculator.add(numbers));
        }

        @ParameterizedTest(name = "ExpectedSum = {0}, numbers = {1}")
        @CsvSource({
                "19, '1\n3\n4,5,6'",
                "19, '//[;]\n1;3;4;5;6'",
                "19, '//[\t\t\t]\n1\n3\t\t4,5\t\t6'",
                "26, '//[,,,]\n1,3,,,4,5,,,6,7'",
                "14, '//[\t]\n1\n3\n4,1001\t6'",
                "30, '//[\t][;][*]\n1\n3;4,1001\t6\t9*7'",
                "30, '//[\t][\t\t][**]\n1\n3\n4,1001\t6\t\t9**7'"
        }
        )
        @DisplayName("add numbers having custom delimiters")
        void addNumbersWithCustomDelimiters(int expectedSum, String numbers) {
            assertEquals(expectedSum, stringCalculator.add(numbers));
        }
    }

    @Nested
    @DisplayName("When the numbers are invalid")
    class InvalidNumbers {
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
        void addTwoNumbersStringContainingIntgerMaxValueWithIntgerOverFlowThrowsException() {
            assertThrows(Exception.class, () -> stringCalculator.add(getTestDataForIntegerOverFlow()));
        }

        private String getTestDataForIntegerOverFlow() {
            StringBuilder stringBuilder = new StringBuilder(90000000);
            stringBuilder.append("999,1000");
            for (int i = 0; i < 10000000; i++) {
                stringBuilder.append(",999,1000");
            }
            return stringBuilder.toString();
        }

        @Test
        void addMultipleNumbersStringWithNegativeAndPositiveIntegersThrowsException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> stringCalculator.add("1,-3,4,-5"));
            assertEquals("negatives not allowed : -3 -5", exception.getMessage());
        }
    }
}
