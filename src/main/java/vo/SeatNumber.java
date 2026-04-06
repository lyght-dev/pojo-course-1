package vo;

public record SeatNumber(String value) {
    public SeatNumber {
        if (value == null) {
            throw new IllegalArgumentException("좌석 번호는 필수입니다.");
        }
        if (!value.matches("^[A-F]$")) {
            throw new IllegalArgumentException("좌석 번호는 A부터 F까지만 가능합니다.");
        }
    }
}
