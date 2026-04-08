package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import application.dto.ReservationResultViewModel;
import application.dto.ScreeningViewModel;
import application.dto.TicketViewModel;
import support.TestDataFactory;
import vo.PhoneNumber;
import vo.SeatNumber;
import vo.SeatNumbers;

class BookingServiceTest {
    @Test
    void 예매하면_예매정보를_생성하고_남은좌석수를_줄인다() {
        BookingService bookingService = TestDataFactory.createBookingService();

        ReservationResultViewModel reservation = bookingService.reserve(
                new PhoneNumber("010-1234-5678"),
                1L,
                new SeatNumbers(List.of(new SeatNumber("A"), new SeatNumber("C")))
        );

        List<TicketViewModel> tickets = bookingService.findTicketsByPhone(new PhoneNumber("010-1234-5678"));
        ScreeningViewModel screening = bookingService.listScreenings().get(0);

        assertEquals(1L, reservation.ticketId());
        assertEquals(1, tickets.size());
        assertEquals("인셉션", tickets.get(0).movieTitle());
        assertEquals("A,C", tickets.get(0).seatNumbers());
        assertEquals("예약완료", tickets.get(0).statusLabel());
        assertEquals(4, screening.remainingSeatCount());
        assertEquals("B,D,E,F", screening.availableSeatNumbers());
    }

    @Test
    void 예매를_취소하면_예매상태를_변경하고_좌석을_반환한다() {
        BookingService bookingService = TestDataFactory.createBookingService();
        PhoneNumber phoneNumber = new PhoneNumber("010-1234-5678");

        bookingService.reserve(
                phoneNumber,
                1L,
                new SeatNumbers(List.of(new SeatNumber("A"), new SeatNumber("C")))
        );
        bookingService.cancel(phoneNumber, 1L);

        TicketViewModel ticket = bookingService.findTicketsByPhone(phoneNumber).get(0);
        ScreeningViewModel screening = bookingService.listScreenings().get(0);

        assertEquals("취소됨", ticket.statusLabel());
        assertEquals(6, screening.remainingSeatCount());
        assertEquals("A,B,C,D,E,F", screening.availableSeatNumbers());
    }

    @Test
    void 이미_예매된_좌석으로_예매하면_예외가_발생한다() {
        BookingService bookingService = TestDataFactory.createBookingService();
        PhoneNumber phoneNumber = new PhoneNumber("010-1234-5678");

        bookingService.reserve(
                phoneNumber,
                1L,
                new SeatNumbers(List.of(new SeatNumber("A")))
        );

        assertThrows(IllegalArgumentException.class, () -> bookingService.reserve(
                phoneNumber,
                1L,
                new SeatNumbers(List.of(new SeatNumber("A")))
        ));
    }

    @Test
    void 존재하지_않는_예매를_취소하면_예외가_발생한다() {
        BookingService bookingService = TestDataFactory.createBookingService();

        assertThrows(IllegalArgumentException.class, () -> bookingService.cancel(
                new PhoneNumber("010-1234-5678"),
                999L
        ));
    }
}
