package application;

import java.util.List;

import application.dto.ReservationResultViewModel;
import application.dto.ScreeningViewModel;
import application.dto.TicketViewModel;
import model.Screening;
import model.Seats;
import model.Ticket;
import model.Tickets;
import utils.Sequence;
import vo.PhoneNumber;
import vo.SeatNumbers;
public final class BookingService {

    private final ScreeningCatalog screeningCatalog;
    private final Tickets tickets;
    private final Sequence ticketSequence;

    public BookingService(ScreeningCatalog screeningCatalog, Tickets tickets, Sequence ticketSequence) {
        if (screeningCatalog == null) {
            throw new IllegalArgumentException("상영 목록은 필수입니다.");
        }
        if (tickets == null) {
            throw new IllegalArgumentException("예매 목록은 필수입니다.");
        }
        if (ticketSequence == null) {
            throw new IllegalArgumentException("예매 번호 시퀀스는 필수입니다.");
        }
        this.screeningCatalog = screeningCatalog;
        this.tickets = tickets;
        this.ticketSequence = ticketSequence;
    }

    public List<ScreeningViewModel> listScreenings() {
        return screeningCatalog.findAll().stream()
                .map(ScreeningViewModel::from)
                .toList();
    }

    public ReservationResultViewModel reserve(PhoneNumber phoneNumber, long screeningId, SeatNumbers seatNumbers) {
        Screening screening = screeningCatalog.findById(screeningId);
        Seats reservedSeats = screening.reserve(seatNumbers);
        Ticket ticket = new Ticket(ticketSequence.next(), phoneNumber, screening, reservedSeats);
        tickets.add(ticket);
        return ReservationResultViewModel.from(ticket);
    }

    public List<TicketViewModel> findTicketsByPhone(PhoneNumber phoneNumber) {
        return tickets.findByPhoneNumber(phoneNumber).stream()
                .map(TicketViewModel::from)
                .toList();
    }

    public void cancel(PhoneNumber phoneNumber, long ticketId) {
        Ticket ticket = tickets.findByTicketId(ticketId);
        ticket.cancelBy(phoneNumber);
    }
}
