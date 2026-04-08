package model;

import vo.Minutes;

public record Movie(String title, Minutes runningTime) {
    public Movie {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("영화 제목은 필수입니다.");
        }
        if (runningTime == null) {
            throw new IllegalArgumentException("상영 시간은 필수입니다.");
        }
    }
}
