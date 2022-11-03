package study.baseball;

import java.util.*;

import java.util.stream.Collectors;

public class BaseballGame {

    final int MIN = 1;
    final int MAX = 9;
    final int DIGITS_COUNT = 3;
    final String RESTART = "1";
    final String QUIT = "2";

    private final InputView inputView = new InputView();
    private final ResultView resultView = new ResultView();
    private ArrayList<Integer> correctAnswer = null;

    int getRandomDigit() {
        Random random = new Random();
        return random.nextInt(MAX - MIN + 1) + MIN;
    }

    ArrayList<Integer> generateAnswer() {
        Set<Integer> answer = new HashSet<>();
        while (answer.size() < DIGITS_COUNT) {
            int randomDigit = getRandomDigit();
            answer.add(randomDigit);
        }
        return new ArrayList<>(answer);
    }

    boolean isInRange(String userInput) {
        ArrayList<Integer> list = parseUserInput(userInput);
        for (int digit : list) {
            if (digit < MIN || MAX < digit) {
                return false;
            }
        }
        return true;
    }

    boolean isNumeric(String userInput) {
        try {
            Integer.parseInt(userInput);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    boolean isSizeCorrect(String userInput) {
        return userInput.length() == DIGITS_COUNT;
    }

    ArrayList<Integer> parseUserInput(String userInput) {
        List<String> stringList = new ArrayList<>(Arrays.asList(userInput.split("")));
        return stringList.stream()
            .map(Integer::parseInt)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    ArrayList<Integer> validate(String userInput) {
        // 1. 크기가 3이 아닌 경우
        // 2. 숫자가 아닌게 하나라도 있을 경우
        // 3. 범위를 벗어난 숫자가 있을 경우
        if (!isSizeCorrect(userInput) || !isNumeric(userInput) || !isInRange(userInput)) {
            resultView.rejectAnswer();
            userInput = inputView.askDigits();
            return validate(userInput);
        }
        ArrayList<Integer> parsedUserInput = parseUserInput(userInput);
        return parsedUserInput;
    }

    int[] countStrikeAndBall(ArrayList<Integer> answer, ArrayList<Integer> guess) {
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
        return new int[] {strikeCount, ballCount};
    }

    void giveSomeHint(ArrayList<Integer> answer, ArrayList<Integer> guess) {
        int[] strikeAndBall = countStrikeAndBall(answer, guess);
        int strikeCount = strikeAndBall[0];
        int ballCount = strikeAndBall[1];
        resultView.printHint(strikeCount, ballCount);
    }

    boolean isToContinue(String restartInput) {
        while (!restartInput.equals(RESTART) && !restartInput.equals(QUIT)) {
            resultView.rejectAnswer();
            restartInput = inputView.askToContinue();
        }

        if (restartInput.equals(QUIT)) {
            return false;
        }
        correctAnswer = generateAnswer();
        return true;
    }

    boolean isCorrectAndWantToQuit(ArrayList<Integer> answer, ArrayList<Integer> guess) {
        if (!guess.equals(answer)) {
            giveSomeHint(answer, guess);
            return false;
        }
        resultView.printSuccessMsg(DIGITS_COUNT);
        String continueInput = inputView.askToContinue();
        boolean toContinue = isToContinue(continueInput);
        return !toContinue;
    }

    void start() {
        // 1. 1~9 사이의 서로 다른 임의의 수 3개 발생시키기
        correctAnswer = generateAnswer();

        // 2. 사용자가 맞출때까지 반복: "숫자를 입력해 주세요 : " 출력
        boolean isGameOver = false;
        while (!isGameOver) {
            String userInput = inputView.askDigits();

            // 3. 입력한 숫자에 따라 힌트 메시지 출력하기: "스트라이크" "볼" "낫싱" 출력
            ArrayList<Integer> validUserInput = validate(userInput);
            isGameOver = isCorrectAndWantToQuit(correctAnswer, validUserInput);
        }
    }
}
