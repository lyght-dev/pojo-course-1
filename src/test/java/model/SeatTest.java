package model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import vo.SeatNumber;

class SeatTest {
    @Test
    void 좌석을_예매하면_예매상태가_된다() {
        Seat seat = new Seat(new SeatNumber("A"));

        seat.reserve();

        assertTrue(seat.isReserved());
    }

    @Test
    void 이미_예매된_좌석을_다시_예매하면_예외가_발생한다() {
        Seat seat = new Seat(new SeatNumber("A"));
        seat.reserve();

        assertThrows(IllegalArgumentException.class, seat::reserve);
    }

    @Test
    void 예매된_좌석을_취소하면_예매가능상태가_된다() {
        Seat seat = new Seat(new SeatNumber("A"));
        seat.reserve();

        seat.release();

        assertFalse(seat.isReserved());
    }
}
