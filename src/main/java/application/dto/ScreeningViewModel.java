package application.dto;

import static java.util.stream.Collectors.joining;

import model.Screening;
import vo.SeatNumber;

public record ScreeningViewModel(
        long screeningId,
        String movieTitle,
        int totalSeatCount,
        int remainingSeatCount,
        String availableSeatNumbers
) {
    public static ScreeningViewModel from(Screening screening) {
        return new ScreeningViewModel(
                screening.screeningId(),
                screening.movie().title(),
                screening.totalSeatCount(),
                screening.remainingSeatCount(),
                screening.availableSeats().toSeatNumbers().values().stream()
                        .map(SeatNumber::value)
                        .collect(joining(","))
        );
    }
}
