package config;

import java.util.List;

import application.BookingService;
import application.ScreeningCatalog;
import controller.MainController;
import model.Movie;
import model.Screening;
import model.Seat;
import model.SeatLayout;
import model.Seats;
import model.Tickets;
import utils.Console;
import utils.Sequence;
import view.InputView;
import view.OutputView;
import vo.Minutes;
import vo.SeatNumber;

public final class ApplicationFactory {
    public MainController createMainController() {
        Console console = new Console();
        return new MainController(
                new BookingService(createScreeningCatalog(), new Tickets(), new Sequence()),
                new InputView(console),
                new OutputView(console)
        );
    }

    private ScreeningCatalog createScreeningCatalog() {
        return new ScreeningCatalog(List.of(
                createScreening(1L, "인셉션", 148),
                createScreening(2L, "인터스텔라", 169)
        ));
    }

    private Screening createScreening(long screeningId, String title, int runningTime) {
        return new Screening(
                screeningId,
                new Movie(title, new Minutes(runningTime)),
                new SeatLayout(createDefaultSeats())
        );
    }

    private Seats createDefaultSeats() {
        return new Seats(List.of(
                createSeat("A"),
                createSeat("B"),
                createSeat("C"),
                createSeat("D"),
                createSeat("E"),
                createSeat("F")
        ));
    }

    private Seat createSeat(String seatNumber) {
        return new Seat(new SeatNumber(seatNumber));
    }
}
