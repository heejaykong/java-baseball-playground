# 숫자 야구 게임 구현(w/ 단위테스트)

### 구현할 기능 목록
* 게임이 시작하면 1에서 9까지 서로 다른 임의의 수 3개 발생시키키
* 게임이 종료할 때까지 사용자의 입력을 반복해서 받기
  * `숫자를 입력해 주세요 :` 출력
  * 예외처리)
    * `1234`, `012`, `12 3`, `1k3`, `메롱` 등 규칙에  어긋나는 입력은 `잘못 입력하셨습니다.` 출력
* 입력한 숫자에 따라 힌트 메시지를 출력하기
  * 같은 수가 같은 자리에 있을 경우: `스트라이크` 출력
  * 같은 수가 다른 자리에 있을 경우: `볼` 출력
  * 같은 수가 전혀 없을 경우: `낫싱` 출력
  * e.g.)
    * `n볼`
    * `n스트라이크`
    * `n볼 m스트라이크`
    * `낫싱`
* 컴퓨터가 발생시킨 임의의 수를 사용자가 맞추면 게임 종료
  * `3개의 숫자를 모두 맞히셨습니다! 게임 종료` 출력
  * `게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.` 출력
  * 사용자가 1을 입력하면 게임 재개하기
  * 사용자가 2를 입력하면 게임 완전히 종료하기
    * 예외처리)
      * 1이나 2가 아닌 입력은 `잘못 입력하셨습니다.` 출력

### 커밋 메시지 컨벤션
본 프로젝트는 커밋 메시지를 다음과 같이 구분해 작성합니다. (참고 자료: [AngularJS Commit Message Conventions](https://gist.github.com/stephenparish/9941e89d80e2bc58a153))
```
feat (feature)
fix (bug fix)
docs (documentation)
style (formatting, missing semi colons, …)
refactor
test (when adding missing tests)
chore (maintain)
```

---

(이하 튜토리얼 문서)

## [NEXTSTEP 플레이그라운드의 미션 진행 과정](https://github.com/next-step/nextstep-docs/blob/master/playground/README.md)

---
## 학습 효과를 높이기 위해 추천하는 미션 진행 방법

---
1. 피드백 강의 전까지 미션 진행 
> 피드백 강의 전까지 혼자 힘으로 미션 진행. 미션을 진행하면서 하나의 작업이 끝날 때 마다 add, commit
> 예를 들어 다음 숫자 야구 게임의 경우 0, 1, 2단계까지 구현을 완료한 후 push

![mission baseball](https://raw.githubusercontent.com/next-step/nextstep-docs/master/playground/images/mission_baseball.png)

---
2. 피드백 앞 단계까지 미션 구현을 완료한 후 피드백 강의를 학습한다.

---
3. Git 브랜치를 master 또는 main으로 변경한 후 피드백을 반영하기 위한 새로운 브랜치를 생성한 후 처음부터 다시 미션 구현을 도전한다.

```
git branch -a // 모든 로컬 브랜치 확인
git checkout master // 기본 브랜치가 master인 경우
git checkout main // 기본 브랜치가 main인 경우

git checkout -b 브랜치이름
ex) git checkout -b apply-feedback
```
