package model;

import vo.PhoneNumber;

public final class Ticket {
    private final long ticketId;
    private final PhoneNumber phoneNumber;
    private final Screening screening;
    private final Seats seats;
    private TicketStatus status;

    public Ticket(long ticketId, PhoneNumber phoneNumber, Screening screening, Seats seats) {
        if (ticketId <= 0) {
            throw new IllegalArgumentException("예매 식별자는 1 이상이어야 합니다.");
        }
        if (phoneNumber == null) {
            throw new IllegalArgumentException("휴대폰 번호는 필수입니다.");
        }
        if (screening == null) {
            throw new IllegalArgumentException("상영 정보는 필수입니다.");
        }
        if (seats == null || seats.count() == 0) {
            throw new IllegalArgumentException("예매 좌석은 1개 이상이어야 합니다.");
        }
        this.ticketId = ticketId;
        this.phoneNumber = phoneNumber;
        this.screening = screening;
        this.seats = seats;
        this.status = TicketStatus.RESERVED;
    }

    public long ticketId() {
        return ticketId;
    }

    public PhoneNumber phoneNumber() {
        return phoneNumber;
    }

    public Screening screening() {
        return screening;
    }

    public Seats seats() {
        return seats;
    }

    public TicketStatus status() {
        return status;
    }

    public boolean hasTicketId(long ticketId) {
        return this.ticketId == ticketId;
    }

    public boolean hasPhoneNumber(PhoneNumber phoneNumber) {
        return this.phoneNumber.equals(phoneNumber);
    }

    public void cancelBy(PhoneNumber phoneNumber) {
        if (!hasPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("휴대폰 번호가 일치하지 않습니다.");
        }
        screening.release(seats.toSeatNumbers());
        status = status.cancel();
    }
}
