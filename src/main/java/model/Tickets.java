package model;

import java.util.ArrayList;
import java.util.List;

import vo.PhoneNumber;

public final class Tickets {
    private final List<Ticket> values = new ArrayList<>();

    public void add(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("예매 정보는 필수입니다.");
        }
        values.add(ticket);
    }

    public List<Ticket> findByPhoneNumber(PhoneNumber phoneNumber) {
        return values.stream()
                .filter(ticket -> ticket.hasPhoneNumber(phoneNumber))
                .toList();
    }

    public Ticket findByTicketId(long ticketId) {
        return values.stream()
                .filter(ticket -> ticket.hasTicketId(ticketId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예매입니다."));
    }
}
