package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import vo.SeatNumber;
import vo.SeatNumbers;

class SeatLayoutTest {
    @Test
    void 좌석을_예매하면_예매한_좌석을_반환하고_남은좌석수를_줄인다() {
        SeatLayout seatLayout = new SeatLayout(createSeats("A", "B", "C"));

        Seats reservedSeats = seatLayout.reserve(createSeatNumbers("A", "C"));

        assertEquals(2, reservedSeats.count());
        assertEquals(1, seatLayout.remainingCount());
        assertEquals(List.of("B"), seatLayout.availableSeats().toSeatNumbers().values().stream()
                .map(SeatNumber::value)
                .toList());
    }

    @Test
    void 존재하지_않는_좌석을_예매하면_예외가_발생한다() {
        SeatLayout seatLayout = new SeatLayout(createSeats("A", "B", "C"));

        assertThrows(IllegalArgumentException.class, () -> seatLayout.reserve(createSeatNumbers("D")));
    }

    private Seats createSeats(String... seatNumbers) {
        return new Seats(
                Arrays.stream(seatNumbers)
                        .map(SeatNumber::new)
                        .map(Seat::new)
                        .toList()
        );
    }

    private SeatNumbers createSeatNumbers(String... seatNumbers) {
        return new SeatNumbers(
                Arrays.stream(seatNumbers)
                        .map(SeatNumber::new)
                        .toList()
        );
    }
}
