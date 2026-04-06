package model;

import java.util.List;

import vo.SeatNumbers;

public record Seats(List<Seat> values) {
    public Seats {
        if (values == null) {
            throw new IllegalArgumentException("좌석 목록은 필수입니다.");
        }
        values = List.copyOf(values);
    }

    public int count() {
        return values.size();
    }

    public SeatNumbers toSeatNumbers() {
        return new SeatNumbers(
                values.stream()
                        .map(Seat::number)
                        .toList()
        );
    }
}
