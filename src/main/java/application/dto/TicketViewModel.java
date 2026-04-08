package application.dto;

import static java.util.stream.Collectors.joining;

import model.Ticket;
import model.TicketStatus;
import vo.SeatNumber;

public record TicketViewModel(
        long ticketId,
        String movieTitle,
        String seatNumbers,
        String statusLabel
) {
    private static final String RESERVED_LABEL = "예약완료";
    private static final String CANCELED_LABEL = "취소됨";

    public static TicketViewModel from(Ticket ticket) {
        return new TicketViewModel(
                ticket.ticketId(),
                ticket.screening().movie().title(),
                ticket.seats().toSeatNumbers().values().stream()
                        .map(SeatNumber::value)
                        .collect(joining(",")),
                toStatusLabel(ticket.status())
        );
    }

    private static String toStatusLabel(TicketStatus ticketStatus) {
        if (ticketStatus.isCanceled()) {
            return CANCELED_LABEL;
        }
        return RESERVED_LABEL;
    }
}
