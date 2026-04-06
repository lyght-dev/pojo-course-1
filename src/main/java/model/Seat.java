package model;

import vo.SeatNumber;

public final class Seat {
    private final SeatNumber number;
    private SeatStatus status;

    public Seat(SeatNumber number) {
        this(number, SeatStatus.AVAILABLE);
    }

    public Seat(SeatNumber number, SeatStatus status) {
        if (number == null) {
            throw new IllegalArgumentException("좌석 번호는 필수입니다.");
        }
        if (status == null) {
            throw new IllegalArgumentException("좌석 상태는 필수입니다.");
        }
        this.number = number;
        this.status = status;
    }

    public SeatNumber number() {
        return number;
    }

    public boolean isReserved() {
        return status.isReserved();
    }

    public boolean hasNumber(SeatNumber seatNumber) {
        return number.equals(seatNumber);
    }

    public void reserve() {
        status = status.reserve();
    }

    public void release() {
        status = status.release();
    }
}
