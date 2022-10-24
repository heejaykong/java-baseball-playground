package study;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {
    private StringCalculator objCalcUnderTest;

    @BeforeEach
    void setUp() {
        objCalcUnderTest = new StringCalculator();
    }

    @Test
    @DisplayName("문자열 계산기 덧셈 테스트")
    void add() {
        int result = objCalcUnderTest.add(10, 20);
        assertEquals(result, 30);
    }

    @Test
    @DisplayName("문자열 계산기 뺄셈 테스트")
    void subtract() {
        int result = objCalcUnderTest.subtract(10, 20);
        assertEquals(result, -10);
    }

    @Test
    @DisplayName("문자열 계산기 곱셈 테스트")
    void multiply() {
        int result = objCalcUnderTest.multiply(10, 20);
        assertEquals(result, 200);

        result = objCalcUnderTest.multiply(10, 0);
        assertEquals(result, 0);
    }

    @Test
    @DisplayName("문자열 계산기 나눗셈 테스트")
    void divide() {
        int result = objCalcUnderTest.divide(60, 5);
        assertEquals(result, 12);

        assertThatThrownBy(() -> objCalcUnderTest.divide(10, 0))
                .isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("/ by zero");
    }

    @ParameterizedTest
    @DisplayName("문자열 계산기 다수의 테스트케이스")
    @CsvSource(value = {"2 + 3 * 4 / 2 : 10", "2 - 3 * 40 / 2 : -20"}, delimiterString = " : ")
    void random(String input, String expected) {
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        objCalcUnderTest.start();
        assertEquals(out.toString().trim(), expected);
    }

    @AfterEach
    void terminate() {
        objCalcUnderTest = null;
    }
}
