package study.baseball;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BaseballGameTest {

    private BaseballGame gameUnderTest;

    @BeforeEach
    void setUp() {
        gameUnderTest = new BaseballGame();
    }

    @Test
    @DisplayName("임의의 수 3개가 서로 다른지 테스트")
    void generateAnswerIsUnique() {
        ArrayList<Integer> randomNumbers = gameUnderTest.generateAnswer();
        Set<Integer> toSet = new HashSet<>(randomNumbers);
        assertEquals(toSet.size(), randomNumbers.size());
    }

    @Test
    @DisplayName("임의의 수 3개가 전부 범위 내에 있는지 테스트")
    void generateAnswerIsInRange() {
        ArrayList<Integer> randomNumbers = gameUnderTest.generateAnswer();
        for (int randomNumber : randomNumbers) {
            assertThat(randomNumber).isBetween(gameUnderTest.MIN, gameUnderTest.MAX);
        }
    }

    @Test
    @DisplayName("사용자입력을 ArrayList<Integer>로 변환하는 메소드 테스트")
    void parseUserInput() {
        ArrayList<Integer> result = gameUnderTest.parseUserInput("123");
        assertEquals(result, Arrays.asList(1, 2, 3));
    }

    @Test
    @DisplayName("사용자입력 유효성 검사 메소드 테스트")
    void validate() {
        String wrongInput1 = "1234";
        String wrongInput2 = "hey\n메롱";
        String wrongInput3 = "190";
        String correctInput = "123";

        assertFalse(gameUnderTest.isSizeCorrect(wrongInput1));
        assertFalse(gameUnderTest.isNumeric(wrongInput2));
        assertFalse(gameUnderTest.isInRange(wrongInput3));

        assertTrue(gameUnderTest.isSizeCorrect(correctInput));
        assertTrue(gameUnderTest.isNumeric(correctInput));
        assertTrue(gameUnderTest.isInRange(correctInput));
    }

    @ParameterizedTest
    @DisplayName("힌트(볼,스트라이크) 주는 메소드 테스트")
    @CsvSource(value = {"123:30", "129:20", "189:10", "789:00", "781:01", "731:02", "312:03", "132:12"}, delimiterString = ":")
    void giveSomeHint(String input, String expected) {
        ArrayList<Integer> answer = new ArrayList<>(Arrays.asList(1, 2, 3));

        ArrayList<Integer> guess = gameUnderTest.parseUserInput(input);
        int[] expectedResult = Arrays.stream(expected.split("")).mapToInt(Integer::parseInt)
            .toArray();

        int[] actualResult = gameUnderTest.countStrikeAndBall(answer, guess);
        assertArrayEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("게임 재시작 메소드 테스트")
    void isToContinue() {
        assertTrue(gameUnderTest.isToContinue("1"));
        assertFalse(gameUnderTest.isToContinue("2"));
    }

    @AfterEach
    void terminate() {
        gameUnderTest = null;
    }
}
