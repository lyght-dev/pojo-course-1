package view;

import java.util.List;

import application.dto.ReservationResultViewModel;
import application.dto.ScreeningViewModel;
import application.dto.TicketViewModel;
import utils.Console;

public final class OutputView {
    private final Console console;

    public OutputView(Console console) {
        if (console == null) {
            throw new IllegalArgumentException("콘솔은 필수입니다.");
        }
        this.console = console;
    }

    public void printMenu() {
        console.writeLine("1. 상영 목록 조회");
        console.writeLine("2. 예매");
        console.writeLine("3. 내 예매 조회");
        console.writeLine("4. 예매 취소");
        console.writeLine("5. 종료");
    }

    public void printScreenings(List<ScreeningViewModel> screenings) {
        console.writeLine("[상영 목록]");
        screenings.forEach(this::printScreening);
    }

    public void printReservationResult(ReservationResultViewModel result) {
        console.writeLine("예매가 완료되었습니다. 예매 번호: " + result.ticketId());
    }

    public void printTickets(List<TicketViewModel> tickets) {
        console.writeLine("[내 예매 조회]");
        tickets.forEach(this::printTicket);
    }

    public void printCancelSuccess() {
        console.writeLine("예매가 취소되었습니다.");
    }

    public void printExitMessage() {
        console.writeLine("프로그램을 종료합니다.");
    }

    private void printScreening(ScreeningViewModel screening) {
        console.writeLine(String.format(
                "상영번호: %d, 영화제목: %s, 전체좌석: %d, 남은좌석: %d, 예매가능좌석: [%s]",
                screening.screeningId(),
                screening.movieTitle(),
                screening.totalSeatCount(),
                screening.remainingSeatCount(),
                screening.availableSeatNumbers()
        ));
    }

    private void printTicket(TicketViewModel ticket) {
        console.writeLine(String.format(
                "예매번호: %d, 영화제목: %s, 예매좌석: %s, 예매상태: %s",
                ticket.ticketId(),
                ticket.movieTitle(),
                ticket.seatNumbers(),
                ticket.statusLabel()
        ));
    }
}
