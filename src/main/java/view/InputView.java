package view;

import utils.Console;

public final class InputView {
    private final Console console;

    public InputView(Console console) {
        if (console == null) {
            throw new IllegalArgumentException("콘솔은 필수입니다.");
        }
        this.console = console;
    }

    public String readMenuSelection() {
        return prompt("원하는 기능을 선택해주세요 : ");
    }

    public String readPhoneNumber() {
        return prompt("휴대폰 번호를 입력해주세요 : ");
    }

    public String readScreeningId() {
        return prompt("상영 번호를 입력해주세요 : ");
    }

    public String readSeatNumbers() {
        return prompt("예매할 좌석을 입력해주세요 : ");
    }

    public String readTicketId() {
        return prompt("예매 번호를 입력해주세요 : ");
    }

    private String prompt(String message) {
        console.write(message);
        return console.readLine().trim();
    }
}
