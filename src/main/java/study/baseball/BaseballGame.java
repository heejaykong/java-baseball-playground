package study.baseball;

import java.util.*;

import java.util.stream.Collectors;

public class BaseballGame {

    final int MIN = 1;
    final int MAX = 9;
    final int DIGITS_COUNT = 3;
    final String RESTART = "1";
    final String QUIT = "2";

    ArrayList<Integer> correctAnswer = null;

    int getRandomDigit(int min, int max) {
        Random random = new Random();
        int randomDigit = random.nextInt(max - min + 1) + min;
        return randomDigit;
    }

    ArrayList<Integer> generateAnswer() {
        Set<Integer> answer = new HashSet<>();
        while (answer.size() < DIGITS_COUNT) {
            int randomDigit = getRandomDigit(MIN, MAX);
            answer.add(randomDigit);
        }
        return new ArrayList<>(answer);
    }

    boolean isNumeric(String userInput) {
        try {
            Integer.parseInt(userInput);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    boolean isInRange(ArrayList<Integer> list, int min, int max) {
        for (int digit : list) {
            if (digit < min || max < digit) {
                return false;
            }
        }
        return true;
    }

    ArrayList<Integer> parseUserInput(String userInput) {
        List<String> stringList = new ArrayList<>(Arrays.asList(userInput.split("")));
        ArrayList<Integer> parsedList = stringList.stream()
            .map(item -> Integer.parseInt(item))
            .collect(Collectors.toCollection(ArrayList::new));
        return parsedList;
    }

    ArrayList<Integer> validate(String userInput, InputView inputView,
        ResultView resultView) {
        // 1. 크기가 3이 아닌 경우
        if (userInput.length() != DIGITS_COUNT) {
            resultView.rejectAnswer();
            userInput = inputView.askDigits();
            return validate(userInput, inputView, resultView);
        }
        // 2. 숫자가 아닌게 하나라도 있을 경우
        if (!isNumeric(userInput)) {
            resultView.rejectAnswer();
            userInput = inputView.askDigits();
            return validate(userInput, inputView, resultView);
        }
        // 3. 범위를 벗어난 숫자가 있을 경우
        ArrayList<Integer> parsedUserInput = parseUserInput(userInput);
        if (!isInRange(parsedUserInput, MIN, MAX)) {
            resultView.rejectAnswer();
            userInput = inputView.askDigits();
            return validate(userInput, inputView, resultView);
        }
        return parsedUserInput;
    }

    void giveSomeHint(ArrayList<Integer> answer, ArrayList<Integer> guess,
        ResultView resultView) {
        int strikeCount = 0;
        int ballCount = 0;

        for (int i = 0; i < DIGITS_COUNT; i++) {
            int currentGuess = guess.get(i);
            int currentAnswer = answer.get(i);

            // 같은 수가 같은 자리에 있을 경우: 스트라이크 출력
            if (currentGuess == currentAnswer) {
                strikeCount += 1;
                continue;
            }
            // 같은 수가 다른 자리에 있을 경우: 볼 출력
            if (answer.contains(currentGuess)) {
                ballCount += 1;
            }
        }
        resultView.printHint(strikeCount, ballCount);
    }

    boolean guessTheAnswer(ArrayList<Integer> answer, ArrayList<Integer> guess,
        InputView inputView, ResultView resultView) {
        if (!guess.equals(answer)) {
            giveSomeHint(answer, guess, resultView);
            return false;
        }
        resultView.printSuccessMsg(DIGITS_COUNT);
        String restartInput = inputView.askToRestart();

        while (!restartInput.equals(RESTART) && !restartInput.equals(QUIT)) {
            resultView.rejectAnswer();
            restartInput = inputView.askToRestart();
        }

        if (restartInput.equals(RESTART)) {
            correctAnswer = generateAnswer();
            return false;
        }
        return true;
    }

    void start() {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();

        // 1. 1~9 사이의 서로 다른 임의의 수 3개 발생시키기
        correctAnswer = generateAnswer();

        // 2. 사용자가 맞출때까지 반복: "숫자를 입력해 주세요 : " 출력
        boolean isGameOver = false;
        while (!isGameOver) {
            String userInput = inputView.askDigits();

            // 3. 입력한 숫자에 따라 힌트 메시지 출력하기: "스트라이크" "볼" "낫싱" 출력
            ArrayList<Integer> validUserInput = validate(userInput, inputView, resultView);
            isGameOver = guessTheAnswer(correctAnswer, validUserInput, inputView, resultView);
        }
    }
}
