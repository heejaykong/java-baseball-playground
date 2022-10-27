package study.baseball;

import java.util.*;

import java.util.stream.Collectors;

public class BaseballGame {
    static final int MIN = 1;
    static final int MAX = 9;
    static final int DIGITS_COUNT = 3;

    static Scanner scanner = new Scanner(System.in);

    static int getRandomDigit(int min, int max) {
        Random random = new Random();
        int randomDigit = random.nextInt(max - min + 1) + min;
        return randomDigit;
    }

    static ArrayList<Integer> generateAnswer() {
        Set<Integer> answer = new HashSet<>();
        while (answer.size() < DIGITS_COUNT) {
            int randomDigit = getRandomDigit(MIN, MAX);
            answer.add(randomDigit);
        }
        return new ArrayList<>(answer);
    }

    static boolean isNumeric(String userInput) {
        try {
            Integer.parseInt(userInput);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static boolean isInRange(ArrayList<Integer> list, int min, int max) {
        for (int digit : list) {
            if (digit < min || max < digit) {
                return false;
            }
        }
        return true;
    }

    static ArrayList<Integer> parseUserInput(String userInput) {
        List<String> stringList = new ArrayList<>(Arrays.asList(userInput.split("")));
        ArrayList<Integer> parsedList = stringList.stream()
                                        .map(item -> Integer.parseInt(item))
                                        .collect(Collectors.toCollection(ArrayList::new));
        return parsedList;
    }

    static ArrayList<Integer> validate(Scanner scanner, InputView inputView, ResultView resultView) {
        String userInput = scanner.nextLine().trim();
//        1. 크기가 3이 아닌 경우
        if (userInput.length() != DIGITS_COUNT) {
            resultView.rejectAnswer();
            inputView.askQuestion();
            return validate(scanner, inputView, resultView);
        }
//        2. 숫자가 아닌게 하나라도 있을 경우
        if (!isNumeric(userInput)) {
            resultView.rejectAnswer();
            inputView.askQuestion();
            return validate(scanner, inputView, resultView);
        }
//        3. 범위를 벗어난 숫자가 있을 경우
        ArrayList<Integer> parsedUserInput = parseUserInput(userInput);
        if (!isInRange(parsedUserInput, MIN, MAX)) {
            resultView.rejectAnswer();
            inputView.askQuestion();
            return validate(scanner, inputView, resultView);
        }
        return parsedUserInput;
    }

    static void start() {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();

//        1. 1~9 사이의 서로 다른 임의의 수 3개 발생시키기
        ArrayList<Integer> correctAnswer = generateAnswer();

//        2. 사용자가 맞출때까지 반복: "숫자를 입력해 주세요 : " 출력
        boolean isGameOver = false;
        while (!isGameOver) {
            inputView.askQuestion();

            ArrayList<Integer> validUserInput = validate(scanner, inputView, resultView);

            boolean isAnswer = correctAnswer.equals(validUserInput);
            isGameOver = isAnswer;
        }
//        4. 입력한 숫자에 따라 힌트 메시지 출력하기
//        5. "스트라이크" "볼" "낫싱"
    }

    public static void main(String[] args) {
        start();
    }
}
