package application.dto;

import model.Ticket;

public record ReservationResultViewModel(long ticketId) {
    public static ReservationResultViewModel from(Ticket ticket) {
        return new ReservationResultViewModel(ticket.ticketId());
    }
}
