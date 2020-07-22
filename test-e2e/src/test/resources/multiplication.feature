# language: ko
기능: 사용자는 정답일 수도 있고 오답일 수도 있는 곱셈 답안을 제출할 수 있다.
  사용자가 정답을 제출하면 결과가 정답임을 나타내는 응답을 받는다.
  또한 점수를 받고 몇 가지 배지도 받을 수 있다.
  따라서 재접속하고 계속 플레이할 수 있도록 동기부여가 된다.
  배지는 첫 번째 정답을 맞출 때, 사용자가 100점, 500점, 999점을 획득할 때 각각 획득할 수 있다.
  사용자가 오답을 제출하면 점수와 배지 모두 얻을 수 없다.

  시나리오: 사용자가 첫 번째 정답 답안을 제출하고 배지를 얻는다
    만약 사용자 철수가 1개의 정답 답안을 제출한다
    그러면 사용자는 답안이 정답이라는 응답을 받는다
    그리고 사용자는 10점을 얻는다
    그리고 사용자는 FIRST_WON 배지를 얻는다

  시나리오: 사용자는 두 번째 정답 답안을 제출하고 포인트만 얻는다
    만약 사용자 철수가 1개의 정답 답안을 제출한다
    그리고 사용자는 FIRST_WON 배지를 얻는다
    만약 사용자 철수가 1개의 정답 답안을 제출한다
    그러면 사용자는 답안이 정답이라는 응답을 받는다
    그리고 사용자는 10점을 얻는다
    그리고 사용자는 배지를 얻지 못한다

  시나리오: 사용자는 오답 답안을 제출하고 아무것도 얻지 못한다
    만약 사용자 철수가 1개의 오답 답안을 제출한다
    그러면 사용자는 답안이 오답이라는 응답을 받는다
    그리고 사용자는 0점을 얻는다
    그리고 사용자는 배지를 얻지 못한다

  # 브론즈, 실버, 골드 배지 확인
  시나리오 개요: <이전_답안> 정답을 제출한 이후에 사용자가 1개의 정답을 제출하고 <배지_이름> 배지를 얻는다.
    만약 사용자 철수가 <이전_답안>개의 정답 답안을 제출한다
    만약 사용자 철수가 1개의 정답 답안을 제출한다
    그러면 사용자는 답안이 정답이라는 응답을 받는다
    그리고 사용자는 10점을 얻는다
    그리고 사용자는 <배지_이름> 배지를 얻는다

    예:
      | 이전_답안 | 배지_이름           |
      | 9       | BRONZE_MULTIPLICATION |
      | 49      | SILVER_MULTIPLICATION |
      | 99      | GOLD_MULTIPLICATION   |