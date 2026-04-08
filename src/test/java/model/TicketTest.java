package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import vo.Minutes;
import vo.PhoneNumber;
import vo.SeatNumber;
import vo.SeatNumbers;

class TicketTest {
    @Test
    void 본인_번호로_예매를_취소하면_상태를_변경하고_좌석을_반환한다() {
        PhoneNumber phoneNumber = new PhoneNumber("010-1234-5678");
        Screening screening = createScreening();
        Seats reservedSeats = screening.reserve(createSeatNumbers("A", "C"));
        Ticket ticket = new Ticket(1L, phoneNumber, screening, reservedSeats);

        ticket.cancelBy(phoneNumber);

        assertEquals(TicketStatus.CANCELED, ticket.status());
        assertTrue(screening.availableSeats().values().stream()
                .anyMatch(seat -> seat.hasNumber(new SeatNumber("A"))));
        assertTrue(screening.availableSeats().values().stream()
                .anyMatch(seat -> seat.hasNumber(new SeatNumber("C"))));
    }

    @Test
    void 다른_번호로_예매를_취소하면_예외가_발생한다() {
        PhoneNumber phoneNumber = new PhoneNumber("010-1234-5678");
        Screening screening = createScreening();
        Ticket ticket = new Ticket(1L, phoneNumber, screening, screening.reserve(createSeatNumbers("A")));

        assertThrows(IllegalArgumentException.class, () -> ticket.cancelBy(new PhoneNumber("010-9999-9999")));
    }

    @Test
    void 이미_취소된_예매를_다시_취소하면_예외가_발생한다() {
        PhoneNumber phoneNumber = new PhoneNumber("010-1234-5678");
        Screening screening = createScreening();
        Ticket ticket = new Ticket(1L, phoneNumber, screening, screening.reserve(createSeatNumbers("A")));
        ticket.cancelBy(phoneNumber);

        assertThrows(IllegalArgumentException.class, () -> ticket.cancelBy(phoneNumber));
    }

    private Screening createScreening() {
        return new Screening(
                1L,
                new Movie("인셉션", new Minutes(148)),
                new SeatLayout(new Seats(List.of(
                        new Seat(new SeatNumber("A")),
                        new Seat(new SeatNumber("B")),
                        new Seat(new SeatNumber("C"))
                )))
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
