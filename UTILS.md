# 제공 유틸 인터페이스

과제 구현 편의를 위해 아래 인터페이스가 제공된다.

## Console

```java
public interface Console {
    String read();
    String readLine();
    void write(String value);
    void writeLine(String value);
}
```

* `read()`는 공백을 기준으로 다음 입력 토큰 하나를 읽어 문자열로 반환한다.
* `readLine()`은 한 줄 전체 입력을 읽어 문자열로 반환한다.
* `write(String value)`는 줄바꿈 없이 문자열을 출력한다.
* `writeLine(String value)`는 줄바꿈과 함께 문자열을 출력한다.

## Random

```java
public interface Random {
    int next(int start, int end);
    String alphaNum(int length);
}
```

* `next(int start, int end)`는 시작값과 종료값을 모두 포함하는 범위 내의 정수를 반환한다.
* `alphaNum(int length)`는 지정한 길이의 랜덤 영숫자 문자열을 반환한다.

## Sequence

```java
public interface Sequence {
    long next();
}
```

* `next()`는 다음 순차 값을 반환한다.
