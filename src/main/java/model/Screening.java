package model;

import vo.SeatNumbers;

public final class Screening {
    private final long screeningId;
    private final Movie movie;
    private final SeatLayout seatLayout;

    public Screening(long screeningId, Movie movie, SeatLayout seatLayout) {
        if (screeningId <= 0) {
            throw new IllegalArgumentException("상영 식별자는 1 이상이어야 합니다.");
        }
        if (movie == null) {
            throw new IllegalArgumentException("영화는 필수입니다.");
        }
        if (seatLayout == null) {
            throw new IllegalArgumentException("좌석 배치는 필수입니다.");
        }
        this.screeningId = screeningId;
        this.movie = movie;
        this.seatLayout = seatLayout;
    }

    public long screeningId() {
        return screeningId;
    }

    public Movie movie() {
        return movie;
    }

    public boolean hasSecreeningId(long screeningId) {
        return this.screeningId == screeningId;
    }

    public int totalSeatCount() {
        return seatLayout.totalCount();
    }

    public int remainingSeatCount() {
        return seatLayout.remainingCount();
    }

    public Seats availableSeats() {
        return seatLayout.availableSeats();
    }

    public Seats reserve(SeatNumbers seatNumbers) {
        return seatLayout.reserve(seatNumbers);
    }

    public void release(SeatNumbers seatNumbers) {
        seatLayout.release(seatNumbers);
    }
}
