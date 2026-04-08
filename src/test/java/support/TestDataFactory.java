package support;

import java.util.List;

import application.BookingService;
import application.ScreeningCatalog;
import model.Movie;
import model.Screening;
import model.Seat;
import model.SeatLayout;
import model.Seats;
import model.Tickets;
import utils.Sequence;
import vo.Minutes;
import vo.SeatNumber;

public final class TestDataFactory {
    private TestDataFactory() {
    }

    public static BookingService createBookingService() {
        return new BookingService(createScreeningCatalog(), new Tickets(), new Sequence());
    }

    public static ScreeningCatalog createScreeningCatalog() {
        return new ScreeningCatalog(List.of(
                createScreening(1L, "인셉션", 148),
                createScreening(2L, "인터스텔라", 169)
        ));
    }

    private static Screening createScreening(long screeningId, String title, int runningTime) {
        return new Screening(
                screeningId,
                new Movie(title, new Minutes(runningTime)),
                new SeatLayout(createDefaultSeats())
        );
    }

    private static Seats createDefaultSeats() {
        return new Seats(List.of(
                createSeat("A"),
                createSeat("B"),
                createSeat("C"),
                createSeat("D"),
                createSeat("E"),
                createSeat("F")
        ));
    }

    private static Seat createSeat(String seatNumber) {
        return new Seat(new SeatNumber(seatNumber));
    }
}
