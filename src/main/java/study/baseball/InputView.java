package study.baseball;

import java.util.Scanner;

public class InputView {

    final String INPUT_MSG = "숫자를 입력해 주세요 : ";

    Scanner scanner = new Scanner(System.in);

    String askDigits() {
        System.out.print(INPUT_MSG);
        String userInputDigits = scanner.nextLine().trim();
        return userInputDigits;
    }

    String askToRestart() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String restartInput = scanner.nextLine().trim();
        return restartInput;
    }
}
