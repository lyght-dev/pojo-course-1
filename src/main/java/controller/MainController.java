package controller;

import java.util.Arrays;

import application.BookingService;
import view.InputView;
import view.OutputView;
import vo.PhoneNumber;
import vo.SeatNumber;
import vo.SeatNumbers;

public final class MainController {
    private final BookingService bookingService;
    private final InputView inputView;
    private final OutputView outputView;

    public MainController(BookingService bookingService, InputView inputView, OutputView outputView) {
        if (bookingService == null) {
            throw new IllegalArgumentException("예매 서비스는 필수입니다.");
        }
        if (inputView == null) {
            throw new IllegalArgumentException("입력 뷰는 필수입니다.");
        }
        if (outputView == null) {
            throw new IllegalArgumentException("출력 뷰는 필수입니다.");
        }
        this.bookingService = bookingService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        while (true) {
            outputView.printMenu();
            MenuAction action = MenuAction.from(inputView.readMenuSelection());
            if (action.isExit()) {
                outputView.printExitMessage();
                return;
            }
            execute(action);
        }
    }

    private void execute(MenuAction action) {
        switch (action) {
            case SHOW_SCREENINGS -> showScreenings();
            case RESERVE -> reserve();
            case SHOW_MY_TICKETS -> showMyTickets();
            case CANCEL_TICKET -> cancelTicket();
            default -> throw new IllegalArgumentException("지원하지 않는 메뉴입니다.");
        }
    }

    private void showScreenings() {
        outputView.printScreenings(bookingService.listScreenings());
    }

    private void reserve() {
        PhoneNumber phoneNumber = new PhoneNumber(inputView.readPhoneNumber());
        long screeningId = parseLong(inputView.readScreeningId(), "상영 번호는 숫자여야 합니다.");
        SeatNumbers seatNumbers = parseSeatNumbers(inputView.readSeatNumbers());
        outputView.printReservationResult(bookingService.reserve(phoneNumber, screeningId, seatNumbers));
    }

    private void showMyTickets() {
        PhoneNumber phoneNumber = new PhoneNumber(inputView.readPhoneNumber());
        outputView.printTickets(bookingService.findTicketsByPhone(phoneNumber));
    }

    private void cancelTicket() {
        PhoneNumber phoneNumber = new PhoneNumber(inputView.readPhoneNumber());
        long ticketId = parseLong(inputView.readTicketId(), "예매 번호는 숫자여야 합니다.");
        bookingService.cancel(phoneNumber, ticketId);
        outputView.printCancelSuccess();
    }

    private long parseLong(String input, String message) {
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(message);
        }
    }

    private SeatNumbers parseSeatNumbers(String input) {
        return new SeatNumbers(
                Arrays.stream(input.split(","))
                        .map(String::trim)
                        .map(SeatNumber::new)
                        .toList()
        );
    }
}
