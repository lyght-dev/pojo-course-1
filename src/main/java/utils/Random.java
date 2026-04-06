package utils;

import java.util.random.RandomGenerator;

/**
 * 랜덤 정수와 영숫자 문자열 생성을 제공하는 유틸 클래스이다.
 *
 * <p>이 클래스 자체는 불변이며, 스레드 안전성은 주입한
 * {@link java.util.random.RandomGenerator} 구현에 따라 달라진다.</p>
 */
public final class Random {
    private static final char[] ALPHANUMERIC_CHARACTERS =
            "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    private final RandomGenerator random;

    /**
     * JDK 기본 난수 생성기를 사용하는 유틸 객체를 생성한다.
     */
    public Random() {
        this(RandomGenerator.getDefault());
    }

    /**
     * 지정한 난수 생성기를 사용하는 유틸 객체를 생성한다.
     *
     * @param random 랜덤 값을 생성할 난수 생성기
     */
    public Random(RandomGenerator random) {
        this.random = random;
    }

    /**
     * 시작값과 종료값을 모두 포함하는 범위 안에서 랜덤 정수를 반환한다.
     *
     * @param start 반환 가능한 최소값
     * @param end 반환 가능한 최대값
     * @return {@code start} 이상 {@code end} 이하의 랜덤 정수
     * @throws IllegalArgumentException 시작값이 종료값보다 큰 경우
     */
    public int next(int start, int end) {
        validateRange(start, end);
        long bound = (long) end - start + 1L;
        return (int) (random.nextLong(bound) + start);
    }

    /**
     * 지정한 길이의 영숫자 랜덤 문자열을 반환한다.
     *
     * @param length 생성할 문자 개수
     * @return {@code 0-9}, {@code A-Z}, {@code a-z}로 구성된 랜덤 문자열
     * @throws IllegalArgumentException 길이가 음수인 경우
     */
    public String alphaNum(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("길이는 0 이상이어야 합니다.");
        }
        StringBuilder builder = new StringBuilder(length);
        for (int index = 0; index < length; index++) {
            builder.append(ALPHANUMERIC_CHARACTERS[random.nextInt(ALPHANUMERIC_CHARACTERS.length)]);
        }
        return builder.toString();
    }

    private void validateRange(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("시작값은 종료값보다 클 수 없습니다.");
        }
    }
}
