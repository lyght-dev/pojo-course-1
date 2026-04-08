package vo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

class ValueObjectTest {
    @Test
    void 중복된_좌석번호로_좌석목록을_생성하면_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class, () -> new SeatNumbers(List.of(
                new SeatNumber("A"),
                new SeatNumber("A")
        )));
    }

    @Test
    void 올바르지_않은_휴대폰번호로_생성하면_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("01012345678"));
    }

    @Test
    void 올바른_좌석목록으로_생성하면_개수를_반환한다() {
        SeatNumbers seatNumbers = new SeatNumbers(List.of(
                new SeatNumber("A"),
                new SeatNumber("C")
        ));

        assertEquals(2, seatNumbers.count());
    }
}
