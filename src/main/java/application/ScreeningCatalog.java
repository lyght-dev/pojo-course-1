package application;

import java.util.List;

import model.Screening;

public final class ScreeningCatalog {
    private final List<Screening> screenings;

    public ScreeningCatalog(List<Screening> screenings) {
        if (screenings == null || screenings.isEmpty()) {
            throw new IllegalArgumentException("상영 목록은 1개 이상이어야 합니다.");
        }
        this.screenings = List.copyOf(screenings);
    }

    public List<Screening> findAll() {
        return screenings;
    }

    public Screening findById(long screeningId) {
        return screenings.stream()
                .filter(screening -> screening.hasSecreeningId(screeningId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상영입니다."));
    }
}
