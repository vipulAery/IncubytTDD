import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {
    StringCalculator stringCalculator = new StringCalculator();
    @Test
    void addEmptyString() {
        assertEquals(0, stringCalculator.add(""));
    }
}
