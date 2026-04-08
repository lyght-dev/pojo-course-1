package controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;
import java.io.StringWriter;

import org.junit.jupiter.api.Test;

import application.BookingService;
import support.TestDataFactory;
import utils.Console;
import view.InputView;
import view.OutputView;

class MainControllerTest {
    @Test
    void 메뉴를_순서대로_입력하면_예매조회취소흐름을_수행한다() {
        StringWriter output = new StringWriter();
        MainController controller = createController(
                String.join("\n",
                        "1",
                        "2",
                        "010-1234-5678",
                        "1",
                        "A,C",
                        "3",
                        "010-1234-5678",
                        "4",
                        "010-1234-5678",
                        "1",
                        "3",
                        "010-1234-5678",
                        "5"
                ) + "\n",
                output
        );

        controller.run();

        String consoleOutput = output.toString();

        assertTrue(consoleOutput.contains("[상영 목록]"));
        assertTrue(consoleOutput.contains("상영번호: 1, 영화제목: 인셉션, 전체좌석: 6, 남은좌석: 6, 예매가능좌석: [A,B,C,D,E,F]"));
        assertTrue(consoleOutput.contains("예매가 완료되었습니다. 예매 번호: 1"));
        assertTrue(consoleOutput.contains("예매번호: 1, 영화제목: 인셉션, 예매좌석: A,C, 예매상태: 예약완료"));
        assertTrue(consoleOutput.contains("예매가 취소되었습니다."));
        assertTrue(consoleOutput.contains("예매번호: 1, 영화제목: 인셉션, 예매좌석: A,C, 예매상태: 취소됨"));
        assertTrue(consoleOutput.contains("프로그램을 종료합니다."));
    }

    private MainController createController(String input, StringWriter output) {
        Console console = new Console(new StringReader(input), output);
        BookingService bookingService = TestDataFactory.createBookingService();
        return new MainController(
                bookingService,
                new InputView(console),
                new OutputView(console)
        );
    }
}
