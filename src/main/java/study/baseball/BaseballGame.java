package study.baseball;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BaseballGame {
    static final int MIN = 1;
    static final int MAX = 9;
    static final int DIGITS_COUNT = 3;
    static int getRandomDigit(int min, int max) {
        Random random = new Random();
        int randomDigit = random.nextInt(max - min + 1) + min;
        return randomDigit;
    }

    static Set<Integer> generateAnswer() {
        Set<Integer> answer = new HashSet<>();
        while (answer.size() < DIGITS_COUNT) {
            int randomDigit = getRandomDigit(MIN, MAX);
            answer.add(randomDigit);
        }
        return answer;
    }

    static void start() {
//        1. 1~9 사이의 서로 다른 임의의 수 3개 발생시키기
        Set<Integer> answer = generateAnswer();
//        2. 사용자가 맞출때까지 반복
//        3. "숫자를 입력해 주세요 : " 출력
//        4. 입력한 숫자에 따라 힌트 메시지 출력하기
//        5. "스트라이크" "볼" "낫싱"
    }

    public static void main(String[] args) {
        start();
    }
}
