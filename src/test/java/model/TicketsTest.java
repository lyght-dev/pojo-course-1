package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import vo.Minutes;
import vo.PhoneNumber;
import vo.SeatNumber;

class TicketsTest {
    @Test
    void 휴대폰번호로_조회하면_해당_예매목록을_반환한다() {
        Tickets tickets = new Tickets();
        PhoneNumber phoneNumber = new PhoneNumber("010-1234-5678");
        tickets.add(createTicket(1L, phoneNumber, "A"));
        tickets.add(createTicket(2L, phoneNumber, "B"));
        tickets.add(createTicket(3L, new PhoneNumber("010-0000-0000"), "C"));

        List<Ticket> foundTickets = tickets.findByPhoneNumber(phoneNumber);

        assertEquals(2, foundTickets.size());
    }

    @Test
    void 존재하지_않는_예매번호로_조회하면_예외가_발생한다() {
        Tickets tickets = new Tickets();

        assertThrows(IllegalArgumentException.class, () -> tickets.findByTicketId(1L));
    }

    private Ticket createTicket(long ticketId, PhoneNumber phoneNumber, String seatNumber) {
        Screening screening = new Screening(
                ticketId,
                new Movie("인셉션", new Minutes(148)),
                new SeatLayout(new Seats(List.of(
                        new Seat(new SeatNumber("A")),
                        new Seat(new SeatNumber("B")),
                        new Seat(new SeatNumber("C"))
                )))
        );
        return new Ticket(
                ticketId,
                phoneNumber,
                screening,
                screening.reserve(new vo.SeatNumbers(List.of(new SeatNumber(seatNumber))))
        );
    }
}
