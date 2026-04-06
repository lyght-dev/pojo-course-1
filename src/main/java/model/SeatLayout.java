package model;

import java.util.List;

import vo.SeatNumber;
import vo.SeatNumbers;

public final class SeatLayout {
    private final Seats seats;

    public SeatLayout(Seats seats) {
        if (seats == null) {
            throw new IllegalArgumentException("좌석 배치는 필수입니다.");
        }
        this.seats = seats;
    }

    public int totalCount() {
        return seats.count();
    }

    public int remainingCount() {
        return (int) seats.values().stream()
                .filter(seat -> !seat.isReserved())
                .count();
    }

    public Seats availableSeats() {
        return new Seats(
                seats.values().stream()
                        .filter(seat -> !seat.isReserved())
                        .toList()
        );
    }

    public Seats reserve(SeatNumbers seatNumbers) {
        List<Seat> selectedSeats = findSeats(seatNumbers);
        selectedSeats.forEach(Seat::reserve);
        return new Seats(selectedSeats);
    }

    public void release(SeatNumbers seatNumbers) {
        findSeats(seatNumbers).forEach(Seat::release);
    }

    private List<Seat> findSeats(SeatNumbers seatNumbers) {
        return seatNumbers.values().stream()
                .map(this::findSeat)
                .toList();
    }

    private Seat findSeat(SeatNumber seatNumber) {
        return seats.values().stream()
                .filter(seat -> seat.hasNumber(seatNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 좌석입니다."));
    }
}
