package vo;

public record Minutes(int value) {
    public Minutes {
        if (value <= 0) {
            throw new IllegalArgumentException("상영 시간은 0보다 커야 합니다.");
        }
    }
}
