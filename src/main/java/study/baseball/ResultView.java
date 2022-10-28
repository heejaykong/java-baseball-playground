package study.baseball;

public class ResultView {
    final String STRIKE = "스트라이크";
    final String BALL = "볼";
    final String NOTHING = "낫싱";
    final String EXCEPTION_MSG = "잘못 입력하셨습니다.";

    void rejectAnswer() {
        System.out.println(EXCEPTION_MSG);
    }

    void printHint(int strikeCount, int ballCount) {
        if (strikeCount == 0 && ballCount == 0) {
            System.out.println(NOTHING);
            return;
        }

        if (ballCount > 0 && strikeCount > 0) {
            System.out.println(ballCount + " " + BALL + " " + strikeCount + " " +  STRIKE);
            return;
        }

        if (ballCount > 0) {
            System.out.println(ballCount + " " +  BALL);
            return;
        }

        System.out.println(strikeCount + " " + STRIKE);
    }
}
