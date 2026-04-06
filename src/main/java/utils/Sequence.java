package utils;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 순차적으로 증가하는 값을 제공하는 유틸 클래스이다.
 *
 * <p>이 클래스는 상태를 가지며 스레드 안전하다.</p>
 */
public final class Sequence {
    private final AtomicLong value;

    /**
     * 첫 번째 반환값이 {@code 1}인 시퀀스를 생성한다.
     */
    public Sequence() {
        this(1L);
    }

    /**
     * 첫 번째 반환값이 지정한 값과 같은 시퀀스를 생성한다.
     *
     * @param startValue {@link #next()}가 처음 반환할 값
     */
    public Sequence(long startValue) {
        this.value = new AtomicLong(startValue - 1L);
    }

    /**
     * 다음 순차 값을 반환한다.
     *
     * @return 다음 순차 숫자
     */
    public long next() {
        return value.incrementAndGet();
    }
}
