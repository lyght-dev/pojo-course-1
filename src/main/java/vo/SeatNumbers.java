package vo;

import java.util.List;

public record SeatNumbers(List<SeatNumber> values) {
    public SeatNumbers {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("좌석 번호는 1개 이상이어야 합니다.");
        }
        List<SeatNumber> copiedValues = List.copyOf(values);

        if (hasDuplicate(copiedValues)) {
            throw new IllegalArgumentException("중복된 좌석 번호는 선택할 수 없습니다.");
        }

        values = copiedValues;
    }

    public int count() {
        return values.size();
    }

    private boolean hasDuplicate(List<SeatNumber> values) {
        return values.stream().distinct().count() != values.size();
    }
}
