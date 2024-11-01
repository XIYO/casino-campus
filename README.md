# 포커

## 요구사항

포커 게임을 수회 반복하고 승패를 판단하는 프로그램을 작성한다.

## 프로그램의 구성

클래스의 구성은 휴머니즘을 최대한 반영하여 구성한다.

### 공통 클래스

#### Card

게임을 진행을 위해 필요한 데이터 클래스

[![](https://mermaid.ink/img/pako:eNqNU9FumzAU_RXLT8lEIwKFBFRVoglrWBeyAummiRcP3AQt2BEYqVmUfvuM6WITbeosZPmce2zfe_A9wozmGLowJdkO1fW8QJsKlSkBfORFhTNWUAKSu5R0nFCBGapycOyYdizR_jVmVUE2moi9gozPM5RtsRTFTcFAzSdJRYj8BBWfJNVuH5ylmpQMpWaDWUBqhkiG_y4Vp0h9Rss9qnBCByJxOgQFUbJgtMt9MATdondTe0Eb6SXO-fY2zkfn7E99j0Riikc3N5g0Ja5Q6-jtrVLx5_VdLOE88JarcK4wC9-LEgXHX7y5r2KRNKgP5Q-66_s96MUuq_hH4ReFCFv_p5Dk60oBi8j3Jfy4WkcKCp6UWBx8U4D_5IcS-sH9IpEwDEJlY6IqP3mzB4ke174afAjCe4m8mf-ue-L3XrgX9V7qe-6Jt_bh6kox_Ux1J6UEapA7WaIi5z0oPE4h2-ISp9Dlyxw_o2bHUt6eJy5FDb_zQDLosqrBGqxos9lC9xntao6afY4YfuvgM7tH5Dul5Z8tOC8YrZZd04veFxLoHuELdI1rY2SYtmlPJrqum_zT4AG6jj2ypta17ox12zF1wzlp8Jc4dDwaTy3LNiaGMzUta6ybp9__fzhc?type=png)](https://mermaid.live/edit#pako:eNqNU9FumzAU_RXLT8lEIwKFBFRVoglrWBeyAummiRcP3AQt2BEYqVmUfvuM6WITbeosZPmce2zfe_A9wozmGLowJdkO1fW8QJsKlSkBfORFhTNWUAKSu5R0nFCBGapycOyYdizR_jVmVUE2moi9gozPM5RtsRTFTcFAzSdJRYj8BBWfJNVuH5ylmpQMpWaDWUBqhkiG_y4Vp0h9Rss9qnBCByJxOgQFUbJgtMt9MATdondTe0Eb6SXO-fY2zkfn7E99j0Riikc3N5g0Ja5Q6-jtrVLx5_VdLOE88JarcK4wC9-LEgXHX7y5r2KRNKgP5Q-66_s96MUuq_hH4ReFCFv_p5Dk60oBi8j3Jfy4WkcKCp6UWBx8U4D_5IcS-sH9IpEwDEJlY6IqP3mzB4ke174afAjCe4m8mf-ue-L3XrgX9V7qe-6Jt_bh6kox_Ux1J6UEapA7WaIi5z0oPE4h2-ISp9Dlyxw_o2bHUt6eJy5FDb_zQDLosqrBGqxos9lC9xntao6afY4YfuvgM7tH5Dul5Z8tOC8YrZZd04veFxLoHuELdI1rY2SYtmlPJrqum_zT4AG6jj2ypta17ox12zF1wzlp8Jc4dDwaTy3LNiaGMzUta6ybp9__fzhc)

#### Hand

[![](https://mermaid.ink/img/pako:eNp9VG1v2jAQ_iuWP6UboPJSCKhCohQWVNpUSdi0LRNykwOsBhs5TruuKr99tpO0KX35ZN8999ydz3f3iCMeAx7gKCFpek7JWpBtyGIqIJKUM3QWhMxgaMy3OyLITQL7YI8eQ3Z6OmMSxIpEMByGDKGvKDI2EHArOEKUyZA9lXSHsFiztBlVNCK5sI7QrLjux0TE-xxegwwoaFQfuU5yXwrK1kqZX3I1iWNLM4_QDecJEJar-Q6YstQxi8QUph3ecRofpqqtimwRqiOaBvf8mlBtXnGqAZfB-4BKidD1Rh4iEc-YLCjPAeCOJBmRUElH-5jyTLir0QVl8dsAwUYAfIiW4adJlm4O4RSkR9itrpJ1LSCmkYqdl_t1Ah5_IMm7LlRyH6izJHF4loJ18AG6pkqnhT3yuZAQX5KdiVpDZ7nlHkVKTHNfuY0PMs8MSfXz31WdoMA1Wz-jhnTTrUHskdCv0gXOLXSvGNoLwc-orBBSJVYIhyFvaXQLIq30rGnOTxv-vU4OijZecTEh0cYac5ZmW9BAtQHTXVJh-y-ScfCcg3mWCQ5MeyF6LMvwupNUW1j5SFTnxSBpMUK__-TKYqpyAU0X8_nScRf-pFD4gTeafXOCQgwcbzJZutPlaHkxuzovWe7Ce6N0rybL69HMK23mC985cLqsKh2lWY5HXskPfrhVvuf-HM1LgqpEyMz2QI3GEB3soVdQ9b8KIMTNEKMv9fpQXXV3IC2boobMHJ951bTSGNewKv-W0FitS7PIlMMNbCHEA3WNYUWyRIZYZaxMSaY21gOL8ECKDGpY8Gy9wYMVSVIlZbtYDWGxbkuTHWG_OH8W1aSqZrgs1rM-jAkePOK_eGB3Gk273-u1mu1eq921-zX8gAf1bq_T6J30-93eid1ttbrNpxr-Z5y2GsfHdrtjd5p2-8S2--0yi4mJU6T29B9SePmB?type=png)](https://mermaid.live/edit#pako:eNp9VG1v2jAQ_iuWP6UboPJSCKhCohQWVNpUSdi0LRNykwOsBhs5TruuKr99tpO0KX35ZN8999ydz3f3iCMeAx7gKCFpek7JWpBtyGIqIJKUM3QWhMxgaMy3OyLITQL7YI8eQ3Z6OmMSxIpEMByGDKGvKDI2EHArOEKUyZA9lXSHsFiztBlVNCK5sI7QrLjux0TE-xxegwwoaFQfuU5yXwrK1kqZX3I1iWNLM4_QDecJEJar-Q6YstQxi8QUph3ecRofpqqtimwRqiOaBvf8mlBtXnGqAZfB-4BKidD1Rh4iEc-YLCjPAeCOJBmRUElH-5jyTLir0QVl8dsAwUYAfIiW4adJlm4O4RSkR9itrpJ1LSCmkYqdl_t1Ah5_IMm7LlRyH6izJHF4loJ18AG6pkqnhT3yuZAQX5KdiVpDZ7nlHkVKTHNfuY0PMs8MSfXz31WdoMA1Wz-jhnTTrUHskdCv0gXOLXSvGNoLwc-orBBSJVYIhyFvaXQLIq30rGnOTxv-vU4OijZecTEh0cYac5ZmW9BAtQHTXVJh-y-ScfCcg3mWCQ5MeyF6LMvwupNUW1j5SFTnxSBpMUK__-TKYqpyAU0X8_nScRf-pFD4gTeafXOCQgwcbzJZutPlaHkxuzovWe7Ce6N0rybL69HMK23mC985cLqsKh2lWY5HXskPfrhVvuf-HM1LgqpEyMz2QI3GEB3soVdQ9b8KIMTNEKMv9fpQXXV3IC2boobMHJ951bTSGNewKv-W0FitS7PIlMMNbCHEA3WNYUWyRIZYZaxMSaY21gOL8ECKDGpY8Gy9wYMVSVIlZbtYDWGxbkuTHWG_OH8W1aSqZrgs1rM-jAkePOK_eGB3Gk273-u1mu1eq921-zX8gAf1bq_T6J30-93eid1ttbrNpxr-Z5y2GsfHdrtjd5p2-8S2--0yi4mJU6T29B9SePmB)

카드 인스턴스를 관리하는 클래스 입니다.

- `open()` 메서드가 실행되면 패의 티어를 계산하며
- `Comparable` 상속받아 패끼리 비교 하여 게임의 승자 판정을 빠르게 할 수있도록 하였습니다.
- 


### 주요 클래스

#### 딜러

- 게임을 주최하고 플레이어를 관리한다.
- 게임을 시작하고 종료한다.
- 게임의 결과를 판정한다.

#### 플레이어

- 카드를 받아 게임을 진행한다.
- 게임 전적이 있다.
- 상금을 가지고 있다.

### 보조 클래스

#### 덱

- 덱은 52장의 카드를 가지고 있다.
- 카드를 섞을 수 있다.
- 덱은 딜러만 접근 가능하다. (공정성)
- 딜러만 카드를 뽑을 수 있다. (공정성)

#### 핸드

- 카드 5장을 가지고 있다.
- 카드의 조합을 판정할 수 있다.
- 카드의 조합을 비교할 수 있다.

## 포커 게임

### 게임 규칙

1. 공식 포커 룰 준수
  - [포커 룰](https://ko.wikipedia.org/wiki/%ED%8F%AC%EC%BB%A4)
  - 문양의 우선 순위 없음: 공식적인 룰에서는 문양에 우선순위가 없다. 비공식적인 룰에서는 문양 우선순위가 존재할 수 있다.
  - 카드 숫자의 우선순위: 에이스(A)가 가장 높고, 2가 가장 낮다.
  - 핸드 구성: 각 플레이어는 5장의 카드를 가진다.
  - 무승부시에는 아무도 상금을 받지 못한다.

### 카드의 조합의 순위

높은 등급 부터 낮은 순으로 나열

#### 1. 로열 플러시 (Royal Flush)

- 구성: A, K, Q, J, 10이 같은 문양으로 이루어진 경우
- 예시: ♠️A ♠️K ♠️Q ♠️J ♠️10, ♥️A ♥️K ♥️Q ♥️J ♥️10
- 승부 규칙:
  - 동일한 등급이 있을 경우 무승부로 처리한다.

#### 2. 스트레이트 플러시 (Straight Flush)

- 구성: 연속된 숫자가 같은 문양으로 이루어진 경우
- 예시: ♣️9 ♣️8 ♣️7 ♣️6 ♣️5, ♦️6 ♦️5 ♦️4 ♦️3 ♦️2
- 승부 규칙:
  - 높은 숫자를 가진 쪽이 승리한다.
  - 높은 숫자도 같을 경우 무승부로 처리한다.

#### 3. 포 오브 어 카인드 (Four of a Kind)

- 구성: 같은 숫자가 4장 있는 경우
- 예시: ♠️Q ♣️Q ♦️Q ♥️Q ♠️7, ♠️8 ♣️8 ♦️8 ♥️8 ♦️J
- 승부 규칙:
  - 포카드를 이루는 숫자가 높은 쪽이 승리한다.
  - 포카드 숫자도 같을 경우 키커 카드(나머지 한 장)가 높은 쪽이 승리한다.
  - 키커 카드도 같을 경우 무승부로 처리한다.

#### 4. 풀 하우스 (Full House)

- 구성: 같은 숫자 3장 + 다른 숫자 2장 (트리플 + 페어)
- 예시: ♠️K ♣️K ♦️K ♥️4 ♠️4, ♠️J ♣️J ♦️J ♥️9 ♦️9
- 승부 규칙:
  - 쓰리카드(3장)의 숫자가 높은 쪽이 승리한다.
  - 쓰리카드도 같을 경우, 페어(2장)의 숫자가 높은 쪽이 승리한다.
  - 두 부분이 모두 같을 경우 무승부로 처리한다.

#### 5. 플러시 (Flush)

- 구성: 같은 문양의 카드 5장
- 예시: ♥️2 ♥️5 ♥️7 ♥️9 ♥️K, ♠️3 ♠️6 ♠️8 ♠️J ♠️A
- 승부 규칙:
  - 가장 높은 숫자를 가진 쪽이 승리한다.
  - 모든 숫자가 같을 경우 무승부로 처리한다.

#### 6. 스트레이트 (Straight)

- 구성: 연속된 숫자의 카드 5장 (문양 상관없음)
- 예시: ♠️5 ♥️4 ♦️3 ♣️2 ♥️A, ♠️10 ♦️9 ♣️8 ♥️7 ♦️6
- 승부 규칙:
  - 가장 높은 숫자를 가진 쪽이 승리한다.
  - 높은 숫자가 같을 경우 무승부로 처리한다.

#### 7. 쓰리 오브 어 카인드 (Three of a Kind)

- 구성: 같은 숫자가 3장 있는 경우
- 예시: ♠️9 ♥️9 ♦️9 ♣️K ♥️5, ♠️4 ♦️4 ♥️4 ♠️8 ♦️J
- 승부 규칙:
  - 트리플을 이루는 숫자가 높은 쪽이 승리한다.
  - 트리플 숫자가 같을 경우 키커 카드가 높은 쪽이 승리한다.
  - 키커 카드도 같을 경우 무승부로 처리한다.

#### 8. 투 페어 (Two Pair)

- 구성: 같은 숫자의 페어가 2개, 총 4장
- 예시: ♠️Q ♦️Q ♣️7 ♥️7 ♠️A, ♠️6 ♦️6 ♣️3 ♥️3 ♦️K
- 승부 규칙:
  - 높은 페어를 가진 쪽이 승리한다.
  - 높은 페어도 같을 경우, 낮은 페어를 비교해 높은 쪽이 승리한다.
  - 두 페어도 같을 경우 키커 카드가 높은 쪽이 승리한다.
  - 모든 경우가 같다면 무승부로 처리한다.

#### 9. 원 페어 (One Pair)

- 구성: 같은 숫자가 2장 있는 경우
- 예시: ♠️A ♦️A ♣️8 ♥️5 ♠️2, ♠️10 ♦️10 ♣️4 ♥️7 ♦️3
- 승부 규칙:
  - 페어를 이루는 숫자가 높은 쪽이 승리한다.
  - 페어 숫자가 같을 경우, 나머지 키커 카드 중 가장 높은 쪽이 승리한다.
  - 키커 카드도 같을 경우 무승부로 처리한다.




## OOP 과제 안내
### [과제 개요]
- **과제 명** : oop 과제1 - 카드게임을 객체지향적으로 설계하고 코드로 작성하시오
- **상세 내용 :** [과제 RFP 노션 링크](https://www.notion.so/Java-OOP-e2410603e2b6444aa322804e03662b91?pvs=4)
- **수행 및 결과물 제출 기한** : 10/25 (금) 15:00 ~ 10/31 (목) 23:59
### [과제 진행 및 제출 방법]
- 본 패스트캠퍼스 Github의 Repository를 본인의 Github Repository로 Fork합니다.
    - 패스트캠퍼스 깃헙은 Private 형태 (Public 불가)
    - 수강생 과제 간 브랜치 병합 문제를 최소화하고자 브랜치가 아닌 "Fork" 형태로 진행
- 개별 레포의 최종 branch → 패스트캠퍼스 업스트림 Repository의 main branch의 **PR 상태**로 제출합니다.
    - **PR TITLE : 수강생 성명 최종 제출 (EX. 김패캠 최종 제출)**
    - Pull Request 링크를 LMS로도 제출해 주셔야 최종 제출 완료 됩니다. (제출자: 수강생 본인 , 제출 기한: ~ 10/31 (목) 23:59시)
    - LMS를 통한 과제 미제출 시 점수가 부여되지 않습니다.
- PR 제출 시 유의사항
    - 멘토님들께서 어플리케이션 실행을 위해 확인해야 할 환경설정 값 등은 반드시 PR 부가 설명란 혹은 README.md에 작성 부탁 드립니다.
    - **Pull Request에서 제출 후 절대 병합(Merge)하지 않도록 주의하세요!**
    - 수행 및 제출 과정에서 문제가 발생한 경우, 바로 질의응답 멘토님이나 강사님에서 얘기하세요! (강사님께서 필요시 개별 힌트 제공)
