# java-calculator-precourse

## 문자열 덧셈 계산기

입력한 문자열에서 **숫자를 추출하여 합산하는 계산기**를 구현한다.  
기본 구분자는 쉼표(`,`)와 콜론(`:`)이며,  
사용자는 `"//[구분자]\\n"` 형식으로 **커스텀 구분자**를 지정할 수 있다.  
잘못된 입력에 대해서는 `IllegalArgumentException`을 발생시키고 프로그램을 종료한다.

---

## 1. 기능 구현 목록

### 1. 입력
- [x] 콘솔에서 문자열을 입력받는다
  - `camp.nextstep.edu.missionutils.Console.readLine()`을 사용
  - 예: `"1,2:3"`


### 2. 입력 검증
- [x] null, 빈 문자열, 커스텀 구분자 헤더 형식을 검증한다 → `IllegalArgumentException`
- [x] null 입력 → 예외 발생
- [x] 빈 문자열(`""`) 입력 → 정상 처리
- [x] 커스텀 구분자 형식 오류 (예: `"//;\n"` 누락) → 예외 발생
- [ ] 비숫자/음수 검증은 문자열 파싱 단계에서 수행


### 3. 구분자 파싱
- [x] 입력값의 맨 앞에 `"//"`가 있고, 그 뒤에 `"\n"`이 있으면 커스텀 구분자로 인식한다
  - `"//;\n1;2;3"` → 구분자: `;` → 결과: `6`
- [x] 커스텀 구분자는 **여러 문자를 허용**한다
  - `"//***\n1***2***3"` → 결과: `6`
- [x] 커스텀 구분자에 공백 포함 시 → 예외 발생 `IllegalArgumentException`
- [x] 예약어(`//`, `\n`, `,`, `:`) 사용 불가 → 예외 발생 `IllegalArgumentException`
- [x] 커스텀 구분자 식별에 실패할 경우 → `IllegalArgumentException`


### 4. 문자열 파싱
- [x] 구분자(기본 또는 커스텀)를 기준으로 문자열을 분리한다
- [x] 분리된 각 요소가 숫자로만 이루어져 있는지 검사한다
  - `"a"`, `"--"`, `"-2"` 등 → `IllegalArgumentException`
- [x] 숫자로 변환 불가 시 → `IllegalArgumentException`
- [x] 정상 입력이면 문자열 배열을 반환한다
  - 입력: `"1,2:3"` → 출력: `["1", "2", "3"]`

### 5. 계산
- [x] 문자열 배열을 정수 배열로 변환한다
- [x] 모든 정수를 합산하여 결과를 반환한다
- [x] 음수 또는 비정상 입력 시 → `IllegalArgumentException`
  - 입력: `["1", "-2", "3"]` → 예외 발생
  - 입력: `["1", "a", "3"]` → 예외 발생


### 6. 출력
- [ ] 최종 합산 결과를 `"결과 : X"` 형식으로 출력한다
  - 예: `"결과 : 6"`

---

## 2. 예외 처리 정책

### 1. 입력 단계
| 조건 | 처리 방식                      | 메시지 |
|------|----------------------------|-------------|
| null 입력 | `IllegalArgumentException` | `"입력값이 존재하지 않습니다."` |
| 빈 문자열 | 정상 처리                      | - |
| 커스텀 구분자 형식 오류 | IllegalArgumentException | "커스텀 구분자 형식이 올바르지 않습니다." |


### 2. 검증 단계
| 조건 | 처리 방식 | 메시지 |
|------|-------------|-------------|
| 음수 입력 | `IllegalArgumentException` | `"음수는 입력할 수 없습니다."` |
| 비숫자 포함 | `IllegalArgumentException` | `"숫자와 구분자만 입력해주세요."` |


### 3. 구분자 파싱 단계
| 조건 | 처리 방식 | 메시지 |
|------|-------------|-------------|
| 커스텀 구분자 식별 실패 | `IllegalArgumentException` | `"커스텀 구분자 형식이 올바르지 않습니다."` |
| 공백 포함 | `IllegalArgumentException` | `"공백은 구분자로 사용할 수 없습니다."` |
| 예약어(`//`, `\n`, `,`, `:`) 포함 | `IllegalArgumentException` | `"예약어는 구분자로 사용할 수 없습니다."` |


### 4. 문자열 파싱 단계
| 조건 | 처리 방식 | 메시지 |
|------|-------------|-------------|
| 숫자 변환 실패 | `IllegalArgumentException` | `"숫자 형식이 올바르지 않습니다."` |
| 음수 존재 | `IllegalArgumentException` | `"음수는 입력할 수 없습니다."` |


### 5. 계산 단계
| 조건 | 처리 방식 | 메시지 |
|------|-------------|-------------|
| 파싱 결과가 비어 있음 | `IllegalArgumentException` | `"계산할 숫자가 없습니다."` |


---

## 3. 프로젝트 구조

```
src
└─ main/java/calculator
   ├─ Application.java                # 실행 진입점(main)
   │
   ├─ controller
   │   └─ CalculatorController.java   # 전체 흐름 제어
   │
   ├─ view
   │   ├─ InputView.java              # 사용자 입력
   │   └─ OutputView.java             # 결과 출력
   │
   ├─ model
   │   ├─ calculator                  # 문자열 파싱 및 합산 로직
   │   │   ├─ Calculator.java         # 계산 로직(덧셈)
   │   │   ├─ Result.java             # 계산 결과를 값 객체로 캡슐화
   │   │   └─ Token.java              # 파싱된 숫자 토큰을 캡슐화
   │   ├─ parser
   │   │   ├─ DelimiterParser.java    # 기본/커스텀 구분자 추출
   │   │   ├─ NumberParser.java       # 문자열 파싱, 숫자 배열로 변환
   │   │   └─ DelimiterPattern.java   # 커스텀 구분자 패턴 정의
   │   └─ validation                  
   │       └─ Validator.java          # 입력값 유효성 검사
   │
   └─ util
       ├─ Constants.java              # 기본 구분자, 정규식 패턴 등의 상수
       └─ ExceptionMessages.java      # 예외 메시지
```
