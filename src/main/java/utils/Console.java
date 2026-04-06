package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

/**
 * 토큰 단위 입력, 줄 단위 입력, 문자열 출력을 제공하는 콘솔 유틸 클래스이다.
 *
 * <p>메서드 호출 사이에 현재 입력 줄을 내부 버퍼에 유지하므로 상태를 가지며 스레드 안전하지 않다.</p>
 */
public final class Console {
    private final BufferedReader reader;
    private final PrintWriter writer;
    private String currentLine;
    private int currentIndex;

    /**
     * {@code System.in}과 {@code System.out}을 사용하는 콘솔을 생성한다.
     */
    public Console() {
        this(
            new InputStreamReader(System.in),
            new PrintWriter(System.out)
        );
    }

    /**
     * 지정한 입력 리더와 출력 라이터를 사용하는 콘솔을 생성한다.
     *
     * @param reader 문자 입력 소스
     * @param writer 문자 출력 대상
     */
    public Console(Reader reader, Writer writer) {
        this.reader = new BufferedReader(reader);
        this.writer = new PrintWriter(writer, true);
    }

    /**
     * 공백을 기준으로 다음 입력 토큰 하나를 읽는다.
     *
     * @return 다음 공백이 아닌 입력 토큰
     * @throws IllegalStateException 더 이상 읽을 입력이 없는 경우
     */
    public String read() {
        while (true) {
            ensureLineLoaded();
            currentIndex = skipWhitespace(currentLine, currentIndex);
            if (currentIndex < currentLine.length()) {
                return readToken();
            }
            clearBufferedLine();
        }
    }

    /**
     * 한 줄 전체 입력을 읽는다.
     *
     * <p>{@link #read()}가 현재 줄의 일부를 이미 소비했다면, 아직 읽지 않은 같은 줄의 나머지를
     * 반환한다.</p>
     *
     * @return 다음 전체 입력 줄 또는 현재 줄의 남은 문자열
     * @throws IllegalStateException 더 이상 읽을 입력이 없는 경우
     */
    public String readLine() {
        if (currentLine != null) {
            String remaining = currentLine.substring(currentIndex);
            clearBufferedLine();
            return remaining;
        }
        return readLineFromSource();
    }

    /**
     * 줄바꿈 없이 문자열을 출력한다.
     *
     * @param value 출력할 문자열
     */
    public void write(String value) {
        writer.print(value);
        writer.flush();
    }

    /**
     * 줄바꿈과 함께 문자열을 출력한다.
     *
     * @param value 출력할 문자열
     */
    public void writeLine(String value) {
        writer.println(value);
        writer.flush();
    }

    private void ensureLineLoaded() {
        if (currentLine == null) {
            currentLine = readLineFromSource();
            currentIndex = 0;
        }
    }

    private String readToken() {
        int start = currentIndex;
        while (currentIndex < currentLine.length() && !Character.isWhitespace(currentLine.charAt(currentIndex))) {
            currentIndex++;
        }
        return currentLine.substring(start, currentIndex);
    }

    private int skipWhitespace(String value, int startIndex) {
        int index = startIndex;
        while (index < value.length() && Character.isWhitespace(value.charAt(index))) {
            index++;
        }
        return index;
    }

    private String readLineFromSource() {
        try {
            String line = reader.readLine();
            if (line == null) {
                throw new IllegalStateException("더 이상 읽을 입력이 없습니다.");
            }
            return line;
        } catch (IOException exception) {
            throw new IllegalStateException("입력 소스에서 값을 읽는 데 실패했습니다.", exception);
        }
    }

    private void clearBufferedLine() {
        currentLine = null;
        currentIndex = 0;
    }
}
